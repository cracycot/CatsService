package dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public interface Dao<T> {
    void create(T t);

    Optional<T> read(int id);

    Collection<T> getAll();

    void remove(int id);
}
