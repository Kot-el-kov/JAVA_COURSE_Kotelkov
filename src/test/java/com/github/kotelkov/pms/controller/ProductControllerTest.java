package com.github.kotelkov.pms.controller;

import com.github.kotelkov.pms.WebApplicationTest;
import com.github.kotelkov.pms.dao.ProductRepository;
import com.github.kotelkov.pms.dao.StoreRepository;
import com.github.kotelkov.pms.entity.Product;
import com.github.kotelkov.pms.entity.Store;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProductControllerTest extends WebApplicationTest {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StoreRepository storeRepository;

    private final Product productCreate = Product.builder().name("name").price(100).description("description").build();

    @Test
    void testCreateProduct() throws Exception {
        Pageable pageable = PageRequest.of(0, 10, ASC,"id");
        assertEquals(0, productRepository.getAll(pageable).size());

        final String productDtoJson =
                """  
                {
                   "name": "name",
                   "price": "100",
                   "description":"description"
                }
                """;

        mockMvc.perform(post("/products/").
                contentType(MediaType.APPLICATION_JSON).
                accept(MediaType.APPLICATION_JSON).
                content(productDtoJson)).
                andDo(print()).
                andExpect(status().is2xxSuccessful()).
                andExpect(jsonPath("$.id").exists()).
                andExpect(jsonPath("$.name").value(productCreate.getName()));

        assertNotNull(productRepository.getAll(pageable));
    }

    @Test
    void testGetAllProducts() throws Exception {
        final Product product = productRepository.save(productCreate);
        mockMvc.perform(get("/products/")).
                andExpect(status().is2xxSuccessful());
    }

    @Test
    void testUpdateProduct() throws Exception {
        final Product product = productRepository.save(productCreate);

        final String productUpdateDto = String.format("""
                {
                   "name": "updated",
                   "id": %s
                }
                """, product.getId());

        mockMvc.perform(put("/products/").
                contentType(MediaType.APPLICATION_JSON).
                content(productUpdateDto)).
                andExpect(status().is2xxSuccessful()).
                andDo(print()).
                andExpect(jsonPath("$.id").value(product.getId())).
                andExpect(jsonPath("$.name").value("updated"));
    }

    @Test
    void testDeleteProduct() throws Exception {
        final Product product = productRepository.save(productCreate);

        mockMvc.perform(delete("/products/" + product.getId())).
                andExpect(status().is2xxSuccessful());

        final Product product1 = productRepository.getById(product.getId());

        assertNull(product1);
    }

    @Test
    void testAddProductToStore() throws Exception {
        final Product product = productRepository.save(productCreate);
        final Store store = storeRepository.save(Store.builder().name("name").address("address").build());
        mockMvc.perform(post("/products/store/"+product.getId()+"/"+store.getId())).
                andExpect(status().isOk());
        mockMvc.perform(delete("/products/store/"+product.getId()+"/"+store.getId())).
                andExpect(status().isOk());
    }

    @Test
    void testGetProductWithStores() throws Exception {
        final Product product = productRepository.save(productCreate);
        final Store store = storeRepository.save(Store.builder().name("name").address("address").build());
        mockMvc.perform(post("/products/store/"+product.getId()+"/"+store.getId())).
                andExpect(status().isOk());
        mockMvc.perform(get("/products/stores/"+product.getId())).
                andExpect(status().is2xxSuccessful());
        mockMvc.perform(delete("/products/store/"+product.getId()+"/"+store.getId())).
                andExpect(status().isOk());
    }

    @Test
    void testGetProductsByName() throws Exception {
        final Product product = productRepository.save(productCreate);

        mockMvc.perform(get("/products/name/"+product.getName())).
                andExpect(status().is2xxSuccessful());
    }


    @Test
    void testDeleteProductFromStore() throws Exception {
        final Product product = productRepository.save(productCreate);
        final Store store = storeRepository.save(Store.builder().name("name").address("address").build());
        mockMvc.perform(post("/products/store/"+product.getId()+"/"+store.getId())).
                andExpect(status().isOk());
        mockMvc.perform(delete("/products/store/"+product.getId()+"/"+store.getId())).
                andExpect(status().isOk());
    }
}
