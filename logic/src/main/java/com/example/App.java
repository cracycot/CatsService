package com.example;

import models.Cat;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class App {
    public static void main( String[] args ) throws SQLException {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Cat.class);
        configuration.configure();
        LocalDate dateOfBirth0 = LocalDate.of(2005, 12, 29);
        Set<Cat> cat1Friends = new HashSet<>();

        Cat cat2 = new Cat.Builder().
                name("Kirill")
//                .id(4)
                .idOwner(1)
                .breed("kamizyk")
                .dateBirth(dateOfBirth0)

                .build();
        cat1Friends.add(cat2);
        Cat cat1 = new Cat.Builder().idOwner(0).breed("skfkdskf")
                .id(2)
//                .friends(cat1Friends)
                .dateBirth(dateOfBirth0)
                .name("Misha").build();
        cat1Friends.add(cat2);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(cat1);
            transaction.commit();
            System.out.println("OK");
        } finally {
            session.close();
        }

    }
}
 