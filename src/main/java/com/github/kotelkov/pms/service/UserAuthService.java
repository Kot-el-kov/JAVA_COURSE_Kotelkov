package com.github.kotelkov.pms.service;

import com.github.kotelkov.pms.model.UserAuth;

import java.util.List;

public interface UserAuthService {
    void createUserAuth(UserAuth userAuth);
    UserAuth getUserAuthById(int id);
    List<UserAuth> getAllUsersAuths();
    boolean updateUserAuth(UserAuth userAuth);
    boolean deleteUserAuthById(int id);
}
