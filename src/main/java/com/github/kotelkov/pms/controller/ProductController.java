package com.github.kotelkov.pms.controller;

import com.github.kotelkov.pms.dto.ProductDto;
import com.github.kotelkov.pms.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductController{

    @Autowired
    private ProductService productService;

    public void createProduct(ProductDto productDto) {
        productService.createProduct(productDto);
    }

    public ProductDto getProductById(Long id) {
       return productService.getProductById(id);
    }

    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    public void updateProduct(ProductDto productDto) {
        productService.updateProduct(productDto);
    }

    public void deleteProduct(Long id) {
        productService.deleteProduct(id);
    }

    public List<ProductDto> getProductBySortedPrice(){
        return productService.getProductSortedByPrice();
    }
}
