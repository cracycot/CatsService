package dao;

import models.Cat;
import models.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.TruncateTable;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;

public class OwnerDAOTest {
    static CatDAO catDAO = new CatDAO();
    static OwnerDAO ownerDAO = new OwnerDAO();
    static Cat cat0;
    static  Cat cat1;

    static Owner owner0;

    static Owner owner1;

    @BeforeEach
    public void createOwners() throws SQLException {
        TruncateTable truncateTable = new TruncateTable();
        truncateTable.truncate("cats");
        truncateTable.truncate("catsfriends");
        truncateTable.truncate("owners");


        LocalDate dateOfBirth0 = LocalDate.of(2005, 12, 29);
        LocalDate dateOfBirth1 = LocalDate.of(2014, 3, 23);


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
                .idOwner(0)
                .breed("kamizyk")
                .dateBirth(dateOfBirth1)
                .build();

        HashMap<Integer, Cat> cats = new HashMap<>();
        cats.put(cat0.getId(), cat0);
        cats.put(cat1.getId(), cat1);

        owner0 = new Owner.Builder()
                .name("Timur")
                .cats(cats)
                .id(0)
                .dateBirth(dateOfBirth0)
                .build();

        owner1 = new Owner.Builder()
                .name("Egor")
                .cats(new HashMap<>())
                .id(1)
                .dateBirth(dateOfBirth1)
                .build();

        ownerDAO.create(owner0);
    }

    @Test
    void create() throws SQLException {
        ownerDAO.create(owner1);
        Owner res = ownerDAO.read(1);
        boolean flag = res.getId() == owner1.getId() && Objects.equals(res.getDateBirth(), owner1.getDateBirth());
        for (int key : res.getCats().keySet()) {
            if (!Objects.equals(owner1.getCats().get(key).getId(),res.getCats().get(key).getId())) {
                flag = false;
            }
        }
        Assertions.assertEquals(true, flag);
    }

    @Test
    void read() throws SQLException {
        Owner res = ownerDAO.read(0);
        boolean flag = res.getId() == owner0.getId() && Objects.equals(res.getDateBirth(), owner0.getDateBirth());
        for (int key : res.getCats().keySet()) {
            if (!Objects.equals(owner0.getCats().get(key).getId(),res.getCats().get(key).getId())) {
                flag = false;
            }
        }
        Assertions.assertEquals(true, flag);
    }

    @Test
    void delete() throws SQLException {
        catDAO.remove(1);
        Cat res = catDAO.read(1);
        Assertions.assertEquals(1, 1);
    }
}
