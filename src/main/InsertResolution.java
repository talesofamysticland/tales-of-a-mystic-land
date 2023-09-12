package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertResolution {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost/estudante?user=estudante&password=estudante&useSSL=true";

        try{
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

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
