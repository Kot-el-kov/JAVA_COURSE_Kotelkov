package com.github.kotelkov.pms.dao.impl;

import com.github.kotelkov.pms.dao.UserProfileRepository;
import com.github.kotelkov.pms.model.UserProfile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserProfileRepositoryImpl implements UserProfileRepository {

    private ArrayList<UserProfile> userProfileArrayList = new ArrayList<>();
    @Override
    public void createUserProfile(UserProfile userProfile) {
        userProfileArrayList.add(userProfile);
    }

    @Override
    public UserProfile getUserProfileById(int id) {
        for (UserProfile userProfile:userProfileArrayList) {
            if (userProfile.getId()==id){
                return userProfile;
            }
        }
        return null;
    }

    @Override
    public List<UserProfile> getAllUsersProfiles() {
        return userProfileArrayList;
    }


    @Override
    public boolean updateUserProfile(UserProfile userProfile) {
        UserProfile curUserProfile = getUserProfileById(userProfile.getId());
        if (curUserProfile!=null){
            userProfileArrayList.remove(curUserProfile);
            userProfileArrayList.add(userProfile);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUserProfileById(int id) {
        UserProfile curUserProfile = getUserProfileById(id);
        if (curUserProfile!=null){
            userProfileArrayList.remove(curUserProfile);
            return true;
        }
        return false;
    }
}
