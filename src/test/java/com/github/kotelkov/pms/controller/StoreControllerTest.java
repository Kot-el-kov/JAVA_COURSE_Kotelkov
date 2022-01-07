package com.github.kotelkov.pms.controller;

import com.github.kotelkov.pms.WebApplicationTest;
import com.github.kotelkov.pms.dao.ProductRepository;
import com.github.kotelkov.pms.dao.StoreRepository;
import com.github.kotelkov.pms.entity.Product;
import com.github.kotelkov.pms.entity.Store;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class StoreControllerTest extends WebApplicationTest {

    @Autowired
    StoreRepository storeRepository;
    @Autowired
    ProductRepository productRepository;

    @Test
    void testCreateStore() throws Exception {
        Pageable pageable = PageRequest.of(0, 10, ASC,"id");
        Assertions.assertEquals(0, storeRepository.getAll(pageable).size());

        final String storeDtoJson =
                """  
                {
                   "name": "name",
                   "address":"address"
                }
                """;

        mockMvc.perform(post("/stores/").
                contentType(MediaType.APPLICATION_JSON).
                accept(MediaType.APPLICATION_JSON).
                content(storeDtoJson)).
                andDo(print()).
                andExpect(status().is2xxSuccessful()).
                andExpect(jsonPath("$.id").exists()).
                andExpect(jsonPath("$.name").value("name"));

        assertNotNull(storeRepository.getAll(pageable));
    }

    @Test
    void testGetStoreById() throws Exception {
        final Store store = storeRepository.save(Store.builder().name("name").address("address").build());
        mockMvc.perform(get("/stores/"+store.getId())).
                andExpect(status().is2xxSuccessful()).
                andExpect(jsonPath("$.id").value(store.getId())).
                andExpect(jsonPath("$.name").value(store.getName()));
    }

    @Test
    void testGetAllStores() throws Exception {
        final Store store = storeRepository.save(Store.builder().name("name").address("address").build());
        mockMvc.perform(get("/stores")).
                andExpect(status().is2xxSuccessful());
    }

    @Test
    void testUpdateStore() throws Exception {
        final Store store = storeRepository.save(Store.builder().name("name").address("address").build());

        final String storeUpdatedDto = String.format("""
                {
                   "name": "updated",
                   "address":"address",
                   "id": %s
                }
                """, store.getId());

        mockMvc.perform(put("/stores/").
                contentType(MediaType.APPLICATION_JSON).
                content(storeUpdatedDto)).
                andExpect(status().is2xxSuccessful()).
                andDo(print()).
                andExpect(jsonPath("$.id").value(store.getId())).
                andExpect(jsonPath("$.name").value("updated"));
    }

    @Test
    void testDeleteStore() throws Exception {
        final Store store = storeRepository.save(Store.builder().name("name").address("address").build());

        mockMvc.perform(delete("/stores/" + store.getId())).
                andExpect(status().is2xxSuccessful());

        final Store store1 = storeRepository.getById(store.getId());

        assertNull(store1);
    }

    @Test
    void testGetStoreWithProducts() throws Exception {
        final Store store = storeRepository.save(Store.builder().name("name").address("address").build());
        final Product product = productRepository.save(Product.builder().name("name").build());
        mockMvc.perform(post("/products/store/"+product.getId()+"/"+store.getId())).
                andExpect(status().isOk());
        mockMvc.perform(get("/stores/products/"+store.getId())).
                andExpect(status().is2xxSuccessful());
        mockMvc.perform(delete("/products/store/"+product.getId()+"/"+store.getId())).
                andExpect(status().isOk());
    }

    @Test
    void testGetStoreByName() throws Exception {
        final Store store = storeRepository.save(Store.builder().name("name").address("address").build());
        mockMvc.perform(get("/stores/name/name")).
                andExpect(status().is2xxSuccessful()).
                andExpect(jsonPath("$.id").value(store.getId())).
                andExpect(jsonPath("$.name").value(store.getName()));
    }
}