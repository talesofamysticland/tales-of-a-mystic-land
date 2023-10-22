package org.talesof.talesofamysticland.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.talesof.talesofamysticland.database.ConnectionManager;
import org.talesof.talesofamysticland.model.Item;

public class ItemDAO {

    public Item findById(Integer id) {
        String sql = "SELECT * FROM Item WHERE id = ?;";

        try (
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if(rs.next()) {
                return resultSetToItem(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public Item findByName(String name) {
        String sql = "SELECT * FROM Item WHERE name = ?;";

        try (
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();

            if(rs.next()) {
                return resultSetToItem(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public List<Item> findAll() {
        String sql = "SELECT * FROM Item;";
        List<Item> items = new ArrayList<>();

        try (
            Connection connection = ConnectionManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
        ) {
            while(rs.next()) {
                items.add(resultSetToItem(rs));
            }

            return items;
        
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Item resultSetToItem(ResultSet rs) throws SQLException {
        return new Item(
            rs.getInt("id"),
            rs.getString("name")
        );
    }

}