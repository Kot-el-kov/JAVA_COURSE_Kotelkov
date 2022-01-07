package com.github.kotelkov.pms.service.impl;

import com.github.kotelkov.pms.dao.RoleRepository;
import com.github.kotelkov.pms.dto.role.RoleCreateDto;
import com.github.kotelkov.pms.dto.role.RoleDto;
import com.github.kotelkov.pms.entity.Role;
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

import static org.mockito.Mockito.*;
import static org.springframework.data.domain.Sort.Direction.ASC;

class RoleServiceImplTest {
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private Mapper mapper;
    @InjectMocks
    private RoleServiceImpl roleServiceImpl;

    private final Role role = Role.builder().id(1L).name("name").build();
    private final RoleDto roleDto = RoleDto.builder().id(1L).name("name").build();
    private final RoleCreateDto roleCreateDto = RoleCreateDto.builder().name("name").build();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateRole() {
        when(roleRepository.save(any())).thenReturn(role);
        when(mapper.convertToModel(any(), any())).thenReturn(role);
        when(mapper.convertToDto(any(), any())).thenReturn(roleDto);
        RoleDto result = roleServiceImpl.createRole(roleCreateDto);
        Assertions.assertEquals(roleDto, result);
        verify(roleRepository,times(1)).save(role);
    }

    @Test
    void testGetRoleById() {
        when(roleRepository.getById(any())).thenReturn(role);
        when(mapper.convertToDto(any(), any())).thenReturn(roleDto);
        RoleDto result = roleServiceImpl.getRoleById(role.getId());
        Assertions.assertEquals(roleDto, result);
        verify(roleRepository,times(1)).getById(role.getId());
    }

    @Test
    void testUpdateRole() {
        when(roleRepository.update(any())).thenReturn(role);
        when(mapper.convertToModel(any(), any())).thenReturn(role);
        when(mapper.convertToDto(any(), any())).thenReturn(roleDto);
        RoleDto result = roleServiceImpl.updateRole(roleDto);
        Assertions.assertEquals(roleDto, result);
        verify(roleRepository,times(1)).update(role);
    }

    @Test
    void testGetAllRoles() {
        when(roleRepository.getAll(any())).thenReturn(Arrays.asList(role));
        when(mapper.convertListToDtoList(any(), any())).thenReturn(Arrays.asList(roleDto));
        Pageable pageable = PageRequest.of(0, 10, ASC,"id");
        Page result = roleServiceImpl.getAllRoles(pageable);
        Assertions.assertEquals(1, result.getContent().size());
        Assertions.assertEquals(roleDto, result.getContent().get(0));
        verify(roleRepository,times(1)).getAll(pageable);
    }

    @Test
    void testGetRoleByName() {
        when(roleRepository.getRoleByName(anyString())).thenReturn(role);
        when(mapper.convertToDto(any(), any())).thenReturn(roleDto);
        RoleDto result = roleServiceImpl.getRoleByName(role.getName());
        Assertions.assertEquals(roleDto, result);
        verify(roleRepository,times(1)).getRoleByName(role.getName());
    }
}