package dao;

import models.Cat;
import models.Owner;
import utils.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class OwnerDAO extends DataBaseConnection implements DAO<Owner> {

    private void setCats(int id, HashMap<Integer, Cat> cats, Connection connection) throws SQLException {
        String sql = "INSERT INTO cats (name, datebirth, breed, idowner, id) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement  = null;
        try {
            for (int i : cats.keySet()) {
                Cat cat = cats.get(i);
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, cat.getName());
                preparedStatement.setDate(2, java.sql.Date.valueOf(cat.getDateBirth()));
                preparedStatement.setString(3, cat.getBreed());
                preparedStatement.setInt(4, id);
                preparedStatement.setInt(5, i);

                preparedStatement.executeUpdate();
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        } // finally вызовется в вызываемом методе

    }

    private HashMap<Integer, Cat> getCats(int idOwner, Connection connection) throws  SQLException {
        String sql = "SELECT * FROM cats WHERE idowner = ?";
        PreparedStatement preparedStatement = null;
        HashMap<Integer, Cat> cats = new HashMap<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idOwner);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString(1);
                LocalDate dateBirth = rs.getDate(2).toLocalDate();
                String breed = rs.getString(3);
                int id = rs.getInt(5);
                Cat cat = new Cat.Builder().name(name)
                        .dateBirth(dateBirth)
                        .breed(breed)
                        .idOwner(idOwner)
                        .id(id)
                        .build();

                cats.put(id, cat);

            }
        }  catch (SQLException e) {
            e.printStackTrace();
        } // finally вызовется в вызываемом методе
        return cats;

    }

    private void delCats(int idOwner, Connection connection) throws SQLException {
        String sql = "DELETE * FROM cats WHERE id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idOwner);

            preparedStatement.executeUpdate();
        }  catch (SQLException e) {
            e.printStackTrace();
        } // finally вызовется в вызываемом методе
    }
    @Override
    public void create(Owner owner) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement  = null;
        String sql = "INSERT INTO owners (name, dateBirth, id) VALUES (?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, owner.getName());
            preparedStatement.setDate(2, java.sql.Date.valueOf(owner.getDateBirth()));
            preparedStatement.setInt(3, owner.getId());

            preparedStatement.executeUpdate();

            setCats(owner.getId(), owner.getCats(), connection);

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
                HashMap<Integer, Cat> cats = getCats(id, connection);
                owner = new Owner.Builder().name(name)
                        .dateBirth(dateBirth)
                        .id(id)
                        .cats(cats)
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

//    @Override
//    public ArrayList<Owner> getAll()   throws SQLException {
//        Connection connection = getConnection();
//        PreparedStatement preparedStatement = null;
//        String sql = "SELECT * FROM owners";
//        ArrayList<Owner> owners = new ArrayList<>();
//        try {
//            preparedStatement = connection.prepareStatement(sql);
//            ResultSet rs = preparedStatement.executeQuery();
//            while (rs.next()) {
//                String name = rs.getString(1);
//                LocalDate dateBirth = rs.getDate(2).toLocalDate();
//                int id = rs.getInt(3);
//                Owner owner = new Owner.Builder().name(name)
//                        .dateBirth(dateBirth)
//                        .id(id)
//                        .build();
//                owners.add(owner);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            if (preparedStatement != null) {
//                preparedStatement.close();
//            }
//            if (connection != null) {
//                connection.close();
//            }
//        }
//        return owners;
//    }

    @Override
    public void remove(int id) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM owners WHERE 'id' = ? LIMIT 1";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

            delCats(id, connection);

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
}
