package com.github.kotelkov.pms.controller;

import com.github.kotelkov.pms.mapper.JsonToModelMapper;
import com.github.kotelkov.pms.model.Product;
import com.github.kotelkov.pms.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductController{

    @Autowired
    private JsonToModelMapper jsonToModelMapper;
    @Autowired
    private ProductService productService;

    public void createProduct(String jsonString) {
        Product product = (Product) jsonToModelMapper.convertToModel(jsonString,Product.class);
        productService.createProduct(product);
    }


    public String getProductById(String jsonString) {
        Product product = (Product) jsonToModelMapper.convertToModel(jsonString,Product.class);
        return jsonToModelMapper.convertToJson(productService.getProductById(product.getId()));
    }


    public String getAllProducts() {
        return jsonToModelMapper.convertToJson(productService.getAllProducts());
    }


    public boolean updateProduct(String jsonString) {
        Product product = (Product) jsonToModelMapper.convertToModel(jsonString,Product.class);
        return productService.updateProduct(product);
    }


    public boolean deleteProductById(String jsonString) {
        Product product = (Product) jsonToModelMapper.convertToModel(jsonString,Product.class);
        return productService.deleteProductById(product.getId());
    }
}
