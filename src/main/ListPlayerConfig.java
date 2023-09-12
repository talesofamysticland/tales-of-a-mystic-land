package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListPlayerConfig {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost/estudante?user=estudante&password=estudante&useSSl=true";

        try {
            Connection conn = DriverManager.getConnection(url);

            int id = 1;
            String sql = "SELECT p.id, stt.volume_effects, stt.volume_music, stt.volume_geral, stt.full_screen, r.width, r.height, MAX(stt.save_date) FROM Player p LEFT JOIN Settings stt ON stt.id = p.id_settings INNER JOIN Resolution r ON r.id = stt.id_resolution WHERE p.id = ? GROUP BY p.id;";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs =  pstm.executeQuery();

            while (rs.next()) {
                String player_id= rs.getString("id");
                double volume_effects = rs.getDouble("volume_effects");
                double volume_music = rs.getDouble("volume_music");
                double volume_geral = rs.getDouble("volume_geral");
                boolean full_screen = rs.getBoolean("full_screen");
                int width = rs.getInt("width");
                int height = rs.getInt("height");
                String save_date = rs.getString("MAX(stt.save_date)");

                System.out.println(player_id);
                System.out.println(volume_effects);
                System.out.println(volume_music);
                System.out.println(volume_geral);
                System.out.println(full_screen);
                System.out.println(width);
                System.out.println(height);
                System.out.println(save_date);
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
