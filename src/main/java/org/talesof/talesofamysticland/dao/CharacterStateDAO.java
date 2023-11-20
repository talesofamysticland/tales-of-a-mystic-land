package org.talesof.talesofamysticland.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.talesof.talesofamysticland.database.ConnectionManager;
import org.talesof.talesofamysticland.model.CharacterState;

public class CharacterStateDAO {

    public CharacterState save(CharacterState characterState) throws SQLException {
        String sql = """
            INSERT INTO Character_state(play_time, experience, coins, strength, resistance, constitution, dexterity, wisdom)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?);
        """;
       
        try (
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection
                .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
           
            statement.setLong(1, characterState.getPlayTime());
            statement.setInt(2, characterState.getExperience());
            statement.setInt(3, characterState.getCoins());
            statement.setInt(4, characterState.getStrength());
            statement.setInt(5, characterState.getResistance());
            statement.setInt(6, characterState.getConstitution());
            statement.setInt(7, characterState.getDexterity());
            statement.setInt(8, characterState.getWisdom());
            

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if(rs.next()) {
                characterState.setId(rs.getInt(1));
            }

            rs.close();

            return characterState;
        }
       
    }

    public CharacterState findById(Integer id) {
        String sql = "SELECT * FROM Character_state WHERE id = ?;";

        try (
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToCharacterState(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public List<CharacterState> findAll() {
        String sql = "SELECT * FROM Character_state;";
        List<CharacterState> charactersStates = new ArrayList<>();

        try (
            Connection connection = ConnectionManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
        ) {
            while(rs.next()) {
                charactersStates.add(resultSetToCharacterState(rs));
            }

            return charactersStates;
       
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private CharacterState resultSetToCharacterState(ResultSet rs) throws SQLException {
        return new CharacterState(
            rs.getInt("id"),
            rs.getLong("play_time"),
            rs.getInt("experience"),
            rs.getInt("coins"),
            rs.getInt("strength"),
            rs.getInt("resistence"),
            rs.getInt("constitution"),
            rs.getInt("dexterity"),
            rs.getInt("wisdom")
        );
    }
}
