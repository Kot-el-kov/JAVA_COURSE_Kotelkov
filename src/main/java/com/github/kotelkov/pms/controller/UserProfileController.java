package com.github.kotelkov.pms.controller;

import com.github.kotelkov.pms.mapper.Mapper;
import com.github.kotelkov.pms.entity.UserProfile;
import com.github.kotelkov.pms.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserProfileController {


    @Autowired
    private UserProfileService userProfileService;

    public void createUserProfile(String jsonString) {
        //UserProfile userProfile = (UserProfile) mapper.convertToModel(jsonString,UserProfile.class);
        //userProfileService.createUserProfile(userProfile);
    }

    public String getUserProfileById(String jsonString) {
        //UserProfile userProfile = (UserProfile) mapper.convertToModel(jsonString,UserProfile.class);
        return null;//mapper.convertToJson(userProfileService.getUserProfileById(userProfile.getId()));
    }

    public String getAllUsersProfiles() {
        return null;
    }

    public boolean updateUserProfile(String jsonString) {
        //UserProfile userProfile = (UserProfile) mapper.convertToModel(jsonString,UserProfile.class);
        return true;//userProfileService.updateUserProfile(userProfile);
    }

    public boolean deleteUserProfileById(String jsonString) {
        //UserProfile userProfile = (UserProfile) mapper.convertToModel(jsonString,UserProfile.class);
        return true;//userProfileService.deleteUserProfileById(userProfile.getId());
    }
}
