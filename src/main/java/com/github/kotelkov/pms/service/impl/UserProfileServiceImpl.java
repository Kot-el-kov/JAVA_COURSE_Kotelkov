package com.github.kotelkov.pms.service.impl;

import com.github.kotelkov.pms.dao.UserProfileRepository;
import com.github.kotelkov.pms.model.UserProfile;
import com.github.kotelkov.pms.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Override
    public void createUserProfile(UserProfile userProfile) {
        userProfileRepository.createUserProfile(userProfile);
    }

    @Override
    public UserProfile getUserProfileById(int id) {
        return userProfileRepository.getUserProfileById(id);
    }

    @Override
    public ArrayList<UserProfile> getAllUsersProfiles() {
        return userProfileRepository.getAllUsersProfiles();
    }

    @Override
    public boolean updateUserProfile(UserProfile userProfile) {
        return userProfileRepository.updateUserProfile(userProfile);
    }

    @Override
    public boolean deleteUserProfileById(int id) {
        return userProfileRepository.deleteUserProfileById(id);
    }
}
