package main;

import java.sql.SQLException;
import java.time.LocalDateTime;

import main.entity.ItemDAO;
import main.entity.Player;
import main.entity.PlayerDAO;
import main.entity.SavePointDAO;

public class Main {
    
    public static void main(String[] args) throws SQLException {

        PlayerDAO playerDao = new PlayerDAO();

        Player player = new Player(1, "skdjfk", "fdghfg", "dfgjkdfg", true, "dfjkgsjkdfjk", LocalDateTime.now());
        playerDao.create(player);
    }
}
