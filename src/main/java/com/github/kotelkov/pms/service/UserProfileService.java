package com.github.kotelkov.pms.service;

import com.github.kotelkov.pms.entity.UserProfile;

import java.util.List;

public interface UserProfileService {
    void createUserProfile(UserProfile userProfile);
    UserProfile getUserProfileById(Long id);
    List<UserProfile> getAllUsersProfiles();
    boolean updateUserProfile(UserProfile userProfile);
    boolean deleteUserProfileById(Long id);
}
