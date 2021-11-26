package com.github.kotelkov.pms.dao;

import com.github.kotelkov.pms.entity.Product;

import java.util.List;

public interface ProductRepository extends GenericDao<Product, Long>{
    List<Product> getProductsSortedByPrice();
}
