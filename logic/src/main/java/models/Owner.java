package models;

import java.util.HashMap;

public class Owner {
    private String name;
    private String dateBirth;
    private static int idGenerate;
    private int id;
    private HashMap<Integer, Cat> ownersCats;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }

    public int getId() {
        return id;
    }

}
