package org.talesof.talesofamysticland.game.tile;

import org.talesof.talesofamysticland.game.main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WorldMap extends TileManager {

    BufferedImage[] worldMap;
    public boolean miniMapOn = false;

    public WorldMap(GamePanel gp) {
        super(gp);

        createWorldMap();
    }

    public void createWorldMap() {

        worldMap = new BufferedImage[gp.maxMap];
        int worldMapWidth = gp.tileSize * gp.maxWorldCol;
        int worldMapHeight = gp.tileSize * gp.maxWorldRow;

        for(int i = 0; i < gp.maxMap; i++) {

            worldMap[i] = new BufferedImage(worldMapWidth, worldMapHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = worldMap[i].createGraphics();

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldCol) {

                int tileNum = mapTileNum[i][col][row];
                int x = gp.tileSize * col;
                int y = gp.tileSize * row;
                g2.drawImage(tile[tileNum].image, x, y, null);

                col++;
                if(col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            g2.dispose();
        }
    }

    public void drawFullMapScreen(Graphics2D g2) {

        // Background color
        g2.setColor(Color.black);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Draw map
        int width = 500;
        int height = 500;
        int x = (gp.screenWidth / 2) - (width / 2);
        int y = (gp.screenHeight / 2) - (height / 2);
        g2.drawImage(worldMap[gp.currentMap], x, y, width, height, null);

        // Draw player
        double scale = (double) (gp.tileSize * gp.maxWorldCol) / width;
        int playerX = (int) (x + gp.player.worldX / scale);
        int playerY = (int) (y + gp.player.worldY / scale);
        int playerSize = gp.tileSize / 3;
        g2.drawImage(gp.player.down1, playerX - 6, playerY - 6, playerSize, playerSize, null);

        // Hint
        g2.setFont(gp.ui.maruMonica.deriveFont(32f));
        g2.setColor(Color.white);
        g2.drawString("[M] Close", 30, 550);
    }

    public void drawMiniMap(Graphics2D g2) {

        if(miniMapOn) {

            // Draw map
            int width = 200;
            int height = 200;
            int x = gp.screenWidth - width - 50;
            int y = 50;

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
            g2.drawImage(worldMap[gp.currentMap], x, y, width, height, null);

            // Draw player
            double scale = (double) (gp.tileSize * gp.maxWorldCol) / width;
            int playerX = (int) (x + gp.player.worldX / scale);
            int playerY = (int) (y + gp.player.worldY / scale);
            int playerSize = gp.tileSize / 6;

            // Shadow
            g2.setColor(Color.black);
            g2.fillRect(playerX - 1, playerY - 1, playerSize, playerSize);
            g2.setColor(Color.white);
            g2.fillRect(playerX - 2, playerY - 2, playerSize, playerSize);

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }
}
