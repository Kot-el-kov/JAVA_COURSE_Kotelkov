package com.github.kotelkov.pms.dao.impl;

import com.github.kotelkov.pms.dao.AbstractDao;
import com.github.kotelkov.pms.dao.UserProfileRepository;
import com.github.kotelkov.pms.entity.UserProfile;
import org.springframework.stereotype.Repository;

@Repository
public class UserProfileRepositoryImpl extends AbstractDao<UserProfile,Long> implements UserProfileRepository {

    public UserProfileRepositoryImpl() {
        super(UserProfile.class);
    }
}
