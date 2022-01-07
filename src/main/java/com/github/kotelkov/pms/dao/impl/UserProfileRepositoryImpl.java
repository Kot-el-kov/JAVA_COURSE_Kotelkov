package com.github.kotelkov.pms.dao.impl;

import com.github.kotelkov.pms.dao.AbstractDao;
import com.github.kotelkov.pms.dao.UserProfileRepository;
import com.github.kotelkov.pms.entity.*;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

@Repository
public class UserProfileRepositoryImpl extends AbstractDao<UserProfile,Long> implements UserProfileRepository {

    public UserProfileRepositoryImpl() {
        super(UserProfile.class);
    }


    @Override
    public UserProfile getUserProfileWithHistory(Long id){
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<UserProfile> query = criteriaBuilder.createQuery(UserProfile.class);
        final Root<UserProfile> from = query.from(UserProfile.class);
        from.fetch(UserProfile_.HISTORY, JoinType.LEFT);
        return entityManager.createQuery(query.select(from).
                where(criteriaBuilder.equal(from.get(UserProfile_.ID), id))).getSingleResult();
    }

    @Override
    public void clearWishlist(Long id) {
        entityManager.createNativeQuery("delete from wishlists where user_id=:id").
                setParameter("id",id).executeUpdate();
    }

    @Override
    public UserProfile getUserProfileWithWishlist(Long id){
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<UserProfile> query = criteriaBuilder.createQuery(UserProfile.class);
        final Root<UserProfile> from = query.from(UserProfile.class);
        from.fetch(UserProfile_.WISHLIST, JoinType.LEFT);
        return entityManager.createQuery(query.select(from).
                where(criteriaBuilder.equal(from.get(UserProfile_.ID), id))).getSingleResult();
    }
}
