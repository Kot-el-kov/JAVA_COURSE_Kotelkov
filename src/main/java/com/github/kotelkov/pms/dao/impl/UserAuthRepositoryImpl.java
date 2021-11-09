package com.github.kotelkov.pms.dao.impl;

import com.github.kotelkov.pms.dao.UserAuthRepository;
import com.github.kotelkov.pms.model.UserAuth;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserAuthRepositoryImpl implements UserAuthRepository {

    private ArrayList<UserAuth> userAuthArrayList = new ArrayList<>();
    @Override
    public void createUserAuth(UserAuth userAuth) {
        userAuthArrayList.add(userAuth);
    }

    @Override
    public UserAuth getUserAuthById(int id) {
        for (UserAuth userAuth:userAuthArrayList) {
            if (userAuth.getId()==id){
                return userAuth;
            }
        }
        return null;
    }

    @Override
    public List<UserAuth> getAllUsersAuths() {
        return userAuthArrayList;
    }


    @Override
    public boolean updateUserAuth(UserAuth userAuth) {
        UserAuth curUserAuth = getUserAuthById(userAuth.getId());
        if (curUserAuth!=null){
            userAuthArrayList.remove(curUserAuth);
            userAuthArrayList.add(userAuth);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUserAuthById(int id) {
        UserAuth curUserAuth = getUserAuthById(id);
        if (curUserAuth!=null){
            userAuthArrayList.remove(curUserAuth);
            return true;
        }
        return false;
    }
}
