package com.github.kotelkov.pms.controller;

import com.github.kotelkov.pms.dto.ProductCreateDto;
import com.github.kotelkov.pms.dto.ProductDto;
import com.github.kotelkov.pms.exception.ResourceNotFoundException;
import com.github.kotelkov.pms.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController{

    @Autowired
    private ProductService productService;

    @PreAuthorize(value = "hasRole('ADMIN')")
    @PostMapping
    public ProductDto createProduct(@RequestBody ProductCreateDto productDto) {
        return  productService.createProduct(productDto);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN','USER')")
    @GetMapping(value = "/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
       ProductDto productDto = Optional.ofNullable(productService.getProductById(id)).
               orElseThrow(() -> new ResourceNotFoundException("Product with id: "+id+" not found"));
       return productDto;
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN','USER')")
    @GetMapping
    public List<ProductDto> getAllProducts() {
        List<ProductDto> productDtoList = Optional.ofNullable(productService.getAllProducts()).
                orElseThrow(()-> new ResourceNotFoundException("Products not found"));
        return productDtoList;
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return productService.updateProduct(productDto);
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN','USER')")
    @GetMapping(value = "/sorted-price")
    public List<ProductDto> getProductBySortedPrice(){
        return productService.getProductSortedByPrice();
    }
}
