package com.github.kotelkov.pms.service.impl;

import com.github.kotelkov.pms.dao.UserAuthRepository;
import com.github.kotelkov.pms.model.UserAuth;
import com.github.kotelkov.pms.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Override
    public void createUserAuth(UserAuth userAuth) {
        userAuthRepository.createUserAuth(userAuth);
    }

    @Override
    public UserAuth getUserAuthById(int id) {
        return userAuthRepository.getUserAuthById(id);
    }

    @Override
    public List<UserAuth> getAllUsersAuths() {
        return userAuthRepository.getAllUsersAuths();
    }

    @Override
    public boolean updateUserAuth(UserAuth userAuth) {
        return userAuthRepository.updateUserAuth(userAuth);
    }

    @Override
    public boolean deleteUserAuthById(int id) {
        return userAuthRepository.deleteUserAuthById(id);
    }
}
