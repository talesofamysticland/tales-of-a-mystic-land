package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class ListPlayerByName {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost/estudante?user=estudante&password=estudante&useSSl=true";

        try {
            Connection conn = DriverManager.getConnection(url);

            String name = "Cellbit";

            String sql = "SELECT * FROM Player WHERE name = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, name);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("name");
                String email = rs.getString("email");
                String verified = rs.getString("verified");
                String register_date = rs.getString("register_date");


                System.out.println(id);
                System.out.println(nome);
                System.out.println(email);
                System.out.println(verified);
                System.out.println(register_date);
                System.out.println("\n");
            }

            rs.close();
            pstm.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
