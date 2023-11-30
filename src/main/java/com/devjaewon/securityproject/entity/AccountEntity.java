package com.devjaewon.securityproject.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Table;

@Entity(name = "account")
@Table(name = "account")
public class AccountEntity {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Nonnull
    @ManyToAny(fetch = FetchType.LAZY)
    @JoinTable(name = "account_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleEntity> role = new ArrayList<RoleEntity>();

    public AccountEntity() {
    }

    public AccountEntity(String email, String password, List<RoleEntity> roles) {
        this.email = email;
        this.password = password;

        for (RoleEntity role : roles) {
            this.role.add(role);
        }
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RoleEntity> getRole() {
        return this.role;
    }

    public void setRole(List<RoleEntity> roles) {
        for (RoleEntity role : roles) {
            this.role.add(role);
        }
    }

}
