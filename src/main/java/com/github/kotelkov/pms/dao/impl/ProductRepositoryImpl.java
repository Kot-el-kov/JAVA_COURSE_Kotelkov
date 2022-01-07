package com.github.kotelkov.pms.dao.impl;

import com.github.kotelkov.pms.dao.AbstractDao;
import com.github.kotelkov.pms.dao.ProductRepository;
import com.github.kotelkov.pms.entity.Product;
import com.github.kotelkov.pms.entity.Product_;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ProductRepositoryImpl extends AbstractDao<Product, Long> implements ProductRepository {

    public ProductRepositoryImpl() {
        super(Product.class);
    }

    @Override
    public void addProductToHistory(Long id,Long productId){
        entityManager.createNativeQuery("insert into histories(user_id,product_id) values(?,?)").
                setParameter(1,id).
                setParameter(2,productId).executeUpdate();
    }

    @Override
    public void addProductToWishlist(Long id, Long productId) {
        entityManager.createNativeQuery("insert into wishlists(user_id,product_id) values(?,?)").
                setParameter(1,id).
                setParameter(2,productId).executeUpdate();
    }

    @Override
    public void deleteProductFromWishlist(Long id, Long productId) {
        entityManager.createNativeQuery("delete from wishlists where user_id=:id and product_id=:productId ").
                setParameter("id",id).
                setParameter("productId",productId).
                executeUpdate();

    }

    @Override
    public void addProductToStore(Long productId,Long storeId){
        entityManager.createNativeQuery("insert into stores_products(product_id,store_id) values(?,?)").
                setParameter(1,productId).
                setParameter(2,storeId).executeUpdate();
    }

    @Override
    public void deleteProductFromStore(Long productId,Long storeId){
        entityManager.createNativeQuery("delete from stores_products where product_id=:productId and store_id=:storeId").
                setParameter("productId",productId).
                setParameter("storeId",storeId).
                executeUpdate();
    }

    @Override
    public Product getProductWithStores(Long productId){
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
        final Root<Product> from = query.from(Product.class);
        from.fetch(Product_.STORES, JoinType.LEFT);
        TypedQuery<Product> allQuery = entityManager.createQuery(query.select(from).
                where(criteriaBuilder.equal(from.get(Product_.ID), productId)));
        return allQuery.getSingleResult();
    }

    @Override
    public List<Product> getProductsByName(String name,Pageable pageable) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
        final Root<Product> rows = query.from(Product.class);
        query.orderBy(criteriaBuilder.asc(rows.get(pageable.getSort().toList().get(0).getProperty())));
        query.where(criteriaBuilder.equal(rows.get(Product_.name), name));
        return entityManager.createQuery(query).setFirstResult((int) pageable.getOffset()).
                setMaxResults(pageable.getPageSize()).
                getResultList();
    }
}
