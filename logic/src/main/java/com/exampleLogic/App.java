package com.exampleLogic;

import com.exampleLogic.models.Cat;
import com.exampleLogic.utils.TruncateTable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class App {
    public static void main( String[] args ) throws SQLException {
        TruncateTable truncateTable = new TruncateTable();
        truncateTable.truncate("cats");
        truncateTable.truncate("owners");


    }
}
 