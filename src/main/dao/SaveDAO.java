package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.database.ConnectionManager;
import main.model.Save;

public class SaveDAO {

    public Save create(Save save) throws SQLException {
        String sql = """
            INSERT INTO Save(player_id, character_name, character_class)
            VALUES (?, ?, ?);
        """;
        
        try (
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection
                .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            
            statement.setInt(1, save.getPlayerId());
            statement.setString(2, save.getCharacterName());
            statement.setString(3, save.getCharacterClass());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if(rs.next()) {
                save.setId(rs.getInt(1));
            }

            rs.close();

            return save;
        } 
    }

    public Save update(Save save) throws SQLException {
        String sql = """
            UPDATE Save 
            SET player_id = ?, character_name = ?, character_class = ?
            WHERE id = ?;
        """;

        try (
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {

            statement.setInt(1, save.getPlayerId());
            statement.setString(2, save.getCharacterName());
            statement.setString(3, save.getCharacterClass());
            statement.setInt(4, save.getId());
            
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                return save;
            }
            
            return null;

        } catch (SQLException e) {
            return null;
        }
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM Save WHERE id = ?;";

        try (
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Save save) {
        delete(save.getId());
    }

    public Save findById(Integer id) {
        String sql = "SELECT * FROM Save WHERE id = ?;";

        try (
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToSave(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public List<Save> findAll() {
        String sql = "SELECT * FROM Save;";
        List<Save> saves = new ArrayList<>();

        try (
            Connection connection = ConnectionManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
        ) {
            while(rs.next()) {
                saves.add(resultSetToSave(rs));
            }

            return saves;
        
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Save resultSetToSave(ResultSet rs) throws SQLException {
        return new Save(
            rs.getInt("id"),
            rs.getInt("player_id"),
            rs.getString("character_name"),
            rs.getString("character_class")
        );
    }
}
