package com.github.kotelkov.pms.service;

import com.github.kotelkov.pms.dto.UserProfileDto;

import java.util.List;

public interface UserProfileService {
    UserProfileDto createUserProfile(UserProfileDto userProfileDto);
    UserProfileDto getUserProfileById(Long id);
    List<UserProfileDto> getAllUsersProfiles();
    UserProfileDto updateUserProfile(UserProfileDto userProfileDto);
    void deleteUserProfileById(Long id);
}
