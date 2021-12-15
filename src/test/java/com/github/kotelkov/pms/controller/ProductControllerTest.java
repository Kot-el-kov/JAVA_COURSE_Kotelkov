package com.github.kotelkov.pms.controller;

import com.github.kotelkov.pms.WebApplicationTest;
import com.github.kotelkov.pms.dao.ProductRepository;
import com.github.kotelkov.pms.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerTest extends WebApplicationTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void productShouldBeCreated() throws Exception {
        assertEquals(0, productRepository.getAll().size());

        final String productDto =
                            """  
                            {
                               "name": "test",
                               "price": "111",
                               "description":"description"
                            }
                            """;
        mockMvc.perform(post("/products/").
                contentType(MediaType.APPLICATION_JSON).
                accept(MediaType.APPLICATION_JSON).
                content(productDto)).
                andDo(print()).
                andExpect(status().is2xxSuccessful()).
                andExpect(jsonPath("$.id").exists());

        assertNotNull(productRepository.getByName("test"));
    }

    @Test
    public void productShouldBeDeletedById() throws Exception {
        final Product product = productRepository.save(Product.builder().name("test").build());

        mockMvc.perform(delete("/products/" + product.getId())).
                andExpect(status().is2xxSuccessful());

        final Product product1 = productRepository.getById(product.getId());

        assertNull(product1);
    }

    @Test
    public void productShouldReturnWithCorrectFields() throws Exception {
        final Product product = productRepository.save(Product.builder().name("test").build());

        mockMvc.perform(get("/products/" + product.getId())).
                andExpect(status().is2xxSuccessful()).
                andExpect(jsonPath("$.id").value(product.getId())).
                andExpect(jsonPath("$.name").value(product.getName()));
    }

    @Test
    public void productNameShouldBeUpdated() throws Exception {
        final Product product = productRepository.save(Product.builder().name("test").build());

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

        final Product product1 = productRepository.getByName("updated");
        assertEquals(product1.getId(), product.getId());
    }

    @Test
    public void shouldReturnErrorTextWhenProductNotExists() throws Exception {
        mockMvc.perform(get("/products/2")).
                andExpect(status().isNotFound()).
                andExpect(jsonPath("$.message").
                        value("Product with id: 2 not found"));
    }

}
