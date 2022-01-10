package com.github.kotelkov.pms.controller;

import com.github.kotelkov.pms.dto.role.RoleCreateDto;
import com.github.kotelkov.pms.dto.role.RoleDto;
import com.github.kotelkov.pms.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.data.domain.Sort.Direction.ASC;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public RoleDto createRole(@Valid @RequestBody RoleCreateDto roleCreateDto){
        return roleService.createRole(roleCreateDto);
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping(value = "/{roleId}")
    public RoleDto getRoleById(@PathVariable Long roleId) {
        return roleService.getRoleById(roleId);
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping
    public Page<RoleDto> getAllRoles(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                     @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
                                     @RequestParam(value = "sort", defaultValue = "id", required = false) String sort) {
        return roleService.getAllRoles(PageRequest.of(page, size, ASC, sort));
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @PutMapping
    public RoleDto updateRole(@Valid @RequestBody RoleDto roleDto) {
        return roleService.updateRole(roleDto);
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping("/name/{name}")
    public RoleDto getRoleByName(@PathVariable String name){
        return roleService.getRoleByName(name);
    }
}
