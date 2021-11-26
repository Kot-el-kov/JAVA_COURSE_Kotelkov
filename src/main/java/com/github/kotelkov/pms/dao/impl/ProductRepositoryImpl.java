package com.github.kotelkov.pms.dao.impl;

import com.github.kotelkov.pms.dao.AbstractDao;
import com.github.kotelkov.pms.dao.ProductRepository;
import com.github.kotelkov.pms.entity.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ProductRepositoryImpl extends AbstractDao<Product, Long> implements ProductRepository {

    public ProductRepositoryImpl() {
        super(Product.class);
    }

    @Override
    public List<Product> getProductsSortedByPrice() {
        final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        final Root<Product> root = cq.from(Product.class);
        CriteriaQuery<Product> query = cq.select(root).orderBy(cb.asc(root.get("price")));
        TypedQuery<Product> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }
}
