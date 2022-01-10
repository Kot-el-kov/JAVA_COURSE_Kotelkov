package com.github.kotelkov.pms.service.impl;

import com.github.kotelkov.pms.dao.StoreRepository;
import com.github.kotelkov.pms.dto.product.ProductDto;
import com.github.kotelkov.pms.dto.store.StoreCreateDto;
import com.github.kotelkov.pms.dto.store.StoreDto;
import com.github.kotelkov.pms.dto.store.StoreWithProductsDto;
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

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.data.domain.Sort.Direction.ASC;

class StoreServiceImplTest {
    @Mock
    StoreRepository storeRepository;
    @Mock
    Mapper mapper;
    @InjectMocks
    StoreServiceImpl storeServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private final Store store = Store.builder().id(1L).name("name").address("address").build();
    private final StoreDto storeDto =StoreDto.builder().id(1l).name("name").address("address").build();
    private final StoreCreateDto storeCreateDto = StoreCreateDto.builder().name("name").address("address").build();
    private final StoreWithProductsDto storeWithProductsDto = StoreWithProductsDto.builder().
            id(1L).name("name").productsDto(Collections.singletonList(ProductDto.builder().name("name").build())).build();

    @Test
    void testCreateStore() {
        when(storeRepository.save(any())).thenReturn(store);
        when(mapper.convert(storeCreateDto, Store.class)).thenReturn(store);
        when(mapper.convert(store, StoreDto.class)).thenReturn(storeDto);
        StoreDto result = storeServiceImpl.createStore(storeCreateDto);
        Assertions.assertEquals(storeDto, result);
        verify(storeRepository,times(1)).save(store);
    }

    @Test
    void testGetStoreById() {
        when(storeRepository.getById(any())).thenReturn(store);
        when(mapper.convert(store, StoreDto.class)).thenReturn(storeDto);
        StoreDto result = storeServiceImpl.getStoreById(store.getId());
        Assertions.assertEquals(storeDto, result);
        verify(storeRepository,times(1)).getById(store.getId());
    }

    @Test
    void testGetAllStores() {
        when(storeRepository.getAll(any())).thenReturn(Collections.singletonList(store));
        when(mapper.convert(any(), any())).thenReturn(Collections.singletonList(storeDto));
        Pageable pageable = PageRequest.of(0, 10, ASC,"id");
        Page result = storeServiceImpl.getAllStores(pageable);
        Assertions.assertEquals(1, result.getContent().size());
        Assertions.assertEquals(storeDto, result.getContent().get(0));
        verify(storeRepository,times(1)).getAll(pageable);
    }

    @Test
    void testUpdateStore() {
        when(storeRepository.update(any())).thenReturn(store);
        when(mapper.convert(storeDto, Store.class)).thenReturn(store);
        when(mapper.convert(store, StoreDto.class)).thenReturn(storeDto);
        StoreDto result = storeServiceImpl.updateStore(storeDto);
        Assertions.assertEquals(storeDto, result);
        verify(storeRepository,times(1)).update(store);
    }

    @Test
    void testDeleteStore() {
        storeServiceImpl.deleteStore(1L);
        verify(storeRepository,times(1)).delete(1L);
    }

    @Test
    void testGetStoreByName() {
        when(storeRepository.getStoreByName(anyString())).thenReturn(store);
        when(mapper.convert(store, StoreDto.class)).thenReturn(storeDto);
        StoreDto result = storeServiceImpl.getStoreByName(storeDto.getName());
        Assertions.assertEquals(storeDto, result);
        verify(storeRepository,times(1)).getStoreByName(store.getName());
    }

    @Test
    void testGetStoreWithProducts(){
        when(storeRepository.getStoreWithProducts(any())).thenReturn(store);
        when(mapper.convert(store,StoreWithProductsDto.class)).thenReturn(storeWithProductsDto);
        StoreWithProductsDto result = storeServiceImpl.getStoreWithProducts(storeDto.getId());
        Assertions.assertEquals(storeWithProductsDto, result);
        verify(storeRepository,times(1)).getStoreWithProducts(storeDto.getId());
    }
}
