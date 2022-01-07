package com.github.kotelkov.pms.dao.impl;

import com.github.kotelkov.pms.dao.AbstractDao;
import com.github.kotelkov.pms.dao.StoreRepository;
import com.github.kotelkov.pms.entity.Store;
import com.github.kotelkov.pms.entity.Store_;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;


@Repository
public class StoreRepositoryImpl extends AbstractDao<Store,Long> implements StoreRepository {

    public StoreRepositoryImpl() {
        super(Store.class);
    }

    @Override
    public Store getStoreWithProducts(Long id) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Store> query = criteriaBuilder.createQuery(Store.class);
        final Root<Store> from = query.from(Store.class);
        from.fetch(Store_.PRODUCTS,JoinType.LEFT);
        return entityManager.createQuery(query.select(from).
                where(criteriaBuilder.equal(from.get(Store_.ID), id))).getSingleResult();
    }

    @Override
    public Store getStoreByName(String name){
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Store> query = criteriaBuilder.createQuery(Store.class);
        final Root<Store> from = query.from(Store.class);
        return entityManager.createQuery(query.select(from).
                where(criteriaBuilder.equal(from.get(Store_.NAME), name))).getSingleResult();
    }
}
