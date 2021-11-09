package com.github.kotelkov.pms.dao;

import com.github.kotelkov.pms.model.Product;

import java.util.List;

public interface ProductRepository {
    void createProduct(Product product);
    Product getProductById(int id);
    List<Product> getAllProducts();
    boolean updateProduct(Product product);
    boolean deleteProductById(int id);
}
