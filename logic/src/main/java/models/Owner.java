package models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

@Entity
public class Owner {
    private String name;
    private LocalDate dateBirth;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable (
        name = "cats",
        joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "owner")
    )
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
            idSecondOwnerCats.add(owner.getId());
        }

        return idFirstOwnerCats.equals(idSecondOwnerCats);
    }
    public boolean equals(Owner owner) {
        return Objects.equals(this.getId(), owner.getId()) && Objects.equals(this.getName(), owner.getName()) && Objects.equals(this.getDateBirth(), owner.getDateBirth())  && equalsCats(owner);
    }

}
