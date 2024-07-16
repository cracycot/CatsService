package com.example;

import models.Cat;
import services.CatService;
import utils.DataBaseConnection;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException {
        LocalDate dateOfBirth = LocalDate.of(2005, 12, 29);

        Cat cat = new Cat();
        cat.setId(0);
        cat.setDateBirth(dateOfBirth);
        cat.setIdOwner(0);
        cat.setBreed("sirya");
        cat.setName("Makar");

        CatService catService = new CatService();

        catService.create(cat);
//        Cat cat1 = new Cat.Builder().name("sa").breed("sakska").dateBirth(dateOfBirth).id(1).idOwner(1).build();

        Cat cat1 = catService.read(0);
//        System.out.println(cat1);

        Cat cat2 = new Cat.Builder().
                name("Ivan")
                .id(1)
                .idOwner(1)
                .breed("kal")
                .dateBirth(dateOfBirth)
                .build();
        catService.create(cat2);
        ArrayList<Cat> cats = catService.getAll();
        for (int i  = 0; i < cats.size(); i ++) {
            System.out.println(cats.get(i));
        }
    }
}
