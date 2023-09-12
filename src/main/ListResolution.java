package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ListResolution {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost/estudante?user=estudante&password=estudante&useSSL=true";

        try {
            Connection conn = DriverManager.getConnection(url);

            String sql = "SELECT width, height FROM Resolution";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                int width = rs.getInt("width");
                int height = rs.getInt("height");

                System.out.println(width);
                System.out.println(height);
                System.out.println("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();;
        }
    }
}
