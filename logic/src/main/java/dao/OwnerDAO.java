package dao;

import models.Owner;

import java.util.ArrayList;

public interface OwnerDAO {
    void create(Owner owner);

    Owner read(int id);

    ArrayList<Owner> getAll();

    void remove(int id);
}
