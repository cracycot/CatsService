package com.exampleView;

import com.exampleLogic.exceptions.ObjectNotFoundException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
//@EnableJpaRepositories(basePackages = "repositories")
public class App1
{
    public static void main( String[] args ) throws ObjectNotFoundException {
//        CatController catController = new CatController();
//        CatService catService = catController.getCatService();
//        System.out.println(catService.getCatById(1l).get().getName());
        SpringApplication.run(App1.class, args);
//        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
//
//        // Получаем OwnerService из контекста
//        TestServuce catService = context.getBean(TestServuce.class);
//
//        // Теперь вы можете использовать ownerService
//        System.out.println(catService.getCatById(1l).get().getId());

        //ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("viewContext.xml");
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
//        CatService catService = context.getBean("catService", CatService.class);
//        System.out.println(catService.getCatRepository().findAll());
//
//        OwnerService ownerService = new OwnerService();  // classPathXmlApplicationContext.getBean("ownerServiceBean", OwnerService.class);
//        System.out.println(ownerService.getOwnerDAO().read(1).getName());

    }
}
