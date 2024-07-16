package models;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Locale;

public class Owner {
    private String name;
    private LocalDate dateBirth;
    private static int idGenerate = 0;
    private int id;
    private HashMap<Integer, Cat> ownersCats;

    public static class Builder {
        public static Owner owner;

        public Builder() {
            owner = new Owner();
//            owner.id = idGenerate;
//            idGenerate += 1;
        }

        public Builder name(String name) {
            owner.name = name;
            return this;
        }

        public Builder dateBirth(LocalDate dateBirth) {
            owner.dateBirth = dateBirth;
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

}
