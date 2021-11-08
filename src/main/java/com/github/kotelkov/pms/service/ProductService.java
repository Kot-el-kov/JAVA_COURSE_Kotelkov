package com.github.kotelkov.pms.service;

import com.github.kotelkov.pms.model.Product;

import java.util.ArrayList;

public interface ProductService {
    void createProduct(Product product);
    Product getProductById(int id);
    ArrayList<Product> getAllProducts();
    boolean updateProduct(Product product);
    boolean deleteProductById(int id);
}
