package com.github.kotelkov.pms.service;

import com.github.kotelkov.pms.dto.ProductDto;

import java.util.List;

public interface ProductService {
    void createProduct(ProductDto productDto);
    ProductDto getProductById(Long id);
    List<ProductDto> getAllProducts();
    void updateProduct(ProductDto productDto);
    void deleteProduct(Long id);
    List<ProductDto> getProductSortedByPrice();
}
