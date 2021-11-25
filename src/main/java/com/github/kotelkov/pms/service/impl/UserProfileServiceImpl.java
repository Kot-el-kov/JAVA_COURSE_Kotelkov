package com.github.kotelkov.pms.service.impl;

import com.github.kotelkov.pms.dao.UserProfileRepository;
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
    public void createUserProfile(UserProfile userProfile) {

    }

    @Override
    public UserProfile getUserProfileById(Long id) {
        return null;
    }

    @Override
    public List<UserProfile> getAllUsersProfiles() {
        return null;
    }

    @Override
    public boolean updateUserProfile(UserProfile userProfile) {
        return false;
    }

    @Override
    public boolean deleteUserProfileById(Long id) {
        return false;
    }
}
