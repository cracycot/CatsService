package dao;

import java.sql.SQLException;
import java.util.Collection;

public interface DAO<T> {
    void create(T t) throws SQLException;
    T read(int id) throws SQLException;

//    Collection<T> getAll() throws SQLException; ??? скорее всего в service

    void remove(int id) throws SQLException;
}
