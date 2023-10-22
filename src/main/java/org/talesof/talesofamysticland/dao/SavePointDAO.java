package org.talesof.talesofamysticland.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.talesof.talesofamysticland.database.ConnectionManager;
import org.talesof.talesofamysticland.model.SavePoint;

public class SavePointDAO{

    public SavePoint findById(Integer id) {
        String sql = "SELECT * FROM Save_point WHERE id = ?;";

        try (
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if(rs.next()) {
                return resultSetToSavePoint(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public SavePoint findByName(String name) {
        String sql = "SELECT * FROM Save_point WHERE name = ?;";

        try (
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();

            if(rs.next()) {
                return resultSetToSavePoint(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public List<SavePoint> findByMap(Integer map) {
        String sql = "SELECT * FROM Save_point WHERE map = ?;";
        List<SavePoint> savePoints = new ArrayList<>();

        try (
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, map);
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                savePoints.add(resultSetToSavePoint(rs));
            }

            return savePoints;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<SavePoint> findAll() {
        String sql = "SELECT * FROM Save_point;";
        List<SavePoint> savePoints = new ArrayList<>();

        try (
            Connection connection = ConnectionManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
        ) {
            while(rs.next()) {
                savePoints.add(resultSetToSavePoint(rs));
            }

            return savePoints;
        
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private SavePoint resultSetToSavePoint(ResultSet rs) throws SQLException {
        return new SavePoint(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getInt("map"),
            rs.getInt("world_x"),
            rs.getInt("world_y")
        );
    }
}