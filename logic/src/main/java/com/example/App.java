package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import repositories.CatRepository;

import java.sql.SQLException;

public class App {
    static CatRepository catRepository;

    public static void main(String[] args ) {
        SpringApplication.run(App.class, args);
    }
}
 