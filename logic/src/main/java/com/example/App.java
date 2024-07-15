package com.example;

import models.Cat;
import services.CatService;
import utils.DataBaseConnection;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
    }
}
