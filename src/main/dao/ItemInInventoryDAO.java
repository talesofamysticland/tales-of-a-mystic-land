package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.database.ConnectionManager;
import main.model.ItemInInventory;

public class ItemInInventoryDAO {
    
    public ItemInInventory create(ItemInInventory item) throws SQLException {
        String sql = """
            INSERT INTO Item_in_inventory (id, id_character_state, id_item, amount, position)
            VALUES (?, ?, ?, ?, ?);
        """;
        
        try (
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            
            statement.setInt(1, item.getId());
            statement.setInt(2, item.getIdCharacterState());
            statement.setInt(3, item.getIdItem());
            statement.setInt(4, item.getAmount());
            statement.setInt(5, item.getPosition());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            rs.close();
    
            return item;    
        }

    }

    public ItemInInventory update(ItemInInventory item) throws SQLException {
        String sql = """
            UPDATE Item_in_inventory 
            SET id_character_state = ?, id_item = ?, amount = ?, position = ?
            WHERE id = ?;
        """;

        try (
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {

            statement.setInt(1, item.getId());
            statement.setInt(2, item.getIdCharacterState());
            statement.setInt(3, item.getIdItem());
            statement.setInt(4, item.getAmount());
            statement.setInt(5, item.getPosition());
            
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
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToAluno(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public ItemInInventory findByCharacterId(Integer id_character_state) {
        String sql = "SELECT * FROM Item_in_inventory WHERE id = ?;";

        try (
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToAluno(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    private ItemInInventory resultSetToAluno(ResultSet rs) throws SQLException {
        return new ItemInInventory(
            rs.getInt("id"),
            rs.getInt("id_character_state"),
            rs.getInt("id_item"),
            rs.getInt("amount"),
            rs.getInt("position")
        );
    }
}