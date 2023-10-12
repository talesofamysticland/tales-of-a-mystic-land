package main.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

import main.Conexao;

public class PlayerDAO {

    public Player create(Player player) throws SQLException {
        String sql = """
            INSERT INTO Player(id_settings, username, email, password, verified, verification_token, register_date)
            VALUES (?, ?, ?, ?, ?, ?, ?);
        """;
        
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection
                .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            
            statement.setInt(1, player.getIdSettings());
            statement.setString(2, player.getUsername());
            statement.setString(3, player.getEmail());
            statement.setString(4, player.getPassword());
            statement.setBoolean(5, player.isVerified());
            statement.setString(6, player.getVerificationToken());
            statement.setObject(7, player.getRegisterDate());

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
            SET id_settings = ?, username = ?, email = ?, password = ?, verified = ?, verification_token = ?, register_date = ?
            WHERE id = ?;
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {

            statement.setInt(1, player.getIdSettings());
            statement.setString(2, player.getUsername());
            statement.setString(3, player.getEmail());
            statement.setString(4, player.getPassword());
            statement.setBoolean(5, player.isVerified());
            statement.setString(6, player.getVerificationToken());
            statement.setObject(7, player.getRegisterDate());
            statement.setInt(8, player.getId());
            
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                return player;
            }
            
            return null;

        } catch (SQLException e) {
            return null;
        }
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM Player WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Player player) {
        delete(player.getId());
    }

    public Player findById(Integer id) {
        String sql = "SELECT * FROM Player WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
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

    public List<Player> findAll() {
        String sql = "SELECT * FROM Player;";
        List<Player> alunos = new ArrayList<>();

        try (
            Connection connection = Conexao.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
        ) {
            while(rs.next()) {
                alunos.add(resultSetToPlayer(rs));
            }

            return alunos;
        
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Player resultSetToPlayer(ResultSet rs) throws SQLException {
        return new Player(
            rs.getInt("id"),
            rs.getInt("id_settings"),
            rs.getString("username"),
            rs.getString("email"),
            rs.getString("password"),
            rs.getBoolean("verified"),
            rs.getString("verification_token"),
            (LocalDateTime) rs.getObject("register_date")
        );
    }
    
}
