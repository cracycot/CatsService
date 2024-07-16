package dao;

import models.Owner;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OwnerDAO {
    void create(Owner owner);

    Owner read(int id) throws SQLException;

    ArrayList<Owner> getAll() throws SQLException;

    void remove(int id) throws SQLException;
}
