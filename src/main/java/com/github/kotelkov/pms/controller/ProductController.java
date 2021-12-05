package com.github.kotelkov.pms.controller;

import com.github.kotelkov.pms.dto.ProductDto;
import com.github.kotelkov.pms.exception.ResourceNotFoundException;
import com.github.kotelkov.pms.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController{

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity createProduct(@RequestBody ProductDto productDto) {
        productService.createProduct(productDto);
        return ResponseEntity.ok(productDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getProductById(@PathVariable Long id) {
       ProductDto productDto = Optional.ofNullable(productService.getProductById(id)).
               orElseThrow(() -> new ResourceNotFoundException("Product with id: "+id+" not found"));
       return ResponseEntity.ok(productDto);
    }

    @RequestMapping(value = "/all")
    public ResponseEntity getAllProducts() {
        List<ProductDto> productDtoList = Optional.ofNullable(productService.getAllProducts()).
                orElseThrow(()-> new ResourceNotFoundException("Products not found"));
        return ResponseEntity.ok(productDtoList);
    }

    @PutMapping
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.updateProduct(productDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/all/sort/price")
    public ResponseEntity getProductBySortedPrice(){
        return ResponseEntity.ok(productService.getProductSortedByPrice());
    }
}
