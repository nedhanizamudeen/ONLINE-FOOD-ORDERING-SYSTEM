package com.ofos.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/dbmsproject";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            java.util.logging.Logger.getLogger(DBConnection.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Adding the main method for testing the connection
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = getConnection();
            if (connection != null) {
                System.out.println("Connection established successfully!");
            } else {
                System.out.println("Failed to establish connection.");
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                    System.out.println("Connection closed.");
                }
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}

