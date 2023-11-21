package org.talesof.talesofamysticland.game;

import javax.swing.JFrame;

import org.talesof.talesofamysticland.game.entity.PlayerCharacter;
import org.talesof.talesofamysticland.game.main.GamePanel;

public class Game {

    public static final String GAME_ASSETS_PATH = "/org/talesof/talesofamysticland/game-assets";

    public static JFrame window;

    public static GamePanel gamePanel;

    public static void start(PlayerCharacter player) {
        
        window = new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Tales of a Mystic Land");

        gamePanel = new GamePanel(player);
        window.add(gamePanel);

        gamePanel.config.loadConfig();

        window.setUndecorated(true);

        window.pack();

        window.setAlwaysOnTop(true);
        window.setFocusableWindowState(true);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}
