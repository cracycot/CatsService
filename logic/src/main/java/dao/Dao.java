package dao;

import models.Cat;

import java.sql.SQLException;
import java.util.Collection;

public interface Dao<T> {
    void create(T t) throws SQLException;
    T read(int id) throws SQLException;

    Collection<T> getAll() throws SQLException;

    void remove(int id) throws SQLException;
}
