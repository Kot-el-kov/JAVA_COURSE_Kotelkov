package com.github.kotelkov.pms.service;

import com.github.kotelkov.pms.dto.role.RoleCreateDto;
import com.github.kotelkov.pms.dto.role.RoleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleService {
    RoleDto createRole(RoleCreateDto roleCreateDto);
    RoleDto getRoleById(Long id);
    RoleDto updateRole(RoleDto roleDto);
    Page<RoleDto> getAllRoles(Pageable pageable);
    RoleDto getRoleByName(String name);
}
