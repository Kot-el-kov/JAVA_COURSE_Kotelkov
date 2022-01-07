package com.github.kotelkov.pms.dao;

import com.github.kotelkov.pms.entity.UserAuth;

public interface UserAuthRepository extends GenericDao<UserAuth,Long>{
    UserAuth getByLoginWithRole(String login);
    UserAuth getByLogin(String login);
    UserAuth getByIdWithRole(Long id);
    void deleteUserAuth(Long id);
    UserAuth getUserAuthWithUserProfile(Long id);
}
