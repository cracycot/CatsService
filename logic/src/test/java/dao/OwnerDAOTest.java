package dao;

import exceptions.ObjectNotFoundException;
import models.Cat;
import models.Owner;
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

public class OwnerDAOTest {
    static CatDAO catDAO = new CatDAO();
    static OwnerDAO ownerDAO = new OwnerDAO();

    static Owner owner0;
    static Owner owner1;
    static Cat cat0;
    static  Cat cat1;
    static  Cat cat2;
    static Cat cat3;
    static Cat cat4;
    @BeforeEach
    void createCats() throws SQLException {
        TruncateTable truncateTable = new TruncateTable();
        truncateTable.truncate("owners");
        truncateTable.truncate("cats");
        truncateTable.truncate("catsfriends");


        LocalDate dateOfBirth0 = LocalDate.of(2005, 12, 29);
        LocalDate dateOfBirth1 = LocalDate.of(2014, 3, 23);
        LocalDate dateOfBirth2 = LocalDate.of(2013, 3, 12);
        LocalDate dateOfBirth3 = LocalDate.of(2018, 11, 9);

        owner0 = new Owner.Builder()
                .name("Timur")
                .dateBirth(dateOfBirth0)
                .build();

        owner1 = new Owner.Builder()
                .name("Egor")
                .dateBirth(dateOfBirth1)
                .build();
//        System.out.println(owner1.getId());
        cat0 = new Cat.Builder().
                name("Ivan")
                .owner(owner0)
                .breed("kal")
                .dateBirth(dateOfBirth0)
                .build();

        cat1 = new Cat.Builder().
                name("Kirill")
                .owner(owner0)
                .breed("kamizyk")
                .dateBirth(dateOfBirth1)
                .build();

        cat2 = new Cat.Builder().
                name("Makar")
                .owner(owner0)
                .breed("beautiful")
                .dateBirth(dateOfBirth2)
                .build();

        cat3 = new Cat.Builder().
                name("Gosha")
                .owner(owner1)
                .breed("krinsh")
                .dateBirth(dateOfBirth3)
                .build();

        cat4 = new Cat.Builder()
                .name("Timur")
                .owner(owner0)
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
//            cat3Friends.add(cat1);
        }

        Set<Cat> petsOwner0 =  new HashSet<>();
        petsOwner0.add(cat0);
        petsOwner0.add(cat2);
        petsOwner0.add(cat4);
        petsOwner0.add(cat1);

        Set<Cat> petsOwner1 = new HashSet<>();

        petsOwner1.add(cat3);


        cat0.setFriends(cat0Friends);
        cat1.setFriends(cat1Friends);
        cat2.setFriends(cat2Friends);
        cat3.setFriends(cat3Friends);


        owner0.setCats(petsOwner0);
        owner1.setCats(petsOwner1);

        catDAO.create(cat0);
        catDAO.create(cat1);
        catDAO.create(cat2);
        catDAO.create(cat3);
        ownerDAO.create(owner1);
        ownerDAO.create(owner0);
        System.out.println(owner1.getId());

    }

    @Test
    void create() throws SQLException {
        Owner result;
        try {
            result =  ownerDAO.read(owner0.getId());
        } catch (ObjectNotFoundException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertTrue(owner0.equalsCats(result));
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
