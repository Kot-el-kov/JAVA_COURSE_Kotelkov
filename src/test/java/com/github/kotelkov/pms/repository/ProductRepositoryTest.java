package com.github.kotelkov.pms.repository;

import com.github.kotelkov.pms.BaseRepositoryTest;
import com.github.kotelkov.pms.dao.ProductRepository;
import com.github.kotelkov.pms.dao.impl.ProductRepositoryImpl;
import com.github.kotelkov.pms.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import javax.persistence.NoResultException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = ProductRepositoryImpl.class)
public class ProductRepositoryTest extends BaseRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    public void jpaShouldSetIdWhenProductSaved() {
        final Product product = productRepository.save(Product.builder().name("test").build());
        assertNotNull(product.getId());
    }

    @Test
    public void getAllProducts(){
        final String name = "test";
        productRepository.save(Product.builder().name(name).build());
        final List<Product> all = productRepository.getAll();

        assertEquals(false, all.isEmpty());
        final Product product = all.get(0);
        assertEquals("test", product.getName());
        assertNotNull(product.getId());
    }

    @Test
    public void repositoryShouldThrowExceptionWhenProductNotFoundByName() {
        assertThrows(NoResultException.class, () -> { productRepository.getByName("Test-Name"); });
    }
}
