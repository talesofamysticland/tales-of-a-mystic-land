package org.talesof.talesofamysticland;

import java.sql.SQLException;

import org.talesof.talesofamysticland.dao.SettingsDAO;
import org.talesof.talesofamysticland.model.Settings;

public class Main {
    
    public static void main(String[] args) throws SQLException {

        SettingsDAO settingsDAO = new SettingsDAO();

        Settings settings = new Settings(1, 1.2f, 3.0f, 2.4f, false, "16:9");

        settingsDAO.create(settings);

        System.out.println(settingsDAO.findById(10));
    }
}
