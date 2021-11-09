package com.github.kotelkov.pms.service.impl;

import com.github.kotelkov.pms.dao.ProductRepository;
import com.github.kotelkov.pms.model.Product;
import com.github.kotelkov.pms.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void createProduct(Product product) {
        productRepository.createProduct(product);
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.getProductById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    @Override
    public boolean updateProduct(Product product) {
        return productRepository.updateProduct(product);
    }

    @Override
    public boolean deleteProductById(int id) {
        return productRepository.deleteProductById(id);
    }
}
