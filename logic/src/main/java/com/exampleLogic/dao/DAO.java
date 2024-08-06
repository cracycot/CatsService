package com.example.dao;

import com.example.exceptions.ObjectNotFoundException;

import java.sql.SQLException;

public interface DAO<T> { // Наследование от JPARepository даст 18 новых методов
    void create(T t) throws SQLException;
    T read(int id) throws SQLException, ObjectNotFoundException;

//    Collection<T> getAll() throws SQLException; ??? скорее всего в service
    void update(T t) throws ObjectNotFoundException;
    void remove(int id) throws SQLException;
}
