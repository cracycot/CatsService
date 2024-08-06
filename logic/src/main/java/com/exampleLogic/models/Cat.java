package com.example.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "cats")
public class Cat {
    private String name;

    @Column(name = "datebirth")
    private LocalDate dateBirth;

    private String breed;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "catsfriends",
            joinColumns = @JoinColumn(name = "cat_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    Set<Cat> friends = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = -1;

    public Cat(String name, LocalDate dateBirth, String breed, Owner  owner, int id) {
        this.name = name;
        this.dateBirth = dateBirth;
        this.breed = breed;
        this.owner = owner;
        this.id = id;
    }

    public Cat() {
    }

    public static class Builder {
        public static Cat cat;

        public Builder() {
            cat = new Cat();
        }

        public Builder name(String name) {
            cat.name = name;
            return this;
        }

        public Builder dateBirth(LocalDate dateBirth) {
            cat.dateBirth = dateBirth;
            return this;
        }

        public Builder breed(String breed) {
            cat.breed = breed;
            return this;
        }

        public Builder owner(Owner owner) {
            cat.owner = owner;
            return this;
        }

        //Подумать над удалением этого метода
        public Builder id(int id) {
            cat.id = id;
            return this;
        }

        public Builder friends(Set<Cat> friends) {
            cat.friends = friends;
            return this;
        }

        public Cat build() {
            return cat;
        }
    }


    @Override
    public String toString() {
        return name + " " + dateBirth + " " + breed + " " + owner.getId() + " " + id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setIdOwner(Owner owner) {
        this.owner = owner;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }


    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }


    public Set<Cat> getFriends() {
        return friends;
    }

    public void setFriends(Set<Cat> friends) {
        this.friends = friends;
    }

    public boolean equalsFriends(Cat cat) {
        HashSet<Integer> idFirstCatFriends = new HashSet<>();
        HashSet<Integer> idSecondCatFriends = new HashSet<>();

        for (Cat friend : this.getFriends()) {
            idFirstCatFriends.add(friend.getId());
        }

        for (Cat friend : cat.getFriends()) {
            idSecondCatFriends.add(friend.getId());
        }

        return idFirstCatFriends.equals(idSecondCatFriends);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Cat cat = (Cat) obj;
        return Objects.equals(this.getId(), cat.getId()) && Objects.equals(this.getBreed(), cat.getBreed()) && Objects.equals(this.getName(), cat.getName()) && getOwner().equals(cat.getOwner());// && Objects.equals(this.getDateBirth(), cat.getDateBirth())  && equalsFriends(cat);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
