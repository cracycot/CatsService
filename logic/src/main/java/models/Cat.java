package models;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;

public class Cat {
    private String name;
    private LocalDate dateBirth;
    private String breed;
    private int idOwner;
    private static int idGenerate = 0;
    private int id;

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

    private HashMap<Integer, Cat> friends;

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
}
