package main;

import java.sql.SQLException;

import main.dao.SettingsDAO;
import main.model.Settings;

public class Main {
    
    public static void main(String[] args) throws SQLException {

        SettingsDAO settingsDAO = new SettingsDAO();

        Settings settings = new Settings(1, 1.2f, 3.0f, 2.4f, false, "16:9");

        settingsDAO.create(settings);

        System.out.println(settingsDAO.findById(10));
    }
}
