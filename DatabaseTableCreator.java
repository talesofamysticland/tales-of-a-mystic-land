package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

Public Class DatabaseTableCreator{
        String url = "jdbc:mysql://localhost/estudante?user=estudante&password=estudante&useSSl=true";
        Try {
            Connection conn = DriverManager.getConnection(url);
            Statement stm = conn.createStatement();

            String sql = "CREATE TABLE Item(id INT PRIMARY KEY, name VARCHAR(50));";
            stm.execute(sql);

        sql = "CREATE TABLE Save_point(" +
        "id INT PRIMARY KEY," +
        "name VARCHAR(50)," +
        "map INT," +
        "world_x INT," +
        "world_y INT);";
        stm.execute(sql);

        sql = "CREATE TABLE Save_state (" +
        "id INT PRIMARY KEY," +
        "id_save_point INT," +
        "play_time INT," +
        "date DATETIME," +
        "experience INT," +
        "coins INT," +
        "strength INT," +
        "resistence INT," +
        "constitution INT," +
        "dexterity INT," +
        "wisdom INT," +
        "FOREIGN KEY (id_save_point) REFERENCES Save_point(id));";
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