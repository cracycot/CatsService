package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public interface Dao<T> {
    void create(T t) throws SQLException;

    Optional<T> read(int id);

    Collection<T> getAll();

    void remove(int id);
}
