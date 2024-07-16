package services;

import dao.OwnerDAO;
import models.Cat;
import models.Owner;
import utils.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class OwnerService extends DataBaseConnection implements OwnerDAO {
    Connection connection = getConnection();
    @Override
    public void create(Owner owner) {
        PreparedStatement preparedStatement  = null;
        String sql = "INSERT INTO owners (name, dateBirth, id) VALUES (?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, owner.getName());
            preparedStatement.setDate(2, java.sql.Date.valueOf(owner.getDateBirth()));
            preparedStatement.setInt(3, owner.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Owner read(int id) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM owners WHERE id = ?";
        Owner owner = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String name = rs.getString(1);
                LocalDate dateBirth = rs.getDate(2).toLocalDate();
                owner = new Owner.Builder().name(name)
                        .dateBirth(dateBirth)
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
        return owner;
    }

    @Override
    public ArrayList<Owner> getAll()   throws SQLException {
        Connection connection = getConnection();
        PreparedStatement prepareStatement = null;
        String sql = "SELECT * FROM owners";
        ArrayList<Owner> owners = new ArrayList<>();
        try {
            prepareStatement = connection.prepareStatement(sql);
            ResultSet rs = prepareStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString(1);
                LocalDate dateBirth = rs.getDate(2).toLocalDate();
                int id = rs.getInt(3);
                Owner owner = new Owner.Builder().name(name)
                        .dateBirth(dateBirth)
                        .id(id)
                        .build();
                owners.add(owner);
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
        return owners;
    }
    @Override
    public void remove(int id) {

    }
}
