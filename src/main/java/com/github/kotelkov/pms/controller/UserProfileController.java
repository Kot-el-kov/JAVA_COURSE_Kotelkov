package com.github.kotelkov.pms.controller;

import com.github.kotelkov.pms.mapper.JsonToModelMapper;
import com.github.kotelkov.pms.model.UserProfile;
import com.github.kotelkov.pms.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserProfileController {

    @Autowired
    private JsonToModelMapper jsonToModelMapper;
    @Autowired
    private UserProfileService userProfileService;

    public void createUserProfile(String jsonString) {
        UserProfile userProfile = (UserProfile) jsonToModelMapper.convertToModel(jsonString,UserProfile.class);
        userProfileService.createUserProfile(userProfile);
    }

    public String getUserProfileById(String jsonString) {
        UserProfile userProfile = (UserProfile) jsonToModelMapper.convertToModel(jsonString,UserProfile.class);
        return jsonToModelMapper.convertToJson(userProfileService.getUserProfileById(userProfile.getId()));
    }

    public String getAllUsersProfiles() {
        return jsonToModelMapper.convertToJson(userProfileService.getAllUsersProfiles());
    }

    public boolean updateUserProfile(String jsonString) {
        UserProfile userProfile = (UserProfile) jsonToModelMapper.convertToModel(jsonString,UserProfile.class);
        return userProfileService.updateUserProfile(userProfile);
    }

    public boolean deleteUserProfileById(String jsonString) {
        UserProfile userProfile = (UserProfile) jsonToModelMapper.convertToModel(jsonString,UserProfile.class);
        return userProfileService.deleteUserProfileById(userProfile.getId());
    }
}
