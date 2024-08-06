package com.exampleLogic.utils;

import com.exampleLogic.models.Cat;
import com.exampleLogic.models.Owner;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConfiguration {
    static public SessionFactory createSessionFactory() {
       Configuration configuration = new Configuration();
       configuration.addAnnotatedClass(Cat.class);
       configuration.addAnnotatedClass(Owner.class);
       configuration.configure();
       return configuration.buildSessionFactory();
    }
}
