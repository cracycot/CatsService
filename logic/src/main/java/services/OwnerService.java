package services;

import dao.OwnerDAO;
import models.Owner;
import utils.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    public Owner read(int id) {
        return null;
    }

    @Override
    public ArrayList<Owner> getAll() {
        return null;
    }

    @Override
    public void remove(int id) {

    }
}
