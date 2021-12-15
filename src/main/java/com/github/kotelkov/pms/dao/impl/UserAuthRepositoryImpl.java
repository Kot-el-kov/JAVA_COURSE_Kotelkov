package com.github.kotelkov.pms.dao.impl;

import com.github.kotelkov.pms.dao.AbstractDao;
import com.github.kotelkov.pms.dao.UserAuthRepository;
import com.github.kotelkov.pms.entity.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

@Repository
public class UserAuthRepositoryImpl extends AbstractDao<UserAuth,Long> implements UserAuthRepository {

    public UserAuthRepositoryImpl() {
        super(UserAuth.class);
    }

    @Override
    public UserAuth getByLoginWithRole(String login){
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<UserAuth> query = criteriaBuilder.createQuery(UserAuth.class);
        final Root<UserAuth> from = query.from(UserAuth.class);
        from.fetch(UserAuth_.ROLE,JoinType.LEFT);
        return entityManager.createQuery(query.select(from).
                where(criteriaBuilder.equal(from.get(UserAuth_.LOGIN), login))).getSingleResult();
    }
}
