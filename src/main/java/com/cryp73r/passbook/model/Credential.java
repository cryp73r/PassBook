package com.cryp73r.passbook.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "DEPOT_CREDENTIAL")
public class Credential {

    @Id
    @GeneratedValue
    @Column(name = "ROWID_CREDENTIAL")
    private Long rowid;

    @CreationTimestamp
    @Column(name = "CREATE_DATE", updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "LAST_UPDATE_DATE")
    private Instant updatedAt;

    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "owner", nullable = false)
    private String owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLT_CODE", referencedColumnName = "PLT_CODE", nullable = false)
    private Platform platform;

    public Credential(String username, String password, Platform platform) {
        this.username = username;
        this.password = password;
        this.platform = platform;
    }

    public Credential() {

    }

    public Long getRowid() {
        return rowid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Platform getPlatform() {
        return platform;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
