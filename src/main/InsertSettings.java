package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertSettings {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost/estudante?user=estudante&password=estudante&useSSL=true";
        Connection conn = DriverManager.getConnection(url);
        int id = 1;
        int id_resolution = 1;
        int volume_effects = -5;
        int volume_music = 1;
        int volume_geral = -2;
        boolean full_screen = true;
        String save_date = "2023-09-11 18:43:22";
        String sql = "INSERT INTO Settings VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);
        pstm.setInt(2, id_resolution);
        pstm.setInt(3, volume_effects);
        pstm.setInt(4, volume_music);
        pstm.setInt(5, volume_geral);
        pstm.setBoolean(6, full_screen);
        pstm.setString(7, save_date);

        pstm.executeUpdate();

        pstm.close();
        conn.close();
    }
}
