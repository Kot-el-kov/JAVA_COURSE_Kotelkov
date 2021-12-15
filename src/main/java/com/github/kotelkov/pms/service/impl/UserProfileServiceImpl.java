package com.github.kotelkov.pms.service.impl;

import com.github.kotelkov.pms.dao.UserProfileRepository;
import com.github.kotelkov.pms.dto.UserProfileDto;
import com.github.kotelkov.pms.entity.UserProfile;
import com.github.kotelkov.pms.mapper.Mapper;
import com.github.kotelkov.pms.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private Mapper mapper;

    @Override
    public UserProfileDto createUserProfile(UserProfileDto userProfileDto) {
        return (UserProfileDto) mapper.convertToDto(userProfileRepository.
                save((UserProfile) mapper.convertToModel(userProfileDto,UserProfile.class)),UserProfileDto.class);
    }

    @Override
    public UserProfileDto getUserProfileById(Long id) {
        return (UserProfileDto) mapper.convertToDto(userProfileRepository.getById(id),UserProfileDto.class);
    }

    @Override
    public List<UserProfileDto> getAllUsersProfiles() {
        return mapper.convertListToDtoList(userProfileRepository.getAll(),UserProfileDto.class);
    }

    @Override
    public UserProfileDto updateUserProfile(UserProfileDto userProfileDto) {
        return (UserProfileDto) mapper.convertToDto(userProfileRepository.update((UserProfile)
                mapper.convertToModel(userProfileDto,UserProfile.class)),UserProfileDto.class);
    }

    @Override
    public void deleteUserProfileById(Long id) {
        userProfileRepository.delete(id);
    }
}
