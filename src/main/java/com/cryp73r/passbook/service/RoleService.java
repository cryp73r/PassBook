package com.cryp73r.passbook.service;

import com.cryp73r.passbook.model.Role;
import com.cryp73r.passbook.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public RoleService() {}

    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    public Role getRole(String roleName) {
        return roleRepository.findByRoleName(roleName).orElseThrow(() -> new RuntimeException("Role not found"));
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
