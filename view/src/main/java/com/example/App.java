package com.example;

import exceptions.ObjectNotFoundException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.CatService;
import services.OwnerService;
import utils.SpringConfig;

/**
 * Hello world!
 *
 */
public class  App 
{
//    @SpringBootApplication
    public static void main( String[] args ) throws ObjectNotFoundException {
//        SpringApplication.run(App.class, args);
        //ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("viewContext.xml");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        CatService catService = context.getBean("catService", CatService.class);
        System.out.println(catService.getCatDAO().read(1).getName());

        OwnerService ownerService = new OwnerService();  // classPathXmlApplicationContext.getBean("ownerServiceBean", OwnerService.class);
//        System.out.println(ownerService.getOwnerDAO().read(1).getName());

    }
}
