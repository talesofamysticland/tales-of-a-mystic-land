package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertPlayer {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost/estudante?user=estudante&password=estudante&useSSL=true";
        Connection conn = DriverManager.getConnection(url);

        int id = 1;
        int id_settings = 4;
        String name = "Cellbit";
        String email = "rafael.lange2000@ifsp.edu.br";
        String password = "42a$12$Iz0ATaP4dTjXRrsONCXvkudNmFsif163.GxlWht1wzJhVtVzua2YC";
        boolean verified = true;
        String verification_token = "5mFaS8Ry-Ni6hPfAc-Dm9bDiT6-R8Ei2rMz";
        String register_date = "2023-08-29 01:25:33";

        String sql = "INSERT INTO Settings VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);
        pstm.setInt(2, id_settings);
        pstm.setString(3, name);
        pstm.setString(4, email);
        pstm.setString(5, password);
        pstm.setBoolean(6, verified);
        pstm.setString(7, verification_token);
        pstm.setString(8, register_date);

        pstm.executeUpdate();

        pstm.close();
        conn.close();
    }
}
