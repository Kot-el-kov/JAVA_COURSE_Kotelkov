package com.github.kotelkov.pms.service.impl;

import com.github.kotelkov.pms.dao.RoleRepository;
import com.github.kotelkov.pms.dto.role.RoleCreateDto;
import com.github.kotelkov.pms.dto.role.RoleDto;
import com.github.kotelkov.pms.entity.Role;
import com.github.kotelkov.pms.exception.ResourceNotFoundException;
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
import java.util.Optional;

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
        return mapper.convert(roleRepository.save(mapper.convert(roleCreateDto, Role.class)), RoleDto.class);
    }

    @Transactional
    @Override
    public RoleDto getRoleById(Long id) {
        return Optional.ofNullable(mapper.convert(roleRepository.getById(id),RoleDto.class)).
                orElseThrow(() -> new ResourceNotFoundException("Role with id: "+id+" not found"));
    }

    @Transactional
    @Override
    public RoleDto updateRole(RoleDto roleDto) {
        return mapper.convert(roleRepository.update(mapper.convert(roleDto,Role.class)), RoleDto.class);
    }

    @Transactional
    @Override
    public Page getAllRoles(Pageable pageable) {
        List roleList = roleRepository.getAll(pageable);
        List roleDtoList = Optional.ofNullable(mapper.convert(roleList,RoleDto.class)).
                orElseThrow(()-> new ResourceNotFoundException("Roles not found"));
        return new PageImpl(roleDtoList,pageable.first() ,roleDtoList.size());
    }

    @Transactional
    @Override
    public RoleDto getRoleByName(String name) {
        return mapper.convert(roleRepository.getRoleByName(name),RoleDto.class);
    }
}
