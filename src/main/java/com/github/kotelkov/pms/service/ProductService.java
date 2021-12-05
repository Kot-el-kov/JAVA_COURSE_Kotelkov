package com.github.kotelkov.pms.service;

import com.github.kotelkov.pms.dto.ProductDto;

import javax.swing.text.html.parser.Entity;
import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);
    ProductDto getProductById(Long id);
    List<ProductDto> getAllProducts();
    ProductDto updateProduct(ProductDto productDto);
    void deleteProduct(Long id);
    List<ProductDto> getProductSortedByPrice();
}
