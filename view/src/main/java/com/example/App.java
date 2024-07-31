package com.example;

import exceptions.ObjectNotFoundException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.CatService;
import services.OwnerService;
import utils.SpringConfig;

/**
 * Hello world!
 *
 */

@SpringBootApplication
public class  App
{
//    @SpringBootApplication
    public static void main( String[] args ) throws ObjectNotFoundException {
        ApplicationContext context = SpringApplication.run(App.class, args);
        CatService catService = context.getBean(CatService.class);
        catService.getcatById(1);
    }
}
