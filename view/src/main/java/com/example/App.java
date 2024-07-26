package com.example;

import exceptions.ObjectNotFoundException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.CatService;
import services.OwnerService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ObjectNotFoundException {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("viewContext.xml");
        
        CatService catService = classPathXmlApplicationContext.getBean("catServiceBean", CatService.class);
        System.out.println(catService.getCatDAO().read(1).getName());

        OwnerService ownerService = classPathXmlApplicationContext.getBean("ownerServiceBean", OwnerService.class);
        System.out.println(ownerService.getOwnerDAO().read(1).getName());

        classPathXmlApplicationContext.close();
    }
}
