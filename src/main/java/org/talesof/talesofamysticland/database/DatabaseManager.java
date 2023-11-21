package org.talesof.talesofamysticland.database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    
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

    public static void createTables() {
        String path = "database/create-table.sql";
        readSQLFile(path);
    }

    public static void dropTables() {
        String path = "database/drop-table.sql";
        readSQLFile(path);
    }

    public static void createViews() {
        String path = "database/create-view.sql";
        readSQLFile(path);
    }

    public static void insertData() {
        String path = "database/insert-data.sql";
        readSQLFile(path);
    }

    private static void readSQLFile(String path) {
        try(
            Connection connection = getConnection();
            Statement stm = connection.createStatement();
            BufferedReader reader = new BufferedReader(new FileReader(path))
        ) {

            String line;
            StringBuilder sb = new StringBuilder();

            while((line = reader.readLine()) != null) {
                sb.append(line);
            }

            String[] sqls = sb.toString().split(";");

            for(String sql : sqls) {
                stm.execute(sql);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}