package main;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class TableCreator {

    public static void main(String[] args) throws SQLException{
        
        try {
            Connection connection = Conexao.getConnection();

            String sql = "CREATE TABLE Settings(id INT, volume_effects NUMERIC(3,1),volume_music NUMERIC(3,1), volume_geral NUMERIC(3,1), full_screen BOOLEAN, resolution ENUM('4:3', '16:9', '21:9'), save_date DATETIME,PRIMARY KEY (id));";
            Statement stm = connection.createStatement();
            stm.execute(sql);
    
            sql = "CREATE TABLE Player(id INT AUTO_INCREMENT, id_settings INT, username VARCHAR(30), email VARCHAR(50), password CHAR(60), verified BOOLEAN, verification_token CHAR(36), register_date DATETIME, PRIMARY KEY (id),FOREIGN KEY (id_settings) REFERENCES Settings(id));";
            stm = connection.createStatement();
            stm.execute(sql);
    
            sql = "CREATE TABLE Change_password(id INT, id_player INT, validated BOOLEAN, verification_date DATETIME, verification_token CHAR(36), PRIMARY KEY (id), FOREIGN KEY (id_player) REFERENCES Player(id));";
            stm = connection.createStatement();
            stm.execute(sql);   
    
            sql = "CREATE TABLE Save(id INT, id_player INT, character_name VARCHAR(20), character_class ENUM('Warrior', 'Wizard', 'Archer'), PRIMARY KEY(id));";
            stm = connection.createStatement();
            stm.execute(sql);
    
            sql = "CREATE TABLE Save_point(id INT, name VARCHAR(50), map INT, world_x INT, world_y INT, PRIMARY KEY (id));";
            stm = connection.createStatement();
            stm.execute(sql);
    
            sql = "CREATE TABLE Character_state(id INT, play_time DECIMAL(15), experience INT, coins INT, strenght INT, resistance INT, constitution INT, dexterity INT, wisdom INT, PRIMARY KEY (id));";
            stm = connection.createStatement();
            stm.execute(sql);
    
            sql = "CREATE TABLE Save_state(id INT, id_save INT, id_character_state INT, id_save_point INT, date DATETIME, PRIMARY KEY (id), FOREIGN KEY(id_save) REFERENCES Save(id), FOREIGN KEY(id_character_state) REFERENCES Character_state(id), FOREIGN KEY (id_save_point) REFERENCES Save_point(id));";
            stm = connection.createStatement();
            stm.execute(sql);
    
            sql = "CREATE TABLE Item(id INT , name VARCHAR(50), PRIMARY KEY (id));";
            stm = connection.createStatement();
            stm.execute(sql);
    
            sql = "CREATE TABLE Item_in_inventory(id INT , id_character_state INT, id_item INT, amount INT, position INT, PRIMARY KEY (id), FOREIGN KEY (id_item) REFERENCES Item(id), FOREIGN KEY (id_character_state) REFERENCES Character_state(id));";
            stm = connection.createStatement();
            stm.execute(sql);
    
            stm.close();
            connection.close();

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}