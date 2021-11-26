package com.github.kotelkov.pms.dao.impl;

import com.github.kotelkov.pms.dao.AbstractDao;
import com.github.kotelkov.pms.dao.UserAuthRepository;
import com.github.kotelkov.pms.entity.UserAuth;
import org.springframework.stereotype.Repository;

@Repository
public class UserAuthRepositoryImpl extends AbstractDao<UserAuth,Long> implements UserAuthRepository {

    public UserAuthRepositoryImpl() {
        super(UserAuth.class);
    }
}
