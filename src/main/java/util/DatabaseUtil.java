package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/football_db";
    private static final String USER = "root"; // Replace with your MySQL username
    private static final String PASSWORD = "ncisbj"; // Replace with your MySQL password

    public static Connection getConnection() throws SQLException {
        try {
            // Load MySQL JDBC driver (optional with modern versions)
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish the connection and return it
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Database connection error: " + e.getMessage());
            throw new SQLException("Could not connect to the database.", e);
        }
    }

    public static void main(String[] args) {
        try {

            System.out.println(getConnection());
        } catch (SQLException e) {
            System.out.println("Driver Not Found!");
            e.printStackTrace();
        }

    }
}


