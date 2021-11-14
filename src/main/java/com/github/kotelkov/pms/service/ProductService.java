package com.github.kotelkov.pms.service;

import com.github.kotelkov.pms.model.Product;

import java.util.List;

public interface ProductService {
    void createProduct(Product product) throws Exception;
    Product getProductById(int id) throws Exception;
    List<Product> getAllProducts() throws Exception;
    void updateProduct(Product product) throws Exception;
    void deleteProductById(int id) throws Exception;
}
