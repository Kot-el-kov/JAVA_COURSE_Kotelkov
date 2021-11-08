package com.github.kotelkov.pms.dao;

import com.github.kotelkov.pms.model.UserAuth;

import java.util.ArrayList;

public interface UserAuthRepository {
    void createUserAuth(UserAuth userAuth);
    UserAuth getUserAuthById(int id);
    ArrayList<UserAuth> getAllUsersAuths();
    boolean updateUserAuth(UserAuth userAuth);
    boolean deleteUserAuthById(int id);
}
