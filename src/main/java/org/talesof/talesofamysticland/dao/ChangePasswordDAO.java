package org.talesof.talesofamysticland.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

import org.talesof.talesofamysticland.database.ConnectionManager;
import org.talesof.talesofamysticland.model.ChangePassword;

public class ChangePasswordDAO {

    public ChangePassword create(ChangePassword changePassword) throws SQLException {
        String sql = """
            INSERT INTO Change_password(player_id, validated, verification_date, verification_token) 
            VALUES (?, ?, ?, ?);
        """;
        
        try (
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection
                .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            
            statement.setInt(1, changePassword.getPlayerId());
            statement.setBoolean(2, changePassword.isValidated());
            statement.setObject(3, changePassword.getVerificationDate());
            statement.setString(4, changePassword.getVerificationToken());

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if(rs.next()) {
                changePassword.setId(rs.getInt(1));
            }

            rs.close();

            return changePassword;
        } 
    }

    public ChangePassword update(ChangePassword changePassword) throws SQLException {
        String sql = """
            UPDATE Change_password 
            SET player_id = ?, validated = ?, verification_date = ?, verification_token = ?
            WHERE id = ?;
        """;

        try (
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {

            statement.setInt(1, changePassword.getPlayerId());
            statement.setBoolean(2, changePassword.isValidated());
            statement.setObject(3, changePassword.getVerificationDate());
            statement.setString(4, changePassword.getVerificationToken());
            statement.setInt(5, changePassword.getId());
            
            int affectedLines = statement.executeUpdate();

            if (affectedLines > 0) {
                return changePassword;
            }
            
            return null;

        } catch (SQLException e) {
            return null;
        }
    }

    public ChangePassword findById(Integer id) {
        String sql = "SELECT * FROM Change_password WHERE id = ?;";

        try (
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToChangePassword(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public List<ChangePassword> findAll() {
        String sql = "SELECT * FROM Change_password;";
        List<ChangePassword> changes = new ArrayList<>();

        try (
            Connection connection = ConnectionManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
        ) {
            while(rs.next()) {
                changes.add(resultSetToChangePassword(rs));
            }

            return changes;
        
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private ChangePassword resultSetToChangePassword(ResultSet rs) throws SQLException {
        return new ChangePassword(
            rs.getInt("id"),
            rs.getInt("player_id"),
            rs.getBoolean("validated"),
            (LocalDateTime) rs.getObject("verification_date"),
            rs.getString("verification_token")
        );
    }
    
}
