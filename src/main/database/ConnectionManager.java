package main.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    
    public static Connection getConnection() {
        // String url = "jdbc:mysql://localhost/estudante1?user=estudante1&password=estudante1&useSSL=true";
        String url = "jdbc:mysql://localhost/talesof?user=root&password=mateus&useSSL=true";
        try {
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}