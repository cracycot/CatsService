package services;

import dao.Dao;
import models.Cat;
import utils.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class CatService extends DataBaseConnection implements Dao<Cat> {

    Connection connection = getConnection();

    @Override
    public void create(Cat cat) {
        PreparedStatement preparedStatement  = null;
        String sql = "INSERT INTO cats (name, dateBirth, breed, idOwner, id) VALUES (?, ?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cat.getName());
            preparedStatement.setDate(2, java.sql.Date.valueOf(cat.getDateBirth()));
            preparedStatement.setString(3, cat.getBreed());
            preparedStatement.setInt(4, cat.getIdOwner());
            preparedStatement.setInt(5, cat.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
