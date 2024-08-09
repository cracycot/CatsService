package com.exampleLogic.models;


import jakarta.persistence.*;

import javax.sql.rowset.Joinable;

@Entity
public class User {
    @Id
    private Long id;

    String name;

    @OneToOne(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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
