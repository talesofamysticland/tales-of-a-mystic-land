package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class listLeaderBoard {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost/estudante?user=estudante&password=estudante&useSSl=true";

        try {
            Connection conn = DriverManager.getConnection(url);
            String sql = "SELECT s.character_name, st.experience FROM Save_state st INNER JOIN Save_slot ss ON st.id = ss.id_save_state INNER JOIN Save s ON s.id = ss.id_save GROUP BY s.character_name, st.experience HAVING st.experience > 50 ORDER BY st.experience DESC;";
            Statement stm = conn.createStatement();
            ResultSet rs =  stm.executeQuery(sql);

            while (rs.next()) {
                String character_name = rs.getString("character_name");
                int experience = rs.getInt("experience");

                System.out.println("Name: " + character_name + "| EXP: " + experience);
            }

            rs.close();
            stm.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
