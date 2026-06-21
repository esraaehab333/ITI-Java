package com.mycompany.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {

    private static final String URL =
            "jdbc:derby://localhost:1527/ContactList";
    private static final String USER = "root";
    private static final String PASS = "root";

    public static Connection getConnection() throws SQLException {
        System.out.println("Connecting to database...");
        Connection con = DriverManager.getConnection(URL, USER, PASS);
        System.out.println("Connected successfully ✅");
        return con;
    }
}
