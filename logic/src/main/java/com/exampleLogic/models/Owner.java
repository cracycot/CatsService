package com.exampleLogic.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "owners")
public class Owner {
    private String name;
    private LocalDate dateBirth;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = -1;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Cat> cats;



    public static class Builder {
        public static Owner owner;

        public Builder() {
            owner = new Owner();
        }

        public Builder name(String name) {
            owner.name = name;
            return this;
        }

        public Builder dateBirth(LocalDate dateBirth) {
            owner.dateBirth = dateBirth;
            return this;
        }

        public Builder cats(Set<Cat> cats) {
            owner.cats = cats;
            return this;
        }

        public Builder id(int id) {
            owner.id = id;
            return this;
        }

        public Owner build() {
            return owner;
        }

    }

    @Override
    public String toString() {
        return name + " " + dateBirth + " " + id;
    }

    public String getName() {
        return name;
    }

    public Set<Cat> getCats() {
        return cats;
    }

    public void setCats(Set<Cat> cats) {
        this.cats = cats;
    }
    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public int getId() {
        return id;
    }

    public boolean equalsCats(Owner owner) {
        HashSet<Integer> idFirstOwnerCats = new HashSet<>();
        HashSet<Integer> idSecondOwnerCats = new HashSet<>();

        for (Cat pets : this.getCats()) {
            idFirstOwnerCats.add(pets.getId());
        }

        for (Cat pets : owner.getCats()) {
            idSecondOwnerCats.add(pets.getId());
        }

        return idFirstOwnerCats.equals(idSecondOwnerCats);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Owner owner = (Owner) obj;
        return Objects.equals(this.getId(), owner.getId()) && Objects.equals(this.getName(), owner.getName()) && Objects.equals(this.getDateBirth(), owner.getDateBirth())  && equalsCats(owner);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
