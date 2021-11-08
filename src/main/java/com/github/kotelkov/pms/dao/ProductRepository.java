package com.github.kotelkov.pms.dao;

import com.github.kotelkov.pms.model.Product;

import java.util.ArrayList;

public interface ProductRepository {
    void createProduct(Product product);
    Product getProductById(int id);
    ArrayList<Product> getAllProducts();
    boolean updateProduct(Product product);
    boolean deleteProductById(int id);
}
