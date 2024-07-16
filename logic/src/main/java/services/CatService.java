package services;

import dao.Dao;
import models.Cat;
import utils.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CatService extends DataBaseConnection implements Dao<Cat> {

    @Override
    public void create(Cat cat) throws SQLException {
        Connection connection = getConnection();
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
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public Cat read(int id) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM cats WHERE id = ?";
        Cat cat = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String name = rs.getString(1);
                LocalDate dateBirth = rs.getDate(2).toLocalDate();
                String breed = rs.getString(3);
                int idOwner = rs.getInt(4);
                cat = new Cat.Builder().name(name)
                        .dateBirth(dateBirth)
                        .breed(breed)
                        .idOwner(idOwner)
                        .id(id)
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return cat;
    }

    @Override
    public ArrayList<Cat> getAll() {
        return null;
    }

    @Override
    public void remove(int id) {

    }
}
