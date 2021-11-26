package com.github.kotelkov.pms.controller;

import com.github.kotelkov.pms.mapper.Mapper;
import com.github.kotelkov.pms.entity.UserAuth;
import com.github.kotelkov.pms.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserAuthController {

    @Autowired
    private Mapper mapper;
    @Autowired
    private UserAuthService userAuthService;

    public void createUserAuth(String jsonString) {
        UserAuth userAuth = (UserAuth) mapper.convertToModel(jsonString,UserAuth.class);

    }

    public String getUserAuthById(String jsonString) {
        UserAuth userAuth = (UserAuth) mapper.convertToModel(jsonString,UserAuth.class);
        return null;
    }

    public String getAllUsersAuths() {
        return null;
    }
    

}
