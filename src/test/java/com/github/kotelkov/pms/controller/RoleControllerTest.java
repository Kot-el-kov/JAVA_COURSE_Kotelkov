package com.github.kotelkov.pms.controller;

import com.github.kotelkov.pms.WebApplicationTest;
import com.github.kotelkov.pms.dao.RoleRepository;
import com.github.kotelkov.pms.entity.Role;

import static org.junit.Assert.assertNotNull;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;

class RoleControllerTest extends WebApplicationTest {

    @Autowired
    RoleRepository roleRepository;

    @Test
    void testCreateRole() throws Exception {
        Pageable pageable = PageRequest.of(0, 10, ASC,"id");
        Assertions.assertEquals(0, roleRepository.getAll(pageable).size());

        final String roleDtoJson =
                """  
                {
                   "name": "name"
                }
                """;

        mockMvc.perform(post("/roles/").
                contentType(MediaType.APPLICATION_JSON).
                accept(MediaType.APPLICATION_JSON).
                content(roleDtoJson)).
                andDo(print()).
                andExpect(status().is2xxSuccessful()).
                andExpect(jsonPath("$.id").exists()).
                andExpect(jsonPath("$.name").value("NAME"));

        assertNotNull(roleRepository.getAll(pageable));
    }

    @Test
    void testGetRoleById() throws Exception {
        final Role role = roleRepository.save(Role.builder().name("name").build());
        mockMvc.perform(get("/roles/"+role.getId())).
                andExpect(status().is2xxSuccessful()).
                andExpect(jsonPath("$.id").value(role.getId())).
                andExpect(jsonPath("$.name").value(role.getName()));
    }

    @Test
    void testGetAllRoles() throws Exception {
        final Role role = roleRepository.save(Role.builder().name("name").build());
        mockMvc.perform(get("/roles")).
                andExpect(status().is2xxSuccessful());
    }

    @Test
    void testUpdateRole() throws Exception {
        final Role role = roleRepository.save(Role.builder().name("name").build());

        final String roleUpdatedDto = String.format("""
                {
                   "name": "updated",
                   "id": %s
                }
                """, role.getId());

        mockMvc.perform(put("/roles/").
                contentType(MediaType.APPLICATION_JSON).
                content(roleUpdatedDto)).
                andExpect(status().is2xxSuccessful()).
                andDo(print()).
                andExpect(jsonPath("$.id").value(role.getId())).
                andExpect(jsonPath("$.name").value("updated"));
    }

    @Test
    void testGetRoleByName() throws Exception {
        final Role role = roleRepository.save(Role.builder().name("name").build());
        mockMvc.perform(get("/roles/name/name")).
                andExpect(status().is2xxSuccessful()).
                andExpect(jsonPath("$.id").value(role.getId())).
                andExpect(jsonPath("$.name").value(role.getName()));
    }
}