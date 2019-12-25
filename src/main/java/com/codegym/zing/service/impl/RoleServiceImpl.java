package com.codegym.zing.service.impl;

import com.codegym.zing.model.Role;
import com.codegym.zing.repository.RoleRepository;
import com.codegym.zing.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findRoleByName(String roleName) {
        return roleRepository.findRoleByName(roleName);
    }

    @Override
    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }
}
