package com.github.kotelkov.pms.controller;

import com.github.kotelkov.pms.dto.product.ProductCreateDto;
import com.github.kotelkov.pms.dto.product.ProductDto;
import com.github.kotelkov.pms.dto.product.ProductWithStoresDto;
import com.github.kotelkov.pms.exception.ResourceNotFoundException;
import com.github.kotelkov.pms.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static org.springframework.data.domain.Sort.Direction.ASC;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController{

    @Autowired
    private ProductService productService;

    @PreAuthorize(value = "hasAnyRole('ADMIN','USER','SELLER')")
    @PostMapping
    public ProductDto createProduct(@Valid @RequestBody ProductCreateDto productCreateDto) {
        return  productService.createProduct(productCreateDto);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN','USER','SELLER')")
    @GetMapping("/{productId}")
    public ProductDto getProductById(@AuthenticationPrincipal String userId, @PathVariable Long productId) {
       ProductDto productDto = Optional.ofNullable(productService.getProductById(Long.parseLong(userId),productId)).
               orElseThrow(() -> new ResourceNotFoundException("Product with id: "+productId+" not found"));
       return productDto;
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN','USER','SELLER')")
    @GetMapping
    public Page<ProductDto> getAllProducts(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                           @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
                                           @RequestParam(value = "sort", defaultValue = "id", required = false) String sort) {
        Page<ProductDto> productDtoList = Optional.ofNullable(productService.getAllProducts(PageRequest.of(page, size, ASC, sort))).
                orElseThrow(()-> new ResourceNotFoundException("Products not found"));
        return productDtoList;
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @PutMapping
    public ProductDto updateProduct(@Valid @RequestBody ProductDto productDto) {
        return productService.updateProduct(productDto);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN','SELLER')")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @PreAuthorize(value = "hasAnyRole('ADMIN','USER','SELLER')")
    @PostMapping("/wishlist/{id}")
    public ResponseEntity addProductToWishlist(@AuthenticationPrincipal String userId, @PathVariable Long id){
        productService.addProductToWishlist(Long.parseLong(userId),id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN','SELLER')")
    @PostMapping("/store/{productId}/{storeId}")
    public ResponseEntity addProductToStore(@PathVariable(value = "productId") Long productId, @PathVariable(value = "storeId") Long storeId){
        productService.addProductToStore(productId,storeId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN','USER','SELLER')")
    @GetMapping("/stores/{productId}")
    public ProductWithStoresDto getProductWithStores(@PathVariable Long productId){
        ProductWithStoresDto productWithStoresDto = Optional.ofNullable(productService.getProductWithStores(productId)).
                orElseThrow(()-> new ResourceNotFoundException("Product not found"));
        return productWithStoresDto;
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN','USER','SELLER')")
    @GetMapping("/name/{name}")
    public Page<ProductDto> getProductsByName(@PathVariable String name,
                                       @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                       @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
                                       @RequestParam(value = "sort", defaultValue = "id", required = false) String sort){
        return productService.getProductsByName(name,PageRequest.of(page, size, ASC, sort));
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN','USER','SELLER')")
    @DeleteMapping("/wishlist/{productId}")
    public ResponseEntity deleteProductFromWishlist(@AuthenticationPrincipal String id,@PathVariable Long productId){
        productService.deleteProductFromWishlist(Long.parseLong(id),productId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN','SELLER')")
    @DeleteMapping("/store/{productId}/{storeId}")
    public ResponseEntity deleteProductFromStore(@PathVariable(value = "productId") Long productId,@PathVariable(value = "storeId") Long storeId){
        productService.deleteProductFromStore(productId,storeId);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
