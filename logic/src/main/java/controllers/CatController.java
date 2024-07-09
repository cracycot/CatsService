package controllers;

import dao.CatDAO;
import dao.Dao;
import models.Cat;

import java.util.ArrayList;
import java.util.Optional;

public class CatController implements Dao<Cat> {


    @Override
    public void create(Cat cat) {


    }

    @Override
    public Optional<Cat> read(int id) {
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
