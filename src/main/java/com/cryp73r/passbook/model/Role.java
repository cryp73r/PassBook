package com.cryp73r.passbook.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "DEPOT_ROLE")
public class Role {
    @Id
    @GeneratedValue
    @Column(name = "ROWID_ROLE")
    private long rowIdRole;

    @Column(name = "ROLE_NAME", nullable = false, unique = true)
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    Set<User> users;

    public Role() {}

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
