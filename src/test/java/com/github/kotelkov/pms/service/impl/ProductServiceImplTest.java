package com.github.kotelkov.pms.service.impl;

import com.github.kotelkov.pms.dao.ProductRepository;
import com.github.kotelkov.pms.dto.product.ProductCreateDto;
import com.github.kotelkov.pms.dto.product.ProductDto;
import com.github.kotelkov.pms.dto.product.ProductWithStoresDto;
import com.github.kotelkov.pms.dto.store.StoreDto;
import com.github.kotelkov.pms.entity.Product;
import com.github.kotelkov.pms.entity.Store;
import com.github.kotelkov.pms.mapper.Mapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.data.domain.Sort.Direction.ASC;

class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;
    @Mock
    Mapper mapper;
    @InjectMocks
    ProductServiceImpl productServiceImpl;

    private final Product product = Product.builder().id(1L).name("name").price(100).description("description").
            stores(Collections.singletonList(Store.builder().name("name").build())).build();
    private final ProductDto productDto = ProductDto.builder().id(1L).name("name").price(100).description("description").build();
    private final ProductCreateDto productCreateDto = ProductCreateDto.builder().name("name").price(100).description("description").build();
    private final ProductWithStoresDto productWithStoresDto = ProductWithStoresDto.builder().
            id(1L).name("name").storesDto(Collections.singletonList(StoreDto.builder().name("name").build())).build();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateProduct() {
        when(productRepository.save(any())).thenReturn(product);
        when(mapper.convertToModel(any(), any())).thenReturn(product);
        when(mapper.convertToDto(any(), any())).thenReturn(productDto);
        ProductDto result = productServiceImpl.createProduct(productCreateDto);
        Assertions.assertEquals(productDto, result);
        verify(productRepository,times(1)).save(product);
    }

    @Test
    void testGetProductById() {
        when(productRepository.getById(any())).thenReturn(product);
        when(mapper.convertToDto(any(), any())).thenReturn(productDto);
        ProductDto result = productServiceImpl.getProductById(1L,productDto.getId());
        Assertions.assertEquals(productDto, result);
        verify(productRepository,times(1)).getById(product.getId());
        verify(productRepository,times(1)).addProductToHistory(1L,productDto.getId());
    }

    @Test
    void testGetAllProducts() {
        when(productRepository.getAll(any())).thenReturn(Arrays.asList(product));
        when(mapper.convertListToDtoList(any(), any())).thenReturn(Arrays.asList(productDto));
        Pageable pageable = PageRequest.of(0, 10, ASC,"id");
        Page result = productServiceImpl.getAllProducts(pageable);
        Assertions.assertEquals(productDto, result.getContent().get(0));
        verify(productRepository,times(1)).getAll(pageable);
    }

    @Test
    void testGetProductWithStores(){
        when(productRepository.getProductWithStores(anyLong())).thenReturn(product);
        when(mapper.convertToDto(any(),any())).thenReturn(productWithStoresDto);
        ProductWithStoresDto result = productServiceImpl.getProductWithStores(productDto.getId());
        Assertions.assertEquals(productWithStoresDto, result);
        verify(productRepository,times(1)).getProductWithStores(productDto.getId());
    }

    @Test
    void testUpdateProduct() {
        when(productRepository.update(any())).thenReturn(product);
        when(mapper.convertToModel(any(), any())).thenReturn(product);
        when(mapper.convertToDto(any(), any())).thenReturn(productDto);
        ProductDto result = productServiceImpl.updateProduct(productDto);
        Assertions.assertEquals(productDto, result);
        verify(productRepository,times(1)).update(product);
    }

    @Test
    void testDeleteProduct() {
        when(mapper.convertToDto(any(), any())).thenReturn(productDto);
        ProductDto productDto = productServiceImpl.createProduct(productCreateDto);
        productServiceImpl.deleteProduct(productDto.getId());
        verify(productRepository,times(1)).delete(productDto.getId());
    }

    @Test
    void testAddProductToStore() {
        productServiceImpl.addProductToStore(1L, 1L);
        verify(productRepository,times(1)).addProductToStore(1L,1L);
    }

    @Test
    void testAddProductToHistory() {
        productServiceImpl.addProductToHistory(1L, 1L);
        verify(productRepository,times(1)).addProductToHistory(1L,1L);
    }

    @Test
    void testAddProductToWishlist() {
        productServiceImpl.addProductToWishlist(1L, 1L);
        verify(productRepository,times(1)).addProductToWishlist(1L,1L);
    }

    @Test
    void testGetProductsByName() {
        when(productRepository.getProductsByName(anyString(), any())).thenReturn(Collections.singletonList(product));
        when(mapper.convertListToDtoList(any(), any())).thenReturn(Collections.singletonList(productDto));
        Pageable pageable = PageRequest.of(0, 10, ASC,"id");
        Page result = productServiceImpl.getProductsByName(productDto.getName(), pageable);
        Assertions.assertEquals(productDto, result.getContent().get(0));
        verify(productRepository,times(1)).getProductsByName(productDto.getName(),pageable);
    }

    @Test
    void testDeleteProductFromWishlist() {
        productServiceImpl.deleteProductFromWishlist(1L, 1L);
        verify(productRepository,times(1)).deleteProductFromWishlist(1L,1L);

    }

    @Test
    void testDeleteProductFromStore() {
        productServiceImpl.deleteProductFromStore(1L, 1L);
        verify(productRepository,times(1)).deleteProductFromStore(1L,1L);
    }
}