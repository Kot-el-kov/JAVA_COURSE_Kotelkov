package com.github.kotelkov.pms.service.impl;

import com.github.kotelkov.pms.dao.RoleRepository;
import com.github.kotelkov.pms.dto.role.RoleCreateDto;
import com.github.kotelkov.pms.dto.role.RoleDto;
import com.github.kotelkov.pms.entity.Role;
import com.github.kotelkov.pms.mapper.Mapper;
import com.github.kotelkov.pms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private Mapper mapper;

    @Transactional
    @Override
    public RoleDto createRole(RoleCreateDto roleCreateDto) {
        roleCreateDto.setName(roleCreateDto.getName().toUpperCase(Locale.ROOT));
        return (RoleDto) mapper.convertToDto(roleRepository.save((Role)
                mapper.convertToModel(roleCreateDto, Role.class)), RoleDto.class);
    }

    @Transactional
    @Override
    public RoleDto getRoleById(Long id) {
        return (RoleDto) mapper.convertToDto(roleRepository.getById(id),RoleDto.class);
    }

    @Transactional
    @Override
    public RoleDto updateRole(RoleDto roleDto) {
        return (RoleDto) mapper.convertToDto(roleRepository.update((Role)
                mapper.convertToModel(roleDto,Role.class)), RoleDto.class);
    }

    @Transactional
    @Override
    public Page getAllRoles(Pageable pageable) {
        List roleDtoList = mapper.convertListToDtoList(roleRepository.getAll(pageable),RoleDto.class);
        return new PageImpl(roleDtoList,pageable.first() ,roleDtoList.size());
    }

    @Transactional
    @Override
    public RoleDto getRoleByName(String name) {
        return (RoleDto) mapper.convertToDto(roleRepository.getRoleByName(name),RoleDto.class);
    }
}
