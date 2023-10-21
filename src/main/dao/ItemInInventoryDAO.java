package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.database.ConnectionManager;
import main.model.ItemInInventory;

public class ItemInInventoryDAO {
    
    public ItemInInventory create(ItemInInventory item) throws SQLException {
        String sql = """
            INSERT INTO Item_in_inventory(character_state_id, item_id, amount, position)
            VALUES (?, ?, ?, ?);
        """;
        
        try (
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            
            statement.setInt(1, item.getcharacterStateId());
            statement.setInt(2, item.getitemId());
            statement.setInt(3, item.getAmount());
            statement.setInt(4, item.getPosition());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            rs.close();
    
            return item;    
        }

    }

    public ItemInInventory update(ItemInInventory item) throws SQLException {
        String sql = """
            UPDATE Item_in_inventory 
            SET character_state_id = ?, item_id = ?, amount = ?, position = ?
            WHERE id = ?;
        """;

        try (
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {

            statement.setInt(1, item.getcharacterStateId());
            statement.setInt(2, item.getitemId());
            statement.setInt(3, item.getAmount());
            statement.setInt(4, item.getPosition());
            statement.setInt(5, item.getId());
            
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                return item;
            }
            
            return null;

        } catch (SQLException e) {
            return null;
        }
    }

    public ItemInInventory findById(Integer id) {
        String sql = "SELECT * FROM Item_in_inventory WHERE id = ?;";

        try (
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToItemInInventory(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public ItemInInventory findByCharacterId(Integer characterStateId) {
        String sql = "SELECT * FROM Item_in_inventory WHERE character_state_id = ?;";

        try (
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, characterStateId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToItemInInventory(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public List<ItemInInventory> findAll() {
        String sql = "SELECT * FROM Player;";
        List<ItemInInventory> itemsInInventories = new ArrayList<>();

        try (
            Connection connection = ConnectionManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
        ) {
            while(rs.next()) {
                itemsInInventories.add(resultSetToItemInInventory(rs));
            }

            return itemsInInventories;
        
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private ItemInInventory resultSetToItemInInventory(ResultSet rs) throws SQLException {
        return new ItemInInventory(
            rs.getInt("id"),
            rs.getInt("character_state_id"),
            rs.getInt("item_id"),
            rs.getInt("amount"),
            rs.getInt("position")
        );
    }
}