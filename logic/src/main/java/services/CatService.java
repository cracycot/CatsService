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
        PreparedStatement prepareStatement  = null;
        String sql = "INSERT INTO cats (name, dateBirth, breed, idOwner, id) VALUES (?, ?, ?, ?, ?)";
        try {
            prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setString(1, cat.getName());
            prepareStatement.setDate(2, java.sql.Date.valueOf(cat.getDateBirth()));
            prepareStatement.setString(3, cat.getBreed());
            prepareStatement.setInt(4, cat.getIdOwner());
            prepareStatement.setInt(5, cat.getId());

            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (prepareStatement != null) {
                prepareStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public Cat read(int id) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement prepareStatement = null;
        String sql = "SELECT * FROM cats WHERE id = ?";
        Cat cat = null;
        try {
            prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setInt(1, id);
            ResultSet rs = prepareStatement.executeQuery();
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
            if (prepareStatement != null) {
                prepareStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return cat;
    }

    @Override
    public ArrayList<Cat> getAll() throws SQLException {
        Connection connection = getConnection();
        PreparedStatement prepareStatement = null;
        String sql = "SELECT * FROM cats";
        ArrayList<Cat> cats = new ArrayList<>();
        try {
            prepareStatement = connection.prepareStatement(sql);
            ResultSet rs = prepareStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString(1);
                LocalDate dateBirth = rs.getDate(2).toLocalDate();
                String breed = rs.getString(3);
                int idOwner = rs.getInt(4);
                int id = rs.getInt(5);
                Cat cat = new Cat.Builder().name(name)
                        .dateBirth(dateBirth)
                        .breed(breed)
                        .idOwner(idOwner)
                        .id(id)
                        .build();
                cats.add(cat);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (prepareStatement != null) {
                prepareStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return cats;
    }

    @Override
    public void remove(int id) {

    }
}
