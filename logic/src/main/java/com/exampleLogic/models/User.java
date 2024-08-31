package com.exampleLogic.models;


import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import javax.sql.rowset.Joinable;
import java.util.Collection;

@Entity
@Table(name = "users")
public class User {
    @Id
    private Long id;

    private String name;

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @OneToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

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
