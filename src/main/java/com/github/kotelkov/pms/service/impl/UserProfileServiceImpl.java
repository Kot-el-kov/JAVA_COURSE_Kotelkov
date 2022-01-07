package com.github.kotelkov.pms.service.impl;

import com.github.kotelkov.pms.dao.UserProfileRepository;
import com.github.kotelkov.pms.dto.user.profile.UserProfileCreateDto;
import com.github.kotelkov.pms.dto.user.profile.UserProfileDto;
import com.github.kotelkov.pms.dto.user.profile.UserProfileWithHistoryDto;
import com.github.kotelkov.pms.dto.user.profile.UserProfileWithWishlistDto;
import com.github.kotelkov.pms.entity.UserProfile;
import com.github.kotelkov.pms.mapper.Mapper;
import com.github.kotelkov.pms.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private Mapper mapper;

    @Transactional
    @Override
    public UserProfileDto createUserProfile(Long userId,UserProfileCreateDto userProfileCreateDto) {
        UserProfile userProfile = (UserProfile) mapper.convertToModel(userProfileCreateDto,UserProfile.class);
        userProfile.setId(userId);
        return (UserProfileDto) mapper.convertToDto(userProfileRepository.save(userProfile),UserProfileDto.class);
    }

    @Transactional
    @Override
    public UserProfileDto getUserProfileById(Long id) {
        return (UserProfileDto) mapper.convertToDto(userProfileRepository.getById(id),UserProfileDto.class);
    }

    @Transactional
    @Override
    public Page getAllUsersProfiles(Pageable pageable) {
        List userProfileDtoList = mapper.
                convertListToDtoList(userProfileRepository.getAll(pageable),UserProfileDto.class);
        return new PageImpl(userProfileDtoList,pageable,userProfileDtoList.size());
    }

    @Transactional
    @Override
    public UserProfileDto updateUserProfile(Long userId,UserProfileCreateDto userProfileCreateDto) {
        UserProfile userProfile = (UserProfile) mapper.convertToModel(userProfileCreateDto,UserProfile.class);
        userProfile.setId(userId);
        return (UserProfileDto) mapper.convertToDto(userProfileRepository.update(userProfile),UserProfileDto.class);
    }

    @Transactional
    @Override
    public void deleteUserProfileById(Long id) {
        userProfileRepository.delete(id);
    }

    @Transactional
    @Override
    public UserProfileWithHistoryDto getUserProfileWithHistory(Long id){
        return (UserProfileWithHistoryDto) mapper.
                convertToDto(userProfileRepository.getUserProfileWithHistory(id),UserProfileWithHistoryDto.class);
    }

    @Transactional
    @Override
    public UserProfileWithWishlistDto getUserProfileWithWishlist(Long id){
        return (UserProfileWithWishlistDto) mapper.
                convertToDto(userProfileRepository.getUserProfileWithWishlist(id),UserProfileWithWishlistDto.class);
    }

    @Transactional
    @Override
    public void clearWishlist(Long id) {
        userProfileRepository.clearWishlist(id);
    }
}
