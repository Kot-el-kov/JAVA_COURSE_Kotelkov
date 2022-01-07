package com.github.kotelkov.pms.dao;

import com.github.kotelkov.pms.entity.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductRepository extends GenericDao<Product, Long>{
    List<Product> getProductsByName(String name, Pageable pageable);
    void addProductToHistory(Long id,Long productId);
    void addProductToWishlist(Long id,Long productId);
    void deleteProductFromWishlist(Long id,Long productId);
    void addProductToStore(Long productId,Long storeId);
    Product getProductWithStores(Long productId);
    void deleteProductFromStore(Long productId,Long storeId);
}
