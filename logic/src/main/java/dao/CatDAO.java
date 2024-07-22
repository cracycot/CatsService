package dao;

import exceptions.ObjectNotFoundException;
import models.Cat;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import utils.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class CatDAO extends DataBaseConnection implements DAO<Cat> {

    private  static  Configuration configuration = new Configuration().addAnnotatedClass(Cat.class).configure();
    private static final SessionFactory sessionFactory = configuration.buildSessionFactory();


    public CatDAO() {
    }

    @Override
    public void create(Cat cat) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            if (cat.getId() == -1) {
                session.persist(cat);
                session.getTransaction().commit();
            }
            else {
                session.update(cat); // надо проверять корректность
            }
//            System.out.println("OK");
//            System.out.println(cat.getId());

        } catch (Exception e) {
            System.err.println("Ошибка при создании Cat: " + e.getMessage());
        }

    }

    @Override
    public Cat read(int id) throws ObjectNotFoundException {
        Cat cat = new Cat();
        try ( Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            cat = session.get(Cat.class, id);
            session.getTransaction().commit();
        }
        catch (Exception e) {
            System.err.println("Ошибка при удалении Cat: " + e.getMessage());
        }
        if (cat == null) {
            throw new ObjectNotFoundException();
        }
        return cat;
    }
    @Override
    public void update(Cat cat) {
        try ( Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(cat);
            session.getTransaction().commit();
        }
        catch (Exception e) {
            System.err.println("Ошибка при удалении Cat: " + e.getMessage());
        }

    }
    @Override
    public void remove(int id) {
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            Cat cat = session.get(Cat.class, id);
            session.remove(cat);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Ошибка при удалении Cat: " + e.getMessage());
        }
    }

//    private void setFriends(int id, HashMap<Integer, Cat> friends, Connection connection) throws SQLException {
//        String sql = "INSERT INTO catsfriends (id, id1) VALUES (?, ?)";
//        PreparedStatement preparedStatement  = null;
//        try {
//            for (int i : friends.keySet()) {
//                preparedStatement = connection.prepareStatement(sql);
//                preparedStatement.setInt(1, id);
//                preparedStatement.setInt(2, i);
//                preparedStatement.executeUpdate();
//            }
//        }  catch (SQLException e) {
//            e.printStackTrace();
//        } // finally вызовется в вызываемом методе
//
//    }
//
//    private HashMap<Integer, Cat> getFriends(int id, Connection connection) throws  SQLException {
//        String sql = "SELECT id1 FROM catsfriends WHERE id = ?";
//        PreparedStatement preparedStatement = null;
//        HashMap<Integer, Cat> friends = new HashMap<>();
//        try {
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, id);
//            ResultSet rs = preparedStatement.executeQuery();
//            while (rs.next()) {
//                int idFriend = rs.getInt(1);
//                String sqlForInnerRequest = "SELECT * FROM cats WHERE id = ?";
//                PreparedStatement preparedStatementForInnerRequest = connection.prepareStatement(sqlForInnerRequest);
//// Выполнение запроса и работа с результатами
////                PreparedStatement preparedStatementForInnerRequest = connection.prepareStatement(sqlForInnerRequest);
//                preparedStatementForInnerRequest.setInt(1, idFriend);
//                ResultSet friendSet = preparedStatementForInnerRequest.executeQuery();
//
//                if (friendSet.next()) {
//                    String name = friendSet.getString(1);
//                    LocalDate dateBirth = friendSet.getDate(2).toLocalDate();
//                    String breed = friendSet.getString(3);
//                    int idOwner = friendSet.getInt(4);
//                    Cat friend = new Cat.Builder().name(name)
//                            .dateBirth(dateBirth)
//                            .breed(breed)
//                            .idOwner(idOwner)
//                            .id(idFriend)
//                            .build();
//
//                    friends.put(idFriend, friend);
//                }
//            }
//        }  catch (SQLException e) {
//            e.printStackTrace();
//        } // finally вызовется в вызываемом методе
//        return friends;
//
//    }
//
//    private void delFriends(int id, Connection connection) throws SQLException {
//        String sql = "DELETE FROM catsfriends WHERE id = ?";
//        PreparedStatement preparedStatement = null;
//        try {
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, id);
//
//            preparedStatement.executeUpdate();
//        }  catch (SQLException e) {
//            e.printStackTrace();
//        } // finally вызовется в вызываемом методе
//    }

//    @Override
//    public void create(Cat cat) throws SQLException {
//        Connection connection = getConnection();
//        PreparedStatement preparedStatement  = null;
//        String sql = "INSERT INTO cats (name, dateBirth, breed, idOwner, id) VALUES (?, ?, ?, ?, ?)";
//        try {
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, cat.getName());
//            preparedStatement.setDate(2, java.sql.Date.valueOf(cat.getDateBirth()));
//            preparedStatement.setString(3, cat.getBreed());
//            preparedStatement.setInt(4, cat.getIdOwner());
//            preparedStatement.setInt(5, cat.getId());
//
//            preparedStatement.executeUpdate();
//
//            setFriends(cat.getId(),cat.getFriends(), connection);
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
//    }

//    @Override
//    public Cat read(int id) throws SQLException {
//        Connection connection = getConnection();
//        PreparedStatement preparedStatement = null;
//        String sql = "SELECT * FROM cats WHERE id = ?";
//        Cat cat = new Cat();
//        try {
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, id);
//            ResultSet rs = preparedStatement.executeQuery();
//            if (rs.next()) {
//                String name = rs.getString(1);
//                LocalDate dateBirth = rs.getDate(2).toLocalDate();
//                String breed = rs.getString(3);
//                int idOwner = rs.getInt(4);
//                HashMap<Integer, Cat> friends = getFriends(id, connection);
//                cat = new Cat.Builder().name(name)
//                        .dateBirth(dateBirth)
//                        .breed(breed)
//                        .idOwner(idOwner)
//                        .id(id)
//                        .friends(friends)
//                        .build();
//            }
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
//        return cat;
//    }

//    @Override
//    public ArrayList<Cat> getAll() throws SQLException {
//        Connection connection = getConnection();
//        PreparedStatement preparedStatement = null;
//        String sql = "SELECT * FROM cats";
//        ArrayList<Cat> cats = new ArrayList<>();
//        try {
//            preparedStatement = connection.prepareStatement(sql);
//            ResultSet rs = preparedStatement.executeQuery();
//            while (rs.next()) {
//                String name = rs.getString(1);
//                LocalDate dateBirth = rs.getDate(2).toLocalDate();
//                String breed = rs.getString(3);
//                int idOwner = rs.getInt(4);
//                int id = rs.getInt(5);
//                Cat cat = new Cat.Builder().name(name)
//                        .dateBirth(dateBirth)
//                        .breed(breed)
//                        .idOwner(idOwner)
//                        .id(id)
//                        .build();
//                cats.add(cat);
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            if (preparedStatement != null) {
//                preparedStatement.close();
//            }
//            if (connection != null) {
//                connection.close();
//            }
//        }
//        return cats;
//    }

//    @Override
//    public void remove(int id) throws SQLException {
//        Connection connection = getConnection();
//        PreparedStatement preparedStatement = null;
//        String sql = "DELETE FROM cats WHERE id = ?";
//        try {
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, id);
//
//            preparedStatement.executeUpdate();
//
//            delFriends(id, connection);
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
//    }
}
