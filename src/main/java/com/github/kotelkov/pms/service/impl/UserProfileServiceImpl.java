package com.github.kotelkov.pms.service.impl;

import com.github.kotelkov.pms.dao.UserProfileRepository;
import com.github.kotelkov.pms.dto.user.profile.UserProfileCreateDto;
import com.github.kotelkov.pms.dto.user.profile.UserProfileDto;
import com.github.kotelkov.pms.dto.user.profile.UserProfileWithHistoryDto;
import com.github.kotelkov.pms.dto.user.profile.UserProfileWithWishlistDto;
import com.github.kotelkov.pms.entity.UserProfile;
import com.github.kotelkov.pms.exception.ResourceNotFoundException;
import com.github.kotelkov.pms.mapper.Mapper;
import com.github.kotelkov.pms.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private Mapper mapper;

    @Transactional
    @Override
    public UserProfileDto createUserProfile(Long userId,UserProfileCreateDto userProfileCreateDto) {
        UserProfile userProfile = mapper.convert(userProfileCreateDto,UserProfile.class);
        userProfile.setId(userId);
        return mapper.convert(userProfileRepository.save(userProfile),UserProfileDto.class);
    }

    @Transactional
    @Override
    public UserProfileDto getUserProfileById(Long id) {
        return Optional.ofNullable(mapper.convert(userProfileRepository.getById(id),UserProfileDto.class)).
                orElseThrow(()->new ResourceNotFoundException("UserProfile with id: "+id+" not found"));
    }

    @Transactional
    @Override
    public Page getAllUsersProfiles(Pageable pageable) {
        List userProfileList = userProfileRepository.getAll(pageable);
        List userProfileDtoList = mapper.convert(userProfileList,UserProfileDto.class);
        return new PageImpl(userProfileDtoList,pageable,userProfileDtoList.size());
    }

    @Transactional
    @Override
    public UserProfileDto updateUserProfile(Long userId,UserProfileCreateDto userProfileCreateDto) {
        UserProfile userProfile = mapper.convert(userProfileCreateDto,UserProfile.class);
        userProfile.setId(userId);
        return mapper.convert(userProfileRepository.update(userProfile),UserProfileDto.class);
    }

    @Transactional
    @Override
    public void deleteUserProfileById(Long id) {
        userProfileRepository.delete(id);
    }

    @Transactional
    @Override
    public UserProfileWithHistoryDto getUserProfileWithHistory(Long id){
        return mapper.convert(userProfileRepository.getUserProfileWithHistory(id),UserProfileWithHistoryDto.class);
    }

    @Transactional
    @Override
    public UserProfileWithWishlistDto getUserProfileWithWishlist(Long id){
        return mapper.convert(userProfileRepository.getUserProfileWithWishlist(id),UserProfileWithWishlistDto.class);
    }

    @Transactional
    @Override
    public void clearWishlist(Long id) {
        userProfileRepository.clearWishlist(id);
    }
}
