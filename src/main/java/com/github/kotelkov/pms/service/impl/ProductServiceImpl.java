package com.github.kotelkov.pms.service.impl;

import com.github.kotelkov.pms.annotation.Transactional;
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

    @Transactional
    @Override
    public void createProduct(Product product) throws Exception {
        productRepository.createProduct(product);
    }

    @Transactional
    @Override
    public Product getProductById(int id) throws Exception {
        return productRepository.getProductById(id);
    }

    @Transactional
    @Override
    public List<Product> getAllProducts() throws Exception {
        return productRepository.getAllProducts();
    }

    @Transactional
    @Override
    public void updateProduct(Product product) throws Exception {
        productRepository.updateProduct(product);
    }

    @Transactional
    @Override
    public void deleteProductById(int id) throws Exception {
        productRepository.deleteProductById(id);
    }
}
