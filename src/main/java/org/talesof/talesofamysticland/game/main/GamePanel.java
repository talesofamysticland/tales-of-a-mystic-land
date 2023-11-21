package org.talesof.talesofamysticland.game.main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;

import org.talesof.talesofamysticland.game.Game;
import org.talesof.talesofamysticland.game.AI.PathFinder;
import org.talesof.talesofamysticland.game.data.SaveLoad;
import org.talesof.talesofamysticland.game.entity.Entity;
import org.talesof.talesofamysticland.game.entity.PlayerCharacter;
import org.talesof.talesofamysticland.game.environment.EnvironmentManager;
import org.talesof.talesofamysticland.game.tile.TileManager;
import org.talesof.talesofamysticland.game.tile.WorldMap;
import org.talesof.talesofamysticland.game.tiles_interactive.InteractiveTile;
import org.talesof.talesofamysticland.service.GameService;

public class GamePanel extends JPanel implements Runnable {

    private GameService gameService;

    // Window configurations
    final int originalTileSize = 16;  // 16x16
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;  // 48x48
    public final int maxScreenCol = 20; //5x4, 16x9, 21x9
    public final int maxScreenRow = 12; //5x4, 16x9, 21x9
    public final int screenWidth = tileSize * maxScreenCol;  // 960px
    public final int screenHeight = tileSize * maxScreenRow;  // 576px

    // World Configuration
    public int maxWorldCol = 50;
    public int maxWorldRow = 50;
    public final int maxMap = 10;
    public int currentMap = 0;

    // For full screen
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fullScreenOn = false;

    // Fps
    int FPS = 60;

    // System
    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound se = new Sound();
    Sound music = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    public PathFinder pFinder = new PathFinder(this);
    public EntityGenerator eGenerator = new EntityGenerator(this);
    EnvironmentManager eManager = new EnvironmentManager(this);
    WorldMap map = new WorldMap(this);
    SaveLoad saveLoad = new SaveLoad(this);
    public Config config = new Config(this);
    Thread gameThread;

    // Entity and object
    public PlayerCharacter player;
    public Entity[][] obj = new Entity[maxMap][20];
    public Entity[][] npc = new Entity[maxMap][10];
    public Entity[][] monster = new Entity[maxMap][20];
    public InteractiveTile[][] iTile = new InteractiveTile[maxMap][50];
    public ArrayList<Entity> particleList = new ArrayList<>();
    // public ArrayList<Entity> projectileList = new ArrayList<>();
    public Entity[][] projectile = new Entity[maxMap][20];
    ArrayList<Entity> entityList = new ArrayList<>();

    // Game state
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int optionsState = 5;
    public final int gameOverState = 6;
    public final int transitionState = 7;
    public final int tradeState = 8;
    public final int sleepState = 9;
    public final int mapState = 10;

    // Area state
    public int currentArea;
    public int nextArea;
    public final int outside = 50;
    public final int indoor = 51;
    public final int dungeon = 52;

