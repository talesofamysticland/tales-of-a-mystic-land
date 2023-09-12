package main;

import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseManager {
    public void insertItem () {
        String url = "jdbc:mysql://localhost/estudante?user=estudante&password=estudante&useSSL=true";

        try {
            Connection conn = DriverManager.getConnection(url);
            int id = 1;
            String name = "ShieldWood";
            String sql = "INSERT INTO Item VALUES (?, ?)";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.setString(2, name);
            pstm.executeUpdate();

            pstm.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertResolution() {
        String url = "jdbc:mysql://localhost/estudante?user=estudante&password=estudante&useSSL=true";

        try {
            Connection conn = DriverManager.getConnection(url);
            int id = 1;
            int width = 16;
            int height = 9;
            String sql = "INSERT INTO Resolution VALUES (?, ?, ?)";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.setInt(2, width);
            pstm.setInt(3, height);
            pstm.executeUpdate();

            pstm.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertSettings() {
        String url = "jdbc:mysql://localhost/estudante?user=estudante&password=estudante&useSSL=true";

        try {
            Connection conn = DriverManager.getConnection(url);
            int id = 1;
            int id_resolution = 2;
            int volume_effects = 60;
            int volume_music = 100;
            int volume_geral = 90;
            boolean full_screen = true;
            int save_date = 12042000;
            String sql = "INSERT INTO Settings VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.setInt(2, id_resolution);
            pstm.setInt(3, volume_effects);
            pstm.setInt(4, volume_music);
            pstm.setInt(5, volume_geral);
            pstm.setBoolean(6, full_screen);
            pstm.setInt(7, save_date);

            pstm.executeUpdate();

            pstm.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertPlayer() {
        String url = "jdbc:mysql://localhost/estudante?user=estudante&password=estudante&useSSL=true";

        try {
            Connection conn = DriverManager.getConnection(url);
            int id = 1;
            int id_settings = 4;
            String name = "Cellbit";
            String email = "rafael.lange2000@ifsp.edu.br";
            String password = "$2a$12$Iz0ATaP4dTjXRrsONCXvkudNmFsif163.GxlWht1wzJhVtVzua2YC";
            boolean verified = true;
            String verification_token = "GDRFAKPWZYTXEUCMHISVJBNOLQPOIUYTREWQZXCVBNMLKJHGF";
            int register_date = 12042010;
            String sql = "INSERT INTO Settings VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.setInt(2, id_settings);
            pstm.setString(3, name);
            pstm.setString(4, email);
            pstm.setString(5, password);
            pstm.setBoolean(6, verified);
            pstm.setString(7, verification_token);
            pstm.setInt(8, register_date);

            pstm.executeUpdate();

            pstm.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}