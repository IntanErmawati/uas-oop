package com.laundry.laundry.databases;
import java.sql.*;
public class DbConncection {
    private static final String  URL = "jdbc:mysql://localhost:3306/laundry";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Database connected");
            return connection;
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
