package org.talesof.talesofamysticland.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.talesof.talesofamysticland.database.DatabaseManager;
import org.talesof.talesofamysticland.model.CharacterState;
import org.talesof.talesofamysticland.model.ItemInInventory;

public class ItemInInventoryDAO {
    
    public ItemInInventory save(ItemInInventory item) throws SQLException {
        String sql = """
            INSERT INTO Item_in_inventory(character_state_id, item_id, amount, current_equipped)
            VALUES (?, ?, ?, ?);
        """;
        
        try (
            Connection connection = DatabaseManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            
            statement.setInt(1, item.getcharacterStateId());
            statement.setInt(2, item.getitemId());
            statement.setInt(3, item.getAmount());
            statement.setBoolean(4, item.isCurrentEquipped());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            rs.close();
    
            return item;    
        }

    }

    public ItemInInventory findById(Integer id) {
        String sql = "SELECT * FROM Item_in_inventory WHERE id = ?;";

        try (
            Connection connection = DatabaseManager.getConnection();
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

    public List<ItemInInventory> findByCharacterState(CharacterState characterState) {
        String sql = "SELECT * FROM Item_in_inventory WHERE character_state_id = ?;";

        List<ItemInInventory> itemsInInventory = new ArrayList<>();

        try (
            Connection connection = DatabaseManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, characterState.getId());
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                itemsInInventory.add(resultSetToItemInInventory(rs));
            }

            return itemsInInventory;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ItemInInventory> findAll() {
        String sql = "SELECT * FROM Player;";
        List<ItemInInventory> itemsInInventories = new ArrayList<>();

        try (
            Connection connection = DatabaseManager.getConnection();
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
            rs.getBoolean("current_equipped")
        );
    }
}