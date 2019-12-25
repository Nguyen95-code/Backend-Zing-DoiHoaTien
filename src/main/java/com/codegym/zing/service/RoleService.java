package com.codegym.zing.service;

import com.codegym.zing.model.Role;

public interface RoleService {
    Role findRoleByName(String roleName);

    Iterable<Role> findAll();

    void save(Role role);
}
