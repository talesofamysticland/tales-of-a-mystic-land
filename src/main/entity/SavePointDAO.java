package main.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SavePointDAO{

    public SavePoint findById(Integer id) {
        String sql = "SELECT * FROM Save_point WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToSavePoint(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public List<SavePoint> findAll() {
        String sql = "SELECT * FROM Save_Point;";
        List<SavePoint> SavePoints = new ArrayList<>();

        try (
            Connection connection = Conexao.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
        ) {
            while(rs.next()) {
                SavePoint.add(resultSetToSavePoint(rs));
            }

            return SavePoint;
        
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    
    }

}