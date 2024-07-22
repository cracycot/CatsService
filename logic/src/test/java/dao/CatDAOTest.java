package dao;


import dao.CatDAO;
import exceptions.ObjectNotFoundException;
import models.Cat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.TruncateTable;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
                .idOwner(0)
                .breed("kal")
                .dateBirth(dateOfBirth0)
                .build();

        cat1 = new Cat.Builder().
                name("Kirill")
                .idOwner(1)
                .breed("kamizyk")
                .dateBirth(dateOfBirth1)
                .build();

        cat2 = new Cat.Builder().
                name("Makar")
                .idOwner(2)
                .breed("beautiful")
                .dateBirth(dateOfBirth2)
                .build();

        cat3 = new Cat.Builder().
                name("Gosha")
                .idOwner(3)
                .breed("krinsh")
                .dateBirth(dateOfBirth3)
                .build();

        cat4 = new Cat.Builder()
                .name("Timur")
                .idOwner(3)
                .breed("krinh")
                .dateBirth(dateOfBirth1)
                .build();

        Set<Cat> cat0Friends = new HashSet<>(); {
            cat0Friends.add(cat1);
            cat0Friends.add(cat2);
            cat0Friends.add(cat3);
        }

        Set<Cat> cat1Friends = new HashSet<>(); {
            cat1Friends.add(cat2);
            cat1Friends.add(cat3);
        }

        Set<Cat> cat2Friends = new HashSet<>(); {
            cat2Friends.add(cat1);
            cat2Friends.add(cat3);
        }

        Set<Cat> cat3Friends = new HashSet<>(); {
            cat3Friends.add(cat1);
        }

        cat0.setFriends(cat0Friends);
        cat1.setFriends(cat1Friends);
        cat2.setFriends(cat2Friends);
        cat3.setFriends(cat3Friends);

        catDAO.create(cat0);
        catDAO.create(cat1);
        catDAO.create(cat2);
        catDAO.create(cat3);






    }

    @Test
    void create() throws SQLException {
        String  name;
        try {
            name =  catDAO.read(cat3.getId()).getName();
        } catch (ObjectNotFoundException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(cat3.getName(),name);
    }

    @Test
    void read() throws SQLException {
        Cat result = null;
        try {
            result = catDAO.read(cat0.getId());
        } catch (ObjectNotFoundException e) {
            throw new RuntimeException(e);
        }
        boolean res = cat0.equals(result);
        Assertions.assertTrue(res);
    }

    @Test
    void delete() throws SQLException {
        catDAO.remove(cat2.getId());
        Assertions.assertThrows(ObjectNotFoundException.class, () -> catDAO.read(cat2.getId()));
    }

}
