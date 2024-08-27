package com.exampleLogic.models;


import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import javax.sql.rowset.Joinable;
import java.util.Collection;

@Entity
public class User {
    @Id
    private Long id;

    String name;

    private String username;
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    // Конструкторы, геттеры и сеттеры...

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    @OneToOne(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private Owner owner;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
