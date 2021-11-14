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

    public void createProduct(String jsonString) throws Exception {
        Product product = (Product) jsonToModelMapper.convertToModel(jsonString,Product.class);
        productService.createProduct(product);
    }


    public String getProductById(String jsonString) throws Exception {
        Product product = (Product) jsonToModelMapper.convertToModel(jsonString,Product.class);
        return jsonToModelMapper.convertToJson(productService.getProductById(product.getId()));
    }


    public String getAllProducts() throws Exception {
        return jsonToModelMapper.convertToJson(productService.getAllProducts());
    }


    public void updateProduct(String jsonString) throws Exception {
        Product product = (Product) jsonToModelMapper.convertToModel(jsonString,Product.class);
        productService.updateProduct(product);
    }


    public void deleteProductById(String jsonString) throws Exception {
        Product product = (Product) jsonToModelMapper.convertToModel(jsonString,Product.class);
        productService.deleteProductById(product.getId());
    }
}
