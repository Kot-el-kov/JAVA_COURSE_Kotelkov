package com.github.kotelkov.pms.dao;

import com.github.kotelkov.pms.entity.Product;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ProductRepository extends GenericDao<Product, Long>{
    List<Product> getProductsSortedByPrice();
    Product getByName(String name);
}