    public GamePanel(GameService gameService) {

        this.gameService = gameService;

        switch(gameService.getCharacterClass()) {
            case "Warrior" -> player = new Warrior(this, keyH);
            case "Wizard" -> player = new Wizard(this, keyH);
            case "Archer" -> player = new Archer(this, keyH);
        }

        this.setPreferredSize(new DimensionUIResource(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame () {
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setInteractiveTile();
        eManager.setup();

        gameState = playState;
        currentArea = outside;

        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D) tempScreen.getGraphics();

        if(fullScreenOn) {
            setFullScreen();
        }
    }

    public void resetGame(boolean restart) {
        player.setDefaultPositions();
        player.restoreStatus();
        player.resetCounter();
        aSetter.setNPC();
        aSetter.setMonster();

        if(restart) {
            player.setDefaultValues();
            aSetter.setObject();
            aSetter.setInteractiveTile();
            eManager.lighting.resetDay();
        }
    }

    public void setFullScreen() {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        Game.window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        screenWidth2 = (int) width;
        screenHeight2 = (int) height;
    }

    public void changeArea() {

        if(nextArea != currentArea) {
            stopMusic();

            if(nextArea == outside) {
                playMusic(0);
            }
            if(nextArea == indoor) {
                playMusic(18);
            }
            if(nextArea == dungeon) {
                playMusic(19);
            }
        }
        currentArea = nextArea;
        aSetter.setMonster();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {

        double drawInterval = 1000000000 / (double) FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;

        while(gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                drawToTempScreen(); // draw everything to the buffered image
                drawToScreen(); // the buffered image to the screen
                delta--;
            }

            if(timer >= 1000000000) {
                timer = 0;
            }
        }
    }

    public void update() {
        if(gameState == playState) {
            player.update();

            for(int i = 0; i < npc[1].length; i++) {
                if(npc[currentMap][i] != null) {
                    npc[currentMap][i].update();
                }
            }

            for (int i = 0; i < monster[1].length; i++) {
                if(monster[currentMap][i] != null) {
                    if(monster[currentMap][i].alive && !monster[currentMap][i].dying) {
                        monster[currentMap][i].update();
                    }
                    if(!monster[currentMap][i].alive) {
                        monster[currentMap][i].checkDrop();
                        monster[currentMap][i] = null;
                    }
                }
            }

            for (int i = 0; i < projectile[1].length; i++) {
                if(projectile[currentMap][i] != null) {
                    if(projectile[currentMap][i].alive) {
                        projectile[currentMap][i].update();
                    }
                    if(!projectile[currentMap][i].alive) {
                        projectile[currentMap][i] = null;
                    }
                }
            }

            for (int i = 0; i < particleList.size(); i++) {
                if(particleList.get(i) != null) {
                    if(particleList.get(i).alive) {
                        particleList.get(i).update();
                    }
                    if(!particleList.get(i).alive) {
                        particleList.remove(i);
                    }
                }
            }

            for (int i = 0; i < iTile[1].length; i++) {
                if (iTile[currentMap][i] != null) {
                    iTile[currentMap][i].update();
                }
            }

            eManager.update();
        }
    }

    public void drawToTempScreen() {

        // Debug
        long drawStart = 0;

        if(keyH.checkDrawTime) {
            drawStart = System.nanoTime();
        }

        // Tile
        tileM.draw(g2);

        // Interactive tile
        for (int i = 0; i < iTile[1].length; i++) {
            if (iTile[currentMap][i] != null) {
                iTile[currentMap][i].draw(g2);
            }
        }

        // add entities to the list
        entityList.add(player);

        for (int i = 0; i < npc[1].length; i++) {
            if (npc[currentMap][i] != null) {
                entityList.add(npc[currentMap][i]);
            }
        }

        for (int i = 0; i < obj[1].length; i++) {
            if (obj[currentMap][i] != null) {
                entityList.add(obj[currentMap][i]);
            }
        }

        for (int i = 0; i < monster[1].length; i++) {
            if (monster[currentMap][i] != null) {
                entityList.add(monster[currentMap][i]);
            }
        }

        for(int i = 0; i < projectile[1].length; i++) {
            if(projectile[currentMap][i] != null) {
                entityList.add(projectile[currentMap][i]);
            }
        }

        for (Entity entity : particleList) {
            if (entity != null) {
                entityList.add(entity);
            }
        }

        // sort
        entityList.sort(Comparator.comparingInt(e -> e.worldY));

        // draw entities
        for(Entity en : entityList) {
            en.draw(g2);
        }

        // empty entity list
        entityList.clear();

        // Environment
        eManager.draw(g2);

        // Mini map
        map.drawMiniMap(g2);

        // Map
        if(gameState == mapState) {
            map.drawFullMapScreen(g2);
        }

        // UI
        ui.draw(g2);

        // Debug
        if(keyH.checkDrawTime) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw time: " + passed, 10, 400);
            System.out.println("Draw time: " + passed);
        }
    }

    public void drawToScreen() {

        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void resumeMusic() {
        music.resume();
    }

    public void playSoundEffect(int i) {
        se.setFile(i);
        se.play();
    }
}
