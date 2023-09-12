package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

Public Class DatabaseTableCreator{
        String url = "jdbc:mysql://localhost/estudante?user=estudante&password=estudante&useSSl=true";

        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stm = conn.createStatement();

            String sql = 
            "CREATE TABLE Resolution(
                id INT,
                width INT,
                height INT,

                PRIMARY KEY (id)
            );";

            stm.execute(sql);

            sql = "CREATE TABLE Settings(
                id INT,
                id_resolution INT,
                volume_effects NUMERIC(3,1),
                volume_music NUMERIC(3,1),
                volume_geral NUMERIC(3,1),
                full_screen BOOLEAN,
                save_date DATETIME,
                
                PRIMARY KEY (id),
                FOREIGN KEY (id_resolution) REFERENCES Resolution(id)
            );";

            stm.execute(sql);

            sql = 
            "CREATE TABLE Player(
                id INT,
                id_settings INT,
                name VARCHAR(30),
                email VARCHAR(50),
                password CHAR(60),
                verified BOOLEAN,
                verification_token CHAR(36),
                register_date DATETIME,

                PRIMARY KEY (id),
                FOREIGN KEY (id_settings) REFERENCES Settings(id)
            );";

            stm.execute(sql);

            sql = "CREATE TABLE Item_in_inventory(" + 
            "id INT PRIMARY KEY," +
            "id_sava_state INT," +
            "id_item INT, " +
            "amount INT," +
            "position INT," +
            "FOREIGN KEY (id_item) REFERENCES Item(id),"
            "FOREIGN KEY (id_save_state) REFERENCES Save_State(id));";
            stm.execute(sql);

            sql = "CREATE TABLE Save_slot(" +
                "id INT PRIMARY KEY," +
                "id_save INT," +
                "id_save_state INT," +
                "position INT," +
                "FOREIGN KEY (id_save) REFERENCES Save(id)," +
                "FOREIGN KEY (id_save_state) REFERENCES Save_state(id));";
            stm.execute(sql);

            sql = "CREATE TABLE Save(" +
                "id INT PRIMARY KEY," +
                "id_player INT," +
                "id_class INT," +
                "character_name VARCHAR(20)," +
                "FOREIGN KEY (id_player) REFERENCES Player(id)," +
                "FOREIGN KEY (id_class) REFERENCES Class(id));";
            stm.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

}