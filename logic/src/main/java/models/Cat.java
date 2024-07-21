package models;

import jakarta.persistence.*;
import org.hibernate.annotations.CollectionId;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Set;

@Entity
@Table(name = "cats")
public class Cat {
    private String name;

    @Column(name = "datebirth")
    private LocalDate dateBirth;
    private String breed;

    @Column(name = "idowner")
    private Integer idOwner;
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "catsfriends",
//            joinColumns = @JoinColumn(name = "cat_id"),
//            inverseJoinColumns = @JoinColumn(name = "friend_id")
//    )
//    Set<Cat> friends;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Cat(String name, LocalDate dateBirth, String breed, int idOwner, int id) {
        this.name = name;
        this.dateBirth = dateBirth;
        this.breed = breed;
        this.idOwner = idOwner;
        this.id = id;
    }

    public Cat() {
    }

    public static class Builder {
        public static Cat cat;

        public Builder() {
            cat = new Cat();
            // проблема с БД
//            cat.id = idGenerate;
//            idGenerate += 1;
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

        public Builder idOwner(int idOwner) {
            cat.idOwner = idOwner;
            return this;
        }

        //Подумать над удалением этого метода
        public Builder id(int id) {
            cat.id = id;
            return this;
        }

//        public Builder friends(Set<Cat> friends) {
//            cat.friends = friends;
//            return this;
//        }

        public Cat build() {
            return cat;
        }
    }


    @Override
    public String toString() {
        return name + " " + dateBirth + " " + breed + " " + idOwner + " " + id;
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

    public int getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(int idOwner) {
        this.idOwner = idOwner;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }


    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }


//        public Set<Cat> getFriends() {
//            return friends;
//        }
//
//        public void setFriends(Set<Cat> friends) {
//            this.friends = friends;
//        }
}
