package org.talesof.talesofamysticland.game.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.talesof.talesofamysticland.game.Game;

public class KeyHandler implements KeyListener {

    GamePanel gp;

    public boolean upPressed, downPressed, leftPressed, rightPressed,
            enterPressed, shotKeyPressed, spacePressed;

    boolean checkDrawTime;

    public KeyHandler(GamePanel gp) { this.gp = gp; }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int code = e.getKeyCode();

        // Play State
        if(gp.gameState == gp.playState) { playState(code); }

        // Pause State
        else if(gp.gameState == gp.pauseState) { pauseState(code); }

        // Dialogue State
        else if(gp.gameState == gp.dialogueState) { dialogueState(code); }

        // Character status State
        else if(gp.gameState == gp.characterState) { characterState(code); }

        // Options State
        else if(gp.gameState == gp.optionsState) { optionsState(code); }

        // Game over State
        else if(gp.gameState == gp.gameOverState) { gameOverState(code); }

        // Trade State
        else if(gp.gameState == gp.tradeState) { tradeState(code); }

        // Map State
        else if(gp.gameState == gp.mapState) { mapState(code); }
    }

    public void playState(int code) {
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = true;
        }

        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = true;
        }

        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }

        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }

        if(code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }

        if(code == KeyEvent.VK_F) {
            shotKeyPressed = true;
        }

        if(code == KeyEvent.VK_P) {
            gp.gameState = gp.pauseState;
            gp.stopMusic();
        }

        if(code == KeyEvent.VK_C) {
            gp.gameState = gp.characterState;
        }

        if(code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.optionsState;
        }

        if(code == KeyEvent.VK_M) {
            gp.gameState = gp.mapState;
        }

        if(code == KeyEvent.VK_X) {
            gp.map.miniMapOn = !gp.map.miniMapOn;
        }

        if(code == KeyEvent.VK_SPACE) {
            spacePressed = true;
        }

        if(code == KeyEvent.VK_T) {
            checkDrawTime = !checkDrawTime;
        }

        if(code == KeyEvent.VK_R) {
            switch(gp.currentMap) {
                case 0: gp.tileM.loadMap(Game.GAME_ASSETS_PATH + "/maps/worldV2.txt", 0);
                case 1: gp.tileM.loadMap(Game.GAME_ASSETS_PATH + "/maps/interior01.txt", 1);
            }
        }
    }

    public void pauseState(int code) {
        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.playState;
            gp.resumeMusic();
        }
    }

    public void dialogueState(int code) {
        if(code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
    }

    public void characterState(int code) {
        if(code == KeyEvent.VK_C || code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }

        if(code == KeyEvent.VK_ENTER) {
            gp.player.selectItem();
        }

        playerInventory(code);
    }

    public void optionsState(int code) {

        if(code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }

        gp.navigationService.openConfigurationsMenu();
    }

    public void gameOverState(int code) {

        int maxCommandNum = 1;

        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            gp.ui.commandNum--;
            gp.playSoundEffect(9);
            if(gp.ui.commandNum < 0) {
                gp.ui.commandNum = maxCommandNum;
            }
        }

        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            gp.ui.commandNum++;
            gp.playSoundEffect(9);
            if (gp.ui.commandNum > maxCommandNum) {
                gp.ui.commandNum = 0;
            }
        }

        if(code == KeyEvent.VK_ENTER) {

            // Retry
            if (gp.ui.commandNum == 0) {
                gp.gameState = gp.playState;
                gp.resetGame(false);
                gp.playMusic(0);
                // Quit
            } else if(gp.ui.commandNum == 1) {
                
                gp.resetGame(true);
            }
        }
    }

    public void tradeState(int code) {

        if(code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }

        if(gp.ui.subState == 0) {
            if(code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
                gp.playSoundEffect(9);
            }
            if(code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
                gp.playSoundEffect(9);
            }
        }

        if(gp.ui.subState == 1) {
            npcInventory(code);
            if(code == KeyEvent.VK_ESCAPE) {
                gp.ui.subState = 0;
            }
        }

        if(gp.ui.subState == 2) {
            playerInventory(code);
            if(code == KeyEvent.VK_ESCAPE) {
                gp.ui.subState = 0;
            }
        }
    }

    public void mapState(int code) {

        if(code == KeyEvent.VK_M) {
            gp.gameState = gp.playState;
        }
    }

    public void playerInventory(int code) {

        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            if(gp.ui.playerSlotRow > gp.ui.minSlotRow) {
                gp.ui.playerSlotRow--;
                gp.playSoundEffect(9);
            }
        }

        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            if(gp.ui.playerSlotRow < gp.ui.maxSlotRow) {
                gp.ui.playerSlotRow++;
                gp.playSoundEffect(9);
            }
        }

        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            if(gp.ui.playerSlotCol > gp.ui.minSlotCol) {
                gp.ui.playerSlotCol--;
                gp.playSoundEffect(9);
            }
        }

        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            if(gp.ui.playerSlotCol < gp.ui.maxSlotCol) {
                gp.ui.playerSlotCol++;
                gp.playSoundEffect(9);
            }
        }
    }

    public void npcInventory(int code) {

        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            if(gp.ui.npcSlotRow > gp.ui.minSlotRow) {
                gp.ui.npcSlotRow--;
                gp.playSoundEffect(9);
            }
        }

        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            if(gp.ui.npcSlotRow < gp.ui.maxSlotRow) {
                gp.ui.npcSlotRow++;
                gp.playSoundEffect(9);
            }
        }

        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            if(gp.ui.npcSlotCol > gp.ui.minSlotCol) {
                gp.ui.npcSlotCol--;
                gp.playSoundEffect(9);
            }
        }

        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            if(gp.ui.npcSlotCol < gp.ui.maxSlotCol) {
                gp.ui.npcSlotCol++;
                gp.playSoundEffect(9);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = false; 
        }

        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }

        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }

        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }

        if(code == KeyEvent.VK_ENTER) {
            enterPressed = false;
        }

        if(code == KeyEvent.VK_F) {
            shotKeyPressed = false;
        }

        if(code == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
    }
}
