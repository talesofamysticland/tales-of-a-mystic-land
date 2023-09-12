package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class listItems {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost/estudante?user=estudante&password=estudante&useSSL=true";

        try {
            Connection conn = DriverManager.getConnection(url);
            String sql = "SELECT * FROM Item";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                System.out.println("ID: " + id);
                System.out.println("Nome: " + name);
            }
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao conectar ao banco de dados");
        }
    }
}
