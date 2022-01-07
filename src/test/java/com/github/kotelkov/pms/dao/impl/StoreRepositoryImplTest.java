package com.github.kotelkov.pms.dao.impl;

import com.github.kotelkov.pms.BaseRepositoryTest;
import com.github.kotelkov.pms.dao.StoreRepository;
import com.github.kotelkov.pms.entity.*;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;


@ContextConfiguration(classes = StoreRepositoryImpl.class)
public class StoreRepositoryImplTest extends BaseRepositoryTest {

    @Autowired
    StoreRepository storeRepository;

    private final Store store = Store.builder().id(1L).name("name").address("address").build();
    private final Store storeCreate = Store.builder().name("name").address("address").build();


   @BeforeEach
   void init(){
       storeRepository.save(storeCreate);
   }

    @Test
    void testGetStoreByName() {
        Store result = storeRepository.getStoreByName(storeCreate.getName());
        Assertions.assertEquals(store.getId(),result.getId());
        Assertions.assertEquals(store.getName(),result.getName());
    }

    @Test
    void testGetById() {
        Store result = storeRepository.getById(store.getId());
        Assert.assertEquals(store.getId(),result.getId());
        Assertions.assertEquals(store.getName(),result.getName());
    }

    @Test
    void testUpdate() {
        Store result = storeRepository.update(store);
        Assert.assertEquals(store.getId(),result.getId());
        Assertions.assertEquals(store.getName(),result.getName());
    }

    @Test
    void testDelete() {
        storeRepository.delete(store.getId());
        Store result = storeRepository.getById(store.getId());
        Assertions.assertNull(result);
    }

    @Test
    void testGetAll() {
        List<Store> result = storeRepository.getAll(PageRequest.of(0, 10, ASC,"id"));
        Assertions.assertEquals(1,result.size());
        Assertions.assertEquals(store.getId(), result.get(0).getId());
        Assertions.assertEquals(store.getName(), result.get(0).getName());
    }
}