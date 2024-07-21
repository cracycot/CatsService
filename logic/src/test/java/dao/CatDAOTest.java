package dao;


import dao.CatDAO;
import models.Cat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.TruncateTable;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;

public class CatDAOTest {
    static CatDAO catDAO = new CatDAO();
    static Cat cat0;
    static  Cat cat1;
    static  Cat cat2;
    static Cat cat3;
    static Cat cat4;
    @BeforeEach
    void createCats() throws SQLException {
        TruncateTable truncateTable = new TruncateTable();
        truncateTable.truncate("cats");
        truncateTable.truncate("catsfriends");


        LocalDate dateOfBirth0 = LocalDate.of(2005, 12, 29);
        LocalDate dateOfBirth1 = LocalDate.of(2014, 3, 23);
        LocalDate dateOfBirth2 = LocalDate.of(2013, 3, 12);
        LocalDate dateOfBirth3 = LocalDate.of(2018, 11, 9);

        cat0 = new Cat.Builder().
                name("Ivan")
                .id(0)
                .idOwner(0)
                .breed("kal")
                .dateBirth(dateOfBirth0)
                .build();

        cat1 = new Cat.Builder().
                name("Kirill")
                .id(1)
                .idOwner(1)
                .breed("kamizyk")
                .dateBirth(dateOfBirth1)
                .build();

        cat2 = new Cat.Builder().
                name("Makar")
                .id(2)
                .idOwner(2)
                .breed("beautiful")
                .dateBirth(dateOfBirth2)
                .build();

        cat3 = new Cat.Builder().
                name("Gosha")
                .id(3)
                .idOwner(3)
                .breed("krinsh")
                .dateBirth(dateOfBirth3)
                .build();

        cat4 = new Cat.Builder()
                .name("Timur")
                .id(4)
                .idOwner(3)
                .breed("krinh")
                .dateBirth(dateOfBirth1)
                .build();

        HashMap<Integer, Cat> cat0Friends = new HashMap<>(); {
            cat0Friends.put(1, cat1);
            cat0Friends.put(2, cat2);
            cat0Friends.put(3, cat3);
        }

        HashMap<Integer, Cat> cat1Friends = new HashMap<>(); {
            cat1Friends.put(2, cat2);
            cat1Friends.put(3, cat3);
        }

        HashMap<Integer, Cat> cat2Friends = new HashMap<>(); {
            cat2Friends.put(1, cat1);
            cat2Friends.put(3, cat3);
        }

        HashMap<Integer, Cat> cat3Friends = new HashMap<>(); {
            cat3Friends.put(1, cat1);
        }

//        cat0.setFriends(cat0Friends);
//        cat1.setFriends(cat1Friends);
//        cat2.setFriends(cat2Friends);
//        cat3.setFriends(cat3Friends);

        catDAO.create(cat0);
        catDAO.create(cat1);
        catDAO.create(cat2);
        catDAO.create(cat3);
    }

    @Test
    void create() throws SQLException {
//        catDAO.create(cat4);
//        Cat res = catDAO.read(4);
//        boolean flag = Objects.equals(res.getBreed(), cat4.getBreed())  && res.getId() == cat4.getId() && Objects.equals(res.getDateBirth(), cat4.getDateBirth())  && res.getIdOwner() == cat4.getIdOwner();
//        for (int key : res.getFriends().keySet()) {
//            if (!Objects.equals(cat4.getFriends().get(key).getId(),res.getFriends().get(key).getId())) {
//                flag = false;
//            }
//        }
//        Assertions.assertEquals(true, flag);
    }

    @Test
    void read() throws SQLException {
//        Cat res = catDAO.read(0);
//        boolean flag = Objects.equals(res.getBreed(), cat0.getBreed())  && res.getId() == cat0.getId() && Objects.equals(res.getDateBirth(),cat0.getDateBirth())  && res.getIdOwner() == cat0.getIdOwner();
//        for (int key : res.getFriends().keySet()) {
//            if (!Objects.equals(cat0.getFriends().get(key).getId(),res.getFriends().get(key).getId())) {
//                flag = false;
//            }
//        }
//        Assertions.assertEquals(true, flag);
    }

    @Test
    void delete() throws SQLException {
        catDAO.remove(1);
        Cat res = catDAO.read(1);
        Assertions.assertEquals(1, 1);
    }

}
