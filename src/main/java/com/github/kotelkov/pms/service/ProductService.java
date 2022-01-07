package com.github.kotelkov.pms.service;

import com.github.kotelkov.pms.dto.product.ProductCreateDto;
import com.github.kotelkov.pms.dto.product.ProductDto;
import com.github.kotelkov.pms.dto.product.ProductWithStoresDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductDto createProduct(ProductCreateDto productCreateDto);
    ProductDto getProductById(Long userId,Long productId);
    Page<ProductDto> getAllProducts(Pageable pageable);
    ProductDto updateProduct(ProductDto productDto);
    void deleteProduct(Long id);
    ProductWithStoresDto getProductWithStores(Long productId);
    void addProductToStore(Long productId, Long storeId);
    void addProductToHistory(Long userId,Long productId);
    void addProductToWishlist(Long userId,Long productId);
    Page<ProductDto> getProductsByName(String name,Pageable pageable);
    void deleteProductFromWishlist(Long id,Long productId);
    void deleteProductFromStore(Long productId,Long storeId);
}
