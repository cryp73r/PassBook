package com.cryp73r.passbook.controller;

import com.cryp73r.passbook.model.Role;
import com.cryp73r.passbook.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping(value = "/role", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Role addRole(@RequestBody Role role) {
        return roleService.addRole(role);
    }

    @GetMapping(value = "/role/{roleName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Role> getRole(@PathVariable(required = false) String roleName) {
        if (roleName != null) return List.of(roleService.getRole(roleName));
        return roleService.getAllRoles();
    }
}
