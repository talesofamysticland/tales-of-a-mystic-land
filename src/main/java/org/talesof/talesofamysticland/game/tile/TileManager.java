package org.talesof.talesofamysticland.game.tile;

import org.talesof.talesofamysticland.game.Game;
import org.talesof.talesofamysticland.game.main.GamePanel;
import org.talesof.talesofamysticland.game.main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int[][][] mapTileNum;
    ArrayList<String> fileNames = new ArrayList<>();
    ArrayList<String> collisionStatus = new ArrayList<>();

    public TileManager(GamePanel gp) {

        this.gp = gp;
        
        InputStream is = getClass().getResourceAsStream(Game.GAME_ASSETS_PATH + "/maps/tiledata.txt");
        
        assert is != null;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line;

        try {

            while((line = br.readLine()) != null) {
                fileNames.add(line);
                collisionStatus.add(br.readLine());
            }
            br.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        tile = new Tile[fileNames.size()];
        getTileImage();

        is = getClass().getResourceAsStream(Game.GAME_ASSETS_PATH + "/maps/worldmap.txt");
        assert is != null;
        br = new BufferedReader(new InputStreamReader(is));

        try {
            String mapLine = br.readLine();
            String[] maxTile = mapLine.split(" ");

            gp.maxWorldCol = maxTile.length;
            gp.maxWorldRow = gp.maxWorldCol;
            mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

            br.close();

        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        loadMap(Game.GAME_ASSETS_PATH + "/maps/worldmap.txt", 0);
        loadMap(Game.GAME_ASSETS_PATH + "/maps/indoor01.txt", 1);
        loadMap(Game.GAME_ASSETS_PATH + "/maps/dungeon01.txt", 2);
        loadMap(Game.GAME_ASSETS_PATH + "/maps/dungeon02.txt", 3);
    }

    public void getTileImage() {

        for(int i = 0; i < fileNames.size(); i++) {

            String fileName;
            boolean collision;

            fileName = fileNames.get(i);

            collision = collisionStatus.get(i).equals("true");

            setup(i, fileName, collision);
        }
    }

    public void setup(int index, String imageName, boolean collision) {
        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(Game.GAME_ASSETS_PATH + "/tiles/" + imageName)));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath, int map) {

        try {
            InputStream is = Objects.requireNonNull(getClass().getResourceAsStream(filePath));
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();

                while(col < gp.maxWorldCol) {

                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[map][col][row] = num;
                    col++;
                }

                if(col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }

            worldCol++;

            if(worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
