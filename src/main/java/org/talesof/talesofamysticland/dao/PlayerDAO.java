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
import org.talesof.talesofamysticland.model.Player;

public class PlayerDAO {

    public Player save(Player player) throws SQLException {
        String sql = """
            INSERT INTO Player(username, email, password, verified, verification_token, register_date) 
            VALUES (?, ?, ?, ?, ?, ?);
        """;
        
        try (
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection
                .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            
            statement.setString(1, player.getUsername());
            statement.setString(2, player.getEmail());
            statement.setString(3, player.getPassword());
            statement.setBoolean(4, player.isVerified());
            statement.setString(5, player.getVerificationToken());
            statement.setObject(6, player.getRegisterDate());

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if(rs.next()) {
                player.setId(rs.getInt(1));
            }

            rs.close();

            return player;
        } 
    }

    public Player update(Player player) throws SQLException {
        String sql = """
            UPDATE Player 
            SET username = ?, email = ?, password = ?, verified = ?, verification_token = ?, register_date = ?
            WHERE id = ?;
        """;

        try (
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {

            statement.setString(1, player.getUsername());
            statement.setString(2, player.getEmail());
            statement.setString(3, player.getPassword());
            statement.setBoolean(4, player.isVerified());
            statement.setString(5, player.getVerificationToken());
            statement.setObject(6, player.getRegisterDate());
            statement.setInt(7, player.getId());
            
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                return player;
            }
            
            return null;

        } catch (SQLException e) {
            return null;
        }
    }

    public Player findById(Integer id) {
        String sql = "SELECT * FROM Player WHERE id = ?;";

        try (
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToPlayer(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public Player findByUsername(String username) {
        String sql = "SELECT * FROM Player WHERE username = ?;";

        try (
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToPlayer(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public Player findByEmail(String email) {
        String sql = "SELECT * FROM Player WHERE email = ?;";

        try (
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToPlayer(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public List<Player> findAll() {
        String sql = "SELECT * FROM Player;";
        List<Player> players = new ArrayList<>();

        try (
            Connection connection = ConnectionManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
        ) {
            while(rs.next()) {
                players.add(resultSetToPlayer(rs));
            }

            return players;
        
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Player resultSetToPlayer(ResultSet rs) throws SQLException {
        return new Player(
            rs.getInt("id"),
            rs.getString("username"),
            rs.getString("email"),
            rs.getString("password"),
            rs.getBoolean("verified"),
            rs.getString("verification_token"),
            (LocalDateTime) rs.getObject("register_date")
        );
    }
    
}
