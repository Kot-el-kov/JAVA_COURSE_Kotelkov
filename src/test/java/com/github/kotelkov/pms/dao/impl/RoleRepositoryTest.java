package com.github.kotelkov.pms.dao.impl;

import com.github.kotelkov.pms.BaseRepositoryTest;
import com.github.kotelkov.pms.dao.RoleRepository;
import com.github.kotelkov.pms.entity.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;

@ContextConfiguration(classes = RoleRepositoryImpl.class)
public class RoleRepositoryTest extends BaseRepositoryTest {

    @Autowired
    RoleRepository roleRepository;

    private final Role role = Role.builder().id(1L).name("name").build();
    private final Role roleCreate = Role.builder().name("name").build();

    @BeforeEach
    void init(){
        roleRepository.save(roleCreate);
    }

    @Test
    void testGetRoleByName() {
        Role result = roleRepository.getRoleByName("name");
        Assertions.assertEquals(role.getId(),result.getId());
        Assertions.assertEquals(role.getName(), result.getName());
    }

    @Test
    void testGetById() {
        Role result = roleRepository.getById(role.getId());
        Assertions.assertEquals(role.getId(), result.getId());
        Assertions.assertEquals(role.getName(), result.getName());
    }

    @Test
    void testGetAll() {
        List<Role> result = roleRepository.getAll(PageRequest.of(0, 10, ASC,"id"));
        Assertions.assertEquals(1,result.size());
        Assertions.assertEquals(role.getId(), result.get(0).getId());
        Assertions.assertEquals(role.getName(), result.get(0).getName());
    }

    @Test
    void testDelete() {
        roleRepository.delete(role.getId());
        Role result = roleRepository.getById(role.getId());
        Assertions.assertNull(result);
    }
}
