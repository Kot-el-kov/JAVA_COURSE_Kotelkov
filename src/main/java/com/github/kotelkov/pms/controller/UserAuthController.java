package com.github.kotelkov.pms.controller;

import com.github.kotelkov.pms.mapper.JsonToModelMapper;
import com.github.kotelkov.pms.model.UserAuth;
import com.github.kotelkov.pms.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserAuthController {

    @Autowired
    private JsonToModelMapper jsonToModelMapper;
    @Autowired
    private UserAuthService userAuthService;

    public void createUserAuth(String jsonString) {
        UserAuth userAuth = (UserAuth) jsonToModelMapper.convertToModel(jsonString,UserAuth.class);
        userAuthService.createUserAuth(userAuth);
    }

    public String getUserAuthById(String jsonString) {
        UserAuth userAuth = (UserAuth) jsonToModelMapper.convertToModel(jsonString,UserAuth.class);
        return jsonToModelMapper.convertToJson(userAuthService.getUserAuthById(userAuth.getId()));
    }

    public String getAllUsersAuths() {
        return jsonToModelMapper.convertToJson(userAuthService.getAllUsersAuths());
    }
    
    public boolean updateUserAuth(String jsonString) {
        UserAuth userAuth = (UserAuth) jsonToModelMapper.convertToModel(jsonString,UserAuth.class);
        return userAuthService.updateUserAuth(userAuth);
    }

    public boolean deleteUserAuthById(String jsonString) {
        UserAuth userAuth = (UserAuth) jsonToModelMapper.convertToModel(jsonString,UserAuth.class);
        return userAuthService.deleteUserAuthById(userAuth.getId());
    }
}
