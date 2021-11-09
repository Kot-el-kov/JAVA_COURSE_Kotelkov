package com.github.kotelkov.pms.service;

import com.github.kotelkov.pms.model.UserProfile;

import java.util.List;

public interface UserProfileService {
    void createUserProfile(UserProfile userProfile);
    UserProfile getUserProfileById(int id);
    List<UserProfile> getAllUsersProfiles();
    boolean updateUserProfile(UserProfile userProfile);
    boolean deleteUserProfileById(int id);
}
