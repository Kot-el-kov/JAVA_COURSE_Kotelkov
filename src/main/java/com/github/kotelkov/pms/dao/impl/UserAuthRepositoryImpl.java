package com.github.kotelkov.pms.dao.impl;

import com.github.kotelkov.pms.dao.AbstractDao;
import com.github.kotelkov.pms.dao.UserAuthRepository;
import com.github.kotelkov.pms.entity.*;
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

    @Override
    public UserAuth getByIdWithRole(Long id){
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<UserAuth> query = criteriaBuilder.createQuery(UserAuth.class);
        final Root<UserAuth> from = query.from(UserAuth.class);
        from.fetch(UserAuth_.ROLE,JoinType.LEFT);
        return entityManager.createQuery(query.select(from).
                where(criteriaBuilder.equal(from.get(UserAuth_.ID), id))).getSingleResult();
    }

    @Override
    public UserAuth getUserAuthWithUserProfile(Long id) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<UserAuth> query = criteriaBuilder.createQuery(UserAuth.class);
        final Root<UserAuth> from = query.from(UserAuth.class);
        from.fetch(UserAuth_.USER_PROFILE,JoinType.LEFT);
        return entityManager.createQuery(query.select(from).
                where(criteriaBuilder.equal(from.get(UserAuth_.ID), id))).getSingleResult();
    }

    @Override
    public UserAuth getByLogin(String login) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<UserAuth> query = criteriaBuilder.createQuery(UserAuth.class);
        final Root<UserAuth> rows = query.from(UserAuth.class);
        query.where(criteriaBuilder.equal(rows.get(UserAuth_.login), login));
        return entityManager.createQuery(query).getSingleResult();
    }

    @Override
    public void deleteUserAuth(Long id){
        UserProfile userProfile = entityManager.find(UserProfile.class,id);
        if (userProfile!=null) entityManager.remove(userProfile);
        entityManager.remove(entityManager.find(UserAuth.class,id));
    }

    @Override
    public UserAuth save(UserAuth userAuth){
        entityManager.persist(userAuth);
        return getByLoginWithRole(userAuth.getLogin());
    }
}
