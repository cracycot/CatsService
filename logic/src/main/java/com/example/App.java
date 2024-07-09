package com.example;

import utils.DataBaseConnection;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        DataBaseConnection db  = new DataBaseConnection();
        db.getConnection();
    }
}
