package org.talesof.talesofamysticland.database;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class TableCreator {

    public static void createTables() {
        
        try(
            Connection connection = ConnectionManager.getConnection();
        ) {

            String sql = """
                                    
                CREATE TABLE IF NOT EXISTS Player(
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    username VARCHAR(30),
                    email VARCHAR(50),
                    password CHAR(60),
                    verified BOOLEAN,
                    verification_token CHAR(36),
                    register_date DATETIME
                );

                CREATE TABLE IF NOT EXISTS Settings(
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    player_id INT,
                    volume_effects NUMERIC(3,1),
                    volume_music NUMERIC(3,1),
                    volume_geral NUMERIC(3,1),
                    full_screen BOOLEAN,
                    resolution ENUM("4:3", "16:9", "21:9"),
                    save_date DATETIME
                );

                CREATE TABLE IF NOT EXISTS Change_password(
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    player_id INT,
                    validated BOOLEAN,
                    verification_date DATETIME,
                    verification_token CHAR(36),
                    
                    FOREIGN KEY(player_id) REFERENCES Player(id)
                );

                CREATE TABLE IF NOT EXISTS Save(
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    player_id INT,
                    character_name VARCHAR(20),
                    character_class ENUM("Warrior", "Wizard", "Archer"),

                    FOREIGN KEY(player_id) REFERENCES Player(id)
                );

                CREATE TABLE IF NOT EXISTS Save_point(
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(50),
                    map INT,
                    world_x INT,
                    world_y INT
                );

                CREATE TABLE IF NOT EXISTS Character_state(
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    play_time DECIMAL(15),
                    experience INT,
                    coins INT,
                    strenght INT,
                    resistance INT,
                    constitution INT,
                    dexterity INT,
                    wisdom INT
                );

                CREATE TABLE IF NOT EXISTS Save_state(
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    save_id INT,
                    character_state_id INT,
                    save_point_id INT,
                    date DATETIME,
                    
                    FOREIGN KEY(save_id) REFERENCES Save(id),
                    FOREIGN KEY(character_state_id) REFERENCES Character_state(id),
                    FOREIGN KEY(save_point_id) REFERENCES Save_point(id)
                );
                    
                CREATE TABLE IF NOT EXISTS Item(
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(50)
                );

                CREATE TABLE IF NOT EXISTS Item_in_inventory(
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    character_state_id INT,
                    item_id INT,
                    amount INT,
                    position INT,
                    
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
}