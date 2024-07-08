package controllers;

import dao.CatDAO;
import models.Cat;

import java.util.ArrayList;

public class CatController implements CatDAO {


    @Override
    public void create(Cat cat) {

    }

    @Override
    public Cat read(int id) {
        return null;
    }

    @Override
    public ArrayList<Cat> getAll() {
        return null;
    }

    @Override
    public void remove(int id) {

    }
}
