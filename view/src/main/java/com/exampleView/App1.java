package com.exampleView;

import com.exampleLogic.exceptions.ObjectNotFoundException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App1
{
    public static void main( String[] args ) throws ObjectNotFoundException {
        SpringApplication.run(App1.class, args);
    }
}
