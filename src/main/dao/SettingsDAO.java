package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

import main.database.ConnectionManager;
import main.model.Settings;

public class SettingsDAO {

    public Settings create(Settings settings) throws SQLException {
        String sql = """
            INSERT INTO Settings(player_id, volume_effects, volume_music, volume_geral, full_screen, resolution, save_date)
            VALUES (?, ?, ?, ?, ?, ?, ?);
        """;
       
        try (
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection
                .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
           
            statement.setInt(1, settings.getPlayerId());
            statement.setFloat(2, settings.getVolumeEffects());
            statement.setFloat(3, settings.getVolumeMusic());
            statement.setFloat(4, settings.getVolumeGeral());
            statement.setBoolean(5, settings.isFullScreen());
            statement.setString(6, settings.getResolution());
            statement.setObject(7, settings.getSaveDate());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if(rs.next()) {
                settings.setId(rs.getInt(1));
            }

            rs.close();

            return settings;
        }
       
    }

    public Settings findById(Integer id) {
        String sql = "SELECT * FROM Settings WHERE id = ?;";

        try (
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToSettings(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public List<Settings> findAll() {
        String sql = "SELECT * FROM Settings;";
        List<Settings> settings = new ArrayList<>();

        try (
            Connection connection = ConnectionManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
        ) {
            while(rs.next()) {
                settings.add(resultSetToSettings(rs));
            }

            return settings;
       
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Settings resultSetToSettings(ResultSet rs) throws SQLException {
        return new Settings(
            rs.getInt("id"),
            rs.getInt("player_id"),
            rs.getFloat("volume_effects"),
            rs.getFloat("volume_music"),
            rs.getFloat("volume_geral"),
            rs.getBoolean("full_screen"),
            rs.getString("resolution"),
            (LocalDateTime) rs.getObject("save_date")
        );
    }
}
