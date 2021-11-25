package com.github.kotelkov.pms.dao.impl;

import com.github.kotelkov.pms.dao.AbstractDao;
import com.github.kotelkov.pms.dao.StoreRepository;
import com.github.kotelkov.pms.entity.Store;
import com.github.kotelkov.pms.entity.Store_;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.Map;


@Repository
public class StoreRepositoryImpl extends AbstractDao<Store,Long> implements StoreRepository {

    public StoreRepositoryImpl() {
        super(Store.class);
    }

    @Override
    public Store getByIdWithProductsCriteria(Long id) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Store> query = criteriaBuilder.createQuery(Store.class);
        final Root<Store> from = query.from(Store.class);
        from.fetch(Store_.products, JoinType.LEFT);
        return entityManager.createQuery(query.select(from).
                where(criteriaBuilder.equal(from.get(Store_.id), id))).getSingleResult();
    }

    @Override
    public Store getByIdWithProductsJPQL(Long id) {
        return entityManager.createQuery("select store from Store store left join fetch store.products products where store.id =:id", Store.class)
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public Store getByIdWithProductsGraph(Long id) {
        EntityGraph<?> graph = this.entityManager.getEntityGraph("with-products");
        Map<String, Object> hints = new HashMap<>();
        hints.put("javax.persistence.fetchgraph", graph);
        return entityManager.find(Store.class, id, hints);
    }
}
