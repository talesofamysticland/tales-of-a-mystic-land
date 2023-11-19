package org.talesof.talesofamysticland.database;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class TableManager {

    public static void create() {
        
        try(
            Connection connection = ConnectionManager.getConnection();
        ) {

            String sql = """
                CREATE TABLE Player(
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    username VARCHAR(30) NOT NULL,
                    email VARCHAR(50) NOT NULL,
                    password CHAR(60) NOT NULL,
                    verified BOOLEAN NOT NULL,
                    verification_token CHAR(36) NOT NULL,
                    register_date DATETIME NOT NULL
                );
                
                CREATE TABLE Settings(
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    player_id INT NOT NULL,
                    volume_effects NUMERIC(3,1) NOT NULL,
                    volume_music NUMERIC(3,1) NOT NULL,
                    volume_geral NUMERIC(3,1) NOT NULL,
                    full_screen BOOLEAN NOT NULL,
                    resolution ENUM("4:3", "16:9", "21:9") NOT NULL,
                    save_date DATETIME NOT NULL,
                    
                    FOREIGN KEY(player_id) REFERENCES Player(id)
                );
                
                CREATE TABLE Change_password(
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    player_id INT NOT NULL,
                    validated BOOLEAN NOT NULL,
                    verification_date DATETIME NOT NULL,
                    verification_token CHAR(36) NOT NULL,
                    
                    FOREIGN KEY(player_id) REFERENCES Player(id)
                );
                
                CREATE TABLE Save(
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    player_id INT NOT NULL,
                    character_name VARCHAR(20) NOT NULL,
                    character_class ENUM("Warrior", "Wizard", "Archer") NOT NULL,
                
                    FOREIGN KEY(player_id) REFERENCES Player(id)
                );
                
                CREATE TABLE Save_point(
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(50) NOT NULL,
                    map INT NOT NULL,
                    world_x INT NOT NULL,
                    world_y INT NOT NULL
                );
                
                CREATE TABLE Character_state(
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    play_time DECIMAL(15) NOT NULL,
                    experience INT NOT NULL,
                    coins INT NOT NULL,
                    strenght INT NOT NULL,
                    resistance INT NOT NULL,
                    constitution INT NOT NULL,
                    dexterity INT NOT NULL,
                    wisdom INT NOT NULL
                );
                
                CREATE TABLE Save_state(
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    save_id INT NOT NULL,
                    character_state_id INT NOT NULL,
                    save_point_id INT NOT NULL,
                    date DATETIME NOT NULL,
                    
                    FOREIGN KEY(save_id) REFERENCES Save(id),
                    FOREIGN KEY(character_state_id) REFERENCES Character_state(id),
                    FOREIGN KEY(save_point_id) REFERENCES Save_point(id)
                );
                    
                CREATE TABLE Item(
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(50) NOT NULL
                );
                
                CREATE TABLE Item_in_inventory(
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    character_state_id INT NOT NULL,
                    item_id INT NOT NULL,
                    amount INT NOT NULL,
                    current_equipped BOOLEAN NOT NULL,
                    
                    FOREIGN KEY(character_state_id) REFERENCES Character_state(id),
                    FOREIGN KEY(item_id) REFERENCES Item(id)
                );
            """;
            
            Statement stm = connection.createStatement();
            stm.execute(sql);

            stm.close();
            connection.close();

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static String drop() {
        
        return """
            DROP TABLE IF EXISTS Item_in_inventory;
            DROP TABLE IF EXISTS Item;
            DROP TABLE IF EXISTS Save_state;
            DROP TABLE IF EXISTS Character_state;
            DROP TABLE IF EXISTS Save_point;
            DROP TABLE IF EXISTS Save;
            DROP TABLE IF EXISTS Change_password;
            DROP TABLE IF EXISTS Settings;
            DROP TABLE IF EXISTS Player;
        """;
    }
} 