package dao;

import models.Cat;

import java.util.ArrayList;

public interface CatDAO {
    void create(Cat cat);

    Cat read(int id);

    ArrayList<Cat> getAll();

    void remove(int id);

}
