package com.cryp73r.passbook.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "DEPOT_USER")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "ROWID_USER")
    private long rowIdUser;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "DISPLAY_NAME")
    private String displayName;

    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "ENABLED", nullable = false, columnDefinition = "true")
    private boolean enabled;

    @CreationTimestamp
    @Column(name = "CREATE_DATE", updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "LAST_UPDATE_DATE")
    private Instant updatedAt;

    @ManyToMany(fetch = FetchType.EAGER)
            @JoinTable(name = "DEPOT_USER_ROLE_REL",
                    joinColumns = @JoinColumn(name = "ROWID_USER"),
                    inverseJoinColumns = @JoinColumn(name = "ROWID_ROLE")
            )
    Set<Role> roles = new HashSet<>();

    public User() {}

    public User(String firstName, String username, String password) {
        this.firstName = firstName;
        this.displayName = firstName.trim();
        this.username = username;
        this.password = password;
    }

    public User(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.displayName = firstName.trim() + " " + lastName.trim();
        this.username = username;
        this.password = password;
    }

    public User(String firstName, String middleName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.displayName = firstName.trim() + " " + middleName.trim() + " " + lastName.trim();
        this.username = username;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String firstName, String middleName, String lastName) {
        if (middleName != null) this.displayName = firstName.trim() + " " + middleName.trim() + " " + lastName.trim();
        else this.displayName = firstName.trim() + " " + lastName.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public Set<Role> getRoles() {return roles;}

    public void removeRole(Role role) {
        roles.remove(role);
    }

    public void removeAllRoles() {
        roles.clear();
    }
}
