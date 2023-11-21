package org.talesof.talesofamysticland.game.main;

import javax.swing.JFrame;

public class Game {

    public static final String GAME_ASSETS_PATH = "/org/talesof/talesofamysticland/game-assets";

    public static JFrame window;

    public static void start() {
        
        window = new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Tales of a Mystic Land");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        gamePanel.config.loadConfig();

        if(gamePanel.fullScreenOn) {
            window.setUndecorated(true);
        }

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}
