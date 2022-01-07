package com.github.kotelkov.pms.dao.impl;

import com.github.kotelkov.pms.BaseRepositoryTest;
import com.github.kotelkov.pms.dao.ProductRepository;
import com.github.kotelkov.pms.entity.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.domain.Sort.Direction.ASC;

@ContextConfiguration(classes = ProductRepositoryImpl.class)
public class ProductRepositoryTest extends BaseRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    private final Product product = Product.builder().id(1L).name("name").price(100).description("description").build();
    private final Product productCreate = Product.builder().name("name").price(100).description("description").build();


    @BeforeEach
    void init(){
        productRepository.save(productCreate);
    }

    @Test
    public void testGetProductsByName() {
        List<Product> resultList = productRepository.getProductsByName(productCreate.getName(),PageRequest.of(0, 10, ASC,"id"));
        assertEquals(1,resultList.size());
        Assertions.assertEquals(product.getId(), resultList.get(0).getId());
        Assertions.assertEquals(product.getName(), resultList.get(0).getName());
    }

    @Test
    public void testGetById() {
        Product result = productRepository.getById(product.getId());
        Assertions.assertEquals(product.getId(), result.getId());
        Assertions.assertEquals(product.getName(), result.getName());
    }

    @Test
    public void testGetAll() {
        List<Product> result = productRepository.getAll(PageRequest.of(0, 10, ASC,"id"));
        Assertions.assertEquals(1,result.size());
        Assertions.assertEquals(product.getId(), result.get(0).getId());
        Assertions.assertEquals(product.getName(), result.get(0).getName());
    }

    @Test
    public void testDelete() {
        productRepository.delete(product.getId());
        Product result = productRepository.getById(product.getId());
        assertNull(result);
    }

    @Test
    public void testDeleteProductFromWishlist() {
        productRepository.deleteProductFromWishlist(1L, 1L);
    }

    @Test
    public void testDeleteProductFromStore(){
        productRepository.deleteProductFromStore(1L, 1L);
    }

    @Test
    public void testGetProductWithStores(){
        Product result = productRepository.getProductWithStores(product.getId());
        assertEquals(product.getId(),result.getId());
        assertEquals(product.getPrice(),result.getPrice());
        assertEquals(product.getDescription(),result.getDescription());
        assertEquals(0,result.getStores().size());
    }
}
