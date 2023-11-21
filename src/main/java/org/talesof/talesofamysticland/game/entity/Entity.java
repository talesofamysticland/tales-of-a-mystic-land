package org.talesof.talesofamysticland.game.entity;

import org.talesof.talesofamysticland.game.Game;
import org.talesof.talesofamysticland.game.main.GamePanel;
import org.talesof.talesofamysticland.game.main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Entity {

    protected GamePanel gp;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2,
            attackLeft1, attackLeft2, attackRight1, attackRight2,
            guardUp, guardDown, guardLeft, guardRight;
    public BufferedImage image, image2, image3;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    public String[][] dialogues = new String[20][20];
    public Entity attacker;

    // State
    public int worldX, worldY;
    public String direction = "down";
    public int spriteNum = 1;
    public int spriteNumVertical = 3;
    public int spriteNumHorizontal = 1;
    public int dialogueSet = 0;
    public int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible = false;
    public boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    boolean hpBarOn = false;
    public boolean onPath = false;
    public boolean knockBack = false;
    public String knockBackDirection;
    public boolean guarding = false;
    public boolean transparent = false;
    public boolean offBalance = false;

    // Counter
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    public int shotAvailableCounter = 0;
    int dyingCounter = 0;
    int hpBarCounter = 0;
    int knockBackCounter = 0;
    int standCounter = 0;
    public int guardCounter = 0;
    int offBalanceCounter = 0;

    // Character Attributes
    public String name;
    public int defaultSpeed;
    public int speed;
    public int maxLife;
    public int life;
    public int maxMana;
    public int mana;
    public int ammo;
    public int level;

    public int strength;
    public int resistance;
    public int constitution;
    public int dexterity;
    public int wisdom;

    public double attack;
    public double magic;
    public double defense;

    public int exp;
    public int nextLevelExp;
    public int coin;
    public int motion1Duration;
    public int motion2Duration;
    public Entity currentWeapon;
    public Entity currentShield;
    public Entity currentLight;
    public Projectile projectile;

    // Item attributes
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;
    public double attackValue;
    public double defenseValue;
    public String description = "";
    public int useCost;
    public int price;
    public int knockBackPower = 0;
    public boolean stackable = false;
    public int amount = 1;
    public int lightRadius;
    public Entity loot;
    public boolean opened = false;

    // Type
    public int type = 0;
    public final int typePlayer = 1;
    public final int typeNPC = 2;
    public final int typeMonster = 3;
    public final int typeSword = 4;
    public final int typeAxe = 5;
    public final int typeShield = 6;
    public final int typeConsumable = 7;
    public final int typePickup = 8;
    public final int typeObstacle = 9;
    public final int typeLight = 10;
    public final int typeStaff = 11;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public int getLeftX() {
        return worldX + solidArea.x;
    }

    public int getRightX() {
        return worldX + solidArea.x + solidArea.width;
    }

    public int getTopY() {
        return worldY + solidArea.y;
    }

    public int getBottomY() {
        return worldY + solidArea.y + solidArea.height;
    }

    public int getCol() {
        return (worldX + solidArea.x) / gp.tileSize;
    }

    public int getRow() {
        return (worldY + solidArea.y) / gp.tileSize;
    }

    public int getXDistance(Entity target) {
        return Math.abs(worldX - target.worldX);
    }

    public int getYDistance(Entity target) {
        return Math.abs(worldY - target.worldY);
    }

    public int getTileDistance(Entity target) {
        return (getXDistance(target) + getYDistance(target)) / gp.tileSize;
    }

    public int getGoalCol(Entity target) {
        return (target.worldX + target.solidArea.x) / gp.tileSize;
    }

    public int getGoalRow(Entity target) {
        return (target.worldY + target.solidArea.y) / gp.tileSize;
    }

    public void setLoot(Entity loot) { }

    public void setAction() { }

    public void resetCounter() {
        spriteCounter = 0;
        actionLockCounter = 0;
        invincibleCounter = 0;
        shotAvailableCounter = 0;
        dyingCounter = 0;
        hpBarCounter = 0;
        knockBackCounter = 0;
        standCounter = 0;
        guardCounter = 0;
        offBalanceCounter = 0;
    }

    public void interact() { }

    public void damageReaction() { }

    public void speak() {

    }

    public void facePlayer() {
        switch(gp.player.direction) {
            case "up" -> direction = "down";
            case "down" -> direction = "up";
            case "left" -> direction = "right";
            case "right" -> direction = "left";
        }
    }

    public void startDialogue(Entity entity, int setNum) {
        gp.gameState = gp.dialogueState;
        gp.ui.npc = entity;
        dialogueSet = setNum;
    }

    public boolean use(Entity entity) { return false; }

    public void checkDrop() { }

    public void dropItem(Entity droppedItem) {

        for(int i = 0; i < gp.obj[1].length; i++) {
            if(gp.obj[gp.currentMap][i] == null) {
                gp.obj[gp.currentMap][i] = droppedItem;
                gp.obj[gp.currentMap][i].worldX = worldX;
                gp.obj[gp.currentMap][i].worldY = worldY;
                break;
            }
        }
    }

    public Color getParticleColor() {
        return null;
    }

    public int getParticleSize() {
        return 0;
    }

    public int getParticleSpeed() {
        return 0;
    }

    public int getParticleMaxLife() {
        return 0;
    }

    public void generateParticle(Entity generator, Entity target) {

        Color color = generator.getParticleColor();
        int size = generator.getParticleSize();
        int speed = generator.getParticleSpeed();
        int maxLife = generator.getParticleMaxLife();

        Particle p1 = new Particle(gp, target, color, size, speed, maxLife, -2, -1);
        Particle p2 = new Particle(gp, target, color, size, speed, maxLife, 2, -1);
        Particle p3 = new Particle(gp, target, color, size, speed, maxLife, -2, 1);
        Particle p4 = new Particle(gp, target, color, size, speed, maxLife, 2, 1);
        gp.particleList.add(p1);
        gp.particleList.add(p2);
        gp.particleList.add(p3);
        gp.particleList.add(p4);
    }

    public void checkStartChasing(Entity target, int distance, int rate) {

        if(getTileDistance(target) < distance) {
            int i = new Random().nextInt(rate);
            if(i == 0) {
                onPath = true;
            }
        }
    }

    public void checkStopChasing(Entity target, int distance, int rate) {

        if(getTileDistance(target) > distance) {
            int i = new Random().nextInt(rate);
            if(i == 0) {
                onPath = false;
            }
        }
    }

    public void checkShot(int rate, int shotInterval) {
        // Check if it shots a projectile
        int i = new Random().nextInt(rate);

        if(i == 0 && !projectile.alive && shotAvailableCounter == shotInterval) {
            projectile.set(worldX, worldY, direction, true, this);

            for(int j = 0; j < gp.projectile[1].length; j++) {
                if(gp.projectile[gp.currentMap][j] == null) {
                    gp.projectile[gp.currentMap][j] = projectile;
                    break;
                }
            }

            shotAvailableCounter = 0;
        }
    }

    public void getRandomDirection() {

        actionLockCounter++;

        if(actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if(i <= 25) direction = "up";
            if(i > 25 && i <= 50) direction = "down";
            if(i > 50 && i <= 75) direction = "left";
            if(i > 75) direction = "right";

            actionLockCounter = 0;
        }
    }

    public String getOppositeDirection(String direction) {
        String oppositeDirection = "";

        switch(direction) {
            case "up" -> oppositeDirection = "down";
            case "down" -> oppositeDirection = "up";
            case "left" -> oppositeDirection = "right";
            case "right" -> oppositeDirection = "left";
        }

        return oppositeDirection;
    }

    public void damagePlayer(double attack) {
        if(!gp.player.invincible) {

            double damage = attack - gp.player.defense;

            // Get an opposite direction
            String canGuardDirection = getOppositeDirection(direction);

            if(gp.player.guarding && gp.player.direction.equals(canGuardDirection)) {

                // Parry
                if(gp.player.guardCounter < 10) {
                    damage = 0;
                    gp.playSoundEffect(16);
                    setKnockBack(this, gp.player, knockBackPower);
                    offBalance = true;
                    spriteCounter -= 80;
                }
                else {
                    damage /= 3;
                    gp.playSoundEffect(15);
                }
            }
            else {
                gp.playSoundEffect(6);

                if(damage < 1) {
                    damage = 1;
                }
            }

            if(damage != 0 && gp.player.life - damage > 0) {
                gp.player.transparent = true;
                setKnockBack(gp.player, this, this.knockBackPower);
            }

            gp.player.life -= damage;
            gp.player.invincible = true;
        }
    }

    public void setKnockBack(Entity target, Entity attacker, int knockBackPower) {
        this.attacker = attacker;
        target.knockBackDirection = attacker.direction;
        target.speed += knockBackPower;
        target.knockBack = true;
    }

    public void searchPath(int goalCol, int goalRow) {

        int startCol = (worldX + solidArea.x) / gp.tileSize;
        int startRow = (worldY + solidArea.y) / gp.tileSize;

        gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow);

        if(gp.pFinder.search()) {

            // Next world X and world Y
            int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;

            // Entity's solid area position
            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;

            if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "up";
            }
            else if(enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "down";
            }
            else if(enTopY >= nextY && enBottomY < nextY + gp.tileSize) {
                // left or right
                if(enLeftX > nextX) {
                    direction = "left";
                }
                if(enLeftX < nextX) {
                    direction = "right";
                }
            }
            else if(enTopY > nextY && enLeftX > nextX) {
                direction = "up";
                checkCollision();
                if(collisionOn) {
                    direction = "left";
                }
            }
            else if(enTopY > nextY && enLeftX < nextX) {
                direction = "up";
                checkCollision();
                if(collisionOn) {
                    direction = "right";
                }
            }
            else if(enTopY < nextY && enLeftX > nextX) {
                direction = "down";
                checkCollision();
                if(collisionOn) {
                    direction = "left";
                }
            }
            else if(enTopY < nextY && enLeftX < nextX) {
                direction = "down";
                checkCollision();
                if(collisionOn) {
                    direction = "right";
                }
            }

            int nextCol = gp.pFinder.pathList.get(0).col;
            int nextRow = gp.pFinder.pathList.get(0).row;
            if(nextCol == goalCol && nextRow == goalRow) {
                onPath = false;
            }
        }
    }

    public void searchPlayer(int goalCol, int goalRow) {

        int startCol = (worldX + solidArea.x) / gp.tileSize;
        int startRow = (worldY + solidArea.y) / gp.tileSize;

        gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow);

        if(gp.pFinder.search()) {

            // Next world X and world Y
            int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;

            // Entity's solid area position
            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;

            if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "up";
            }
            else if(enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "down";
            }
            else if(enTopY >= nextY && enBottomY < nextY + gp.tileSize) {
                // left or right
                if(enLeftX > nextX) {
                    direction = "left";
                }
                if(enLeftX < nextX) {
                    direction = "right";
                }
            }
            else if(enTopY > nextY && enLeftX > nextX) {
                direction = "up";
                checkCollision();
                if(collisionOn) {
                    direction = "left";
                }
            }
            else if(enTopY > nextY && enLeftX < nextX) {
                direction = "up";
                checkCollision();
                if(collisionOn) {
                    direction = "right";
                }
            }
            else if(enTopY < nextY && enLeftX > nextX) {
                direction = "down";
                checkCollision();
                if(collisionOn) {
                    direction = "left";
                }
            }
            else if(enTopY < nextY && enLeftX < nextX) {
                direction = "down";
                checkCollision();
                if(collisionOn) {
                    direction = "right";
                }
            }
        }
    }

    public int getDetected(Entity user, Entity[][] target, String targetName) {

        int index = 999;

        // Check the surrounding objects
        int nextWorldX = user.getLeftX();
        int nextWorldY = user.getTopY();

        switch(user.direction) {
            case "up" -> nextWorldY = user.getTopY() - gp.player.speed;
            case "down" -> nextWorldY = user.getBottomY() + gp.player.speed;
            case "left" -> nextWorldX = user.getLeftX() - gp.player.speed;
            case "right" -> nextWorldX = user.getRightX() + gp.player.speed;
        }

        int col = nextWorldX / gp.tileSize;
        int row = nextWorldY / gp.tileSize;

        for(int i = 0; i < target[1].length; i++) {
            Entity trg = target[gp.currentMap][i];
            if(trg != null) {
                if(trg.getCol() == col && trg.getRow() == row && trg.name.equals(targetName)) {
                    index = i;
                    break;
                }
            }
        }

        return index;
    }

    public void checkAttack(int rate, int straight, int horizontal) {

        boolean targetInRange = false;

        int xDistance = getXDistance(gp.player);
        int yDistance = getYDistance(gp.player);

        switch(direction) {
            case "up" -> {
                if(gp.player.worldY < worldY && yDistance < straight && xDistance < horizontal) {
                    targetInRange = true;
                }
            }
            case "down" -> {
                if(gp.player.worldY > worldY && yDistance < straight && xDistance < horizontal) {
                    targetInRange = true;
                }
            }
            case "left" -> {
                if(gp.player.worldX < worldX && xDistance < straight && yDistance < horizontal) {
                    targetInRange = true;
                }
            }
            case "right" -> {
                if(gp.player.worldX > worldX && xDistance < straight && yDistance < horizontal) {
                    targetInRange = true;
                }
            }
        }

        if(targetInRange) {
            // Check if it initiates an attack
            int i = new Random().nextInt(rate);
            if(i == 0) {
                attacking = true;
                spriteNum = 1;
                spriteNumHorizontal = 1;
                spriteNumVertical = 1;
                spriteCounter = 0;
                shotAvailableCounter = 0;
            }
        }
    }

    public void attacking() {
        spriteCounter++;

        if(spriteCounter <= motion1Duration) {
            spriteNum = 1;
            spriteNumHorizontal = 1;
            spriteNumVertical = 1;
        }

        if(spriteCounter > motion1Duration && spriteCounter <= motion2Duration) {
            spriteNum = 2;
            spriteNumHorizontal = 4;
            spriteNumVertical = 3;

            // Save the current data
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // Adjust player's worldX/Y for the attackArea

            switch (direction) {
                case "up" -> worldY -= attackArea.height;
                case "down" -> worldY += attackArea.height;
                case "left" -> worldX -= attackArea.width;
                case "right" -> worldX += attackArea.width;
            }

            // attackArea become solidArea
            solidArea.width = attackArea.width;
            solidArea.height= attackArea.height;

            if(type == typeMonster) {

                if(gp.cChecker.checkPlayer(this)) {
                    damagePlayer(attack);
                }
            }
            else {
                // Check monster collision with the updated data
                int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
                gp.player.damageMonster(monsterIndex, this, attack, currentWeapon.knockBackPower);

                int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
                gp.player.damageInteractiveTile(iTileIndex);

                int projectileIndex = gp.cChecker.checkEntity(this, gp.projectile);
                if(projectile.user != gp.player) {
                    gp.player.damageProjectile(projectileIndex);
                }
            }

            // Restore the original data
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }

        if(spriteCounter > motion2Duration) {
            spriteNum = 1;
            spriteNumHorizontal = 1;
            spriteNumVertical = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void checkCollision() {
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        gp.cChecker.checkEntity(this, gp.iTile);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if(this.type == typeMonster && contactPlayer) {
            damagePlayer(attack);
        }
    }

    public void update() {

        if(knockBack) {
            checkCollision();

            if(collisionOn) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;

            } else {
                switch(knockBackDirection) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }

            knockBackCounter++;
            if(knockBackCounter == 10) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
        }
        else if(attacking) {
            attacking();
        }
        else {
            setAction();
            checkCollision();

            if(!collisionOn) {
                switch(direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }

            spriteCounter++;

            if(spriteCounter > 24) {
                if(spriteNum == 1) {
                    spriteNum = 2;
                } else if(spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

        if(invincible) {
            invincibleCounter++;
            if(invincibleCounter > 50) {
                invincible = false;
                transparent = false;
                invincibleCounter = 0;
            }
        }

        if(shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }

        if(offBalance) {
            offBalanceCounter++;

            if(offBalanceCounter > 60) {
                offBalance = false;
                offBalanceCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            int tempScreenX = screenX;
            int tempScreenY = screenY;

            switch (direction) {
                case "up" -> {
                    if(!attacking) {
                        if (spriteNum == 1) image = up1;
                        if (spriteNum == 2) image = up2;
                    }
                    if(attacking) {
                        tempScreenY = screenY - gp.tileSize;
                        if (spriteNum == 1) image = attackUp1;
                        if (spriteNum == 2) image = attackUp2;
                    }
                }
                case "down" -> {
                    if(!attacking) {
                        if (spriteNum == 1) image = down1;
                        if (spriteNum == 2) image = down2;
                    }
                    if(attacking) {
                        if (spriteNum == 1) image = attackDown1;
                        if (spriteNum == 2) image = attackDown2;
                    }
                }
                case "left" -> {
                    if(!attacking) {
                        if (spriteNum == 1) image = left1;
                        if (spriteNum == 2) image = left2;
                    }
                    if(attacking) {
                        tempScreenX = screenX - gp.tileSize;
                        if (spriteNum == 1) image = attackLeft1;
                        if (spriteNum == 2) image = attackLeft2;
                    }
                }
                case "right" -> {
                    if(!attacking) {
                        if (spriteNum == 1) image = right1;
                        if (spriteNum == 2) image = right2;
                    }
                    if(attacking) {
                        if (spriteNum == 1) image = attackRight1;
                        if (spriteNum == 2) image = attackRight2;
                    }
                }
            }

            // Monster HP bar
            if(type == typeMonster && hpBarOn) {

                double oneScale = (double) gp.tileSize / maxLife;
                double hpBarValue = oneScale * life;

                g2.setColor(new Color(35, 35, 35));
                g2.fillRect(screenX - 2, screenY - 16, gp.tileSize + 3, 9);

                g2.setColor(new Color(255, 0, 30));
                g2.fillRect(screenX, screenY - 14, (int)hpBarValue, 6);

                hpBarCounter++;

                if(hpBarCounter > 600) {
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }

            if(invincible) {
                hpBarOn = true;
                hpBarCounter = 0;
                changeAlpha(g2, 0.4f);
            }
            if(dying) {
                dyingAnimation(g2);
            }

            g2.drawImage(image, tempScreenX, tempScreenY, null);

            changeAlpha(g2, 1f);
        }
    }

    public void dyingAnimation(Graphics2D g2) {
        dyingCounter++;

        int i = 5;

        if(dyingCounter <= i) { changeAlpha(g2, 0f); }
        if(dyingCounter > i && dyingCounter <= i * 2) { changeAlpha(g2, 1f); }
        if(dyingCounter > i * 2 && dyingCounter <= i * 3) { changeAlpha(g2, 0f); }
        if(dyingCounter > i * 3 && dyingCounter <= i * 4) { changeAlpha(g2, 1f); }
        if(dyingCounter > i * 4 && dyingCounter <= i * 5) { changeAlpha(g2, 0f); }
        if(dyingCounter > i * 5 && dyingCounter <= i * 6) { changeAlpha(g2, 1f); }
        if(dyingCounter > i * 6 && dyingCounter <= i * 7) { changeAlpha(g2, 0f); }
        if(dyingCounter > i * 7 && dyingCounter <= i * 8) { changeAlpha(g2, 1f); }

        if(dyingCounter > i * 8) {
            alive = false;
        }
    }

    public void changeAlpha(Graphics2D g2, float alphaValue) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }

    public BufferedImage setup(String imagePath) {
        return setup(imagePath, gp.tileSize, gp.tileSize);
    }

    public BufferedImage setup(String imagePath, int width, int height) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(Game.GAME_ASSETS_PATH + imagePath + ".png")));
            image = uTool.scaleImage(image, width, height);
        } catch(IOException e) {
            e.printStackTrace();
        }

        return image;
    }
}
