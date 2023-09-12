package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ListTable {
    public void listItems() {
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

    public void listResolution() {
        String url = "jdbc:mysql://localhost/estudante?user=estudante&password=estudante&useSSL=true";

        try {
            Connection conn = DriverManager.getConnection(url);
            String sql = "SELECT width, height FROM Resolution";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                int width = rs.getInt("width");
                int height = rs.getInt("height");

                System.out.println("width: " + width);
                System.out.println("height: " + height);
            }
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao conectar ao banco de dados");
        }
    }

    public void printLeaderBoard(){
        String url = "jdbc:mysql://localhost/estudante?user=estudante&password=estudante&useSSl=true";

        try{
        Connection conn = DriverManager.getConnection(url);
        String sql = "SELECT s.character_name, st.experience FROM Save_state st INNER JOIN Save_slot ss ON st.id = ss.id_save_state INNER JOIN Save s ON s.id = ss.id_save GROUP BY s.character_name, st.experience HAVING st.experience > 50 ORDER BY st.experience DESC;";
        Statement stm = conn.createStatement();
        ResultSet rs =  stm.executeQuery(sql);

        while (rs.next()) {
            String character_name = rs.getString(character_name);
            int experience = rs.getInt(experience);

            System.out.println("Name: " + character_name + "| EXP: " + experience);
        }

        conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
