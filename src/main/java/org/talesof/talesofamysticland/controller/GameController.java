package org.talesof.talesofamysticland.controller;

import org.talesof.talesofamysticland.game.main.GamePanel;
import org.talesof.talesofamysticland.service.GameService;

public class GameController {

    private GamePanel gamePanel;

    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public GameService getGameService() {
        return gameService;
    }

    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }       
}
