package com.github.kotelkov.pms.dao;

import com.github.kotelkov.pms.entity.Role;

public interface RoleRepository extends GenericDao<Role, Long> {
    Role getRoleByName(String name);
}
