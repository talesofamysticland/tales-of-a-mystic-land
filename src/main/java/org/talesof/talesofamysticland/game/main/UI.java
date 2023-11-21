package org.talesof.talesofamysticland.game.main;

import org.talesof.talesofamysticland.game.Game;
import org.talesof.talesofamysticland.game.entity.Entity;
import org.talesof.talesofamysticland.game.object.OBJ_BronzeCoin;
import org.talesof.talesofamysticland.game.object.OBJ_Heart;
import org.talesof.talesofamysticland.game.object.OBJ_ManaCrystal;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    public Font maruMonica;
    BufferedImage heart_full, heart_half, heart_blank, crystal_full, crystal_blank, coin;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public String currentDialogue = "";
    public int commandNum = 0;
    public int playerSlotCol = 0;
    public int playerSlotRow = 0;
    public int npcSlotCol = 0;
    public int npcSlotRow = 0;
    final public int minSlotRow = 0;
    final public int maxSlotRow = 3;
    final public int minSlotCol = 0;
    final public int maxSlotCol = 4;
    int subState = 0;
    int counter = 0;
    public Entity npc;
    int charIndex = 0;
    String combinedText = "";

    public UI(GamePanel gp)  {
        this.gp = gp;

        try {
            InputStream is = Objects.requireNonNull(getClass().getResourceAsStream(Game.GAME_ASSETS_PATH + "/fonts/x12y16pxMaruMonica.ttf"));
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        // Create HUD Object
        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;

        Entity crystal = new OBJ_ManaCrystal(gp);
        crystal_full = crystal.image;
        crystal_blank = crystal.image2;

        Entity bronze_coin = new OBJ_BronzeCoin(gp);
        coin = bronze_coin.down1;
    }

    public void addMessage(String text) {
        message.add(text);
        messageCounter.add(0);
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(maruMonica);
        g2.setColor(Color.white);

        // Play State
        if(gp.gameState == gp.playState) {
            drawPlayerLifeAndMana();
            drawMessage();
        }

        // Pause State
        if(gp.gameState == gp.pauseState) {
            drawPlayerLifeAndMana();
            drawPauseScreen();
        }

        // Dialogue State
        if(gp.gameState == gp.dialogueState) {
            drawPlayerLifeAndMana();
            drawDialogueScreen();
        }

        // Character State
        if(gp.gameState == gp.characterState) {
            drawCharacterScreen();
            drawInventory(gp.player, true);
        }

        // Options State
        if(gp.gameState == gp.optionsState) {
            drawOptionsScreen();
        }

        // Game over State
        if(gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
        }

        // Transition State
        if(gp.gameState == gp.transitionState) {
            drawTransition();
        }

        if(gp.gameState == gp.tradeState) {
            drawTradeScreen();
        }

        if(gp.gameState == gp.sleepState) {
            drawSleepScreen();
        }
    }

    private void drawPlayerLifeAndMana() {

        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;

        // Draw max life
        while(i < gp.player.maxLife / 2) {
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }

        // Reset
        x = gp.tileSize / 2;
        i = 0;

        // Draw current life
        while(i < gp.player.life) {
            g2.drawImage(heart_half, x, y, null);
            i++;

            if(i < gp.player.life) {
                g2.drawImage(heart_full, x, y, null);
            }

            i++;
            x += gp.tileSize;
        }

        // Draw max mana
        x = (gp.tileSize / 2) - 5;
        y = (int) (gp.tileSize * 1.5);
        i = 0;

        while(i < gp.player.maxMana) {
            g2.drawImage(crystal_blank, x, y, null);
            i++;
            x += 35;
        }

        // Draw mana
        x = (gp.tileSize / 2) - 5;
        y = (int) (gp.tileSize * 1.5);
        i = 0;

        while(i < gp.player.mana) {
            g2.drawImage(crystal_full, x, y, null);
            i++;
            x += 35;
        }
    }

    public void drawMessage() {

        int messageX = gp.tileSize;
        int messageY = gp.tileSize * 4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));

        for(int i = 0; i < message.size(); i++) {
            if(message.get(i) != null) {
                g2.setColor(Color.black);
                g2.drawString(message.get(i), messageX + 2, messageY + 2);
                g2.setColor(Color.white);
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1;
                messageCounter.set(i ,counter);
                messageY += 50;

                if(messageCounter.get(i) > 180) {
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }

    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen() {
        int x = gp.tileSize * 3;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 6);
        int height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += gp.tileSize;
        y += gp.tileSize;

        if(npc.dialogues[npc.dialogueSet][npc.dialogueIndex] != null) {

            char[] characters = npc.dialogues[npc.dialogueSet][npc.dialogueIndex].toCharArray();

            if(charIndex < characters.length) {
                gp.playSoundEffect(17);
                String s = String.valueOf(characters[charIndex]);
                combinedText += s;
                currentDialogue = combinedText;
                charIndex++;
            }

            if(gp.keyH.enterPressed) {

                charIndex = 0;
                combinedText = "";
                if(gp.gameState == gp.dialogueState) {
                    npc.dialogueIndex++;
                    gp.keyH.enterPressed = false;
                }
            }
        }
        else {
            npc.dialogueIndex = 0;

            if(gp.gameState == gp.dialogueState) {
                gp.gameState = gp.playState;
            }
        }

        splitLine(currentDialogue, x, y);
    }

    public void drawCharacterScreen() {

        // Create frame
        final int frameX = gp.tileSize * 2;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize * 5;
        final int frameHeight = gp.tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // Text
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 35;

        // Names
        g2.drawString("Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Life", textX, textY);
        textY += lineHeight;
        g2.drawString("Mana", textX, textY);
        textY += lineHeight;
        g2.drawString("Strength", textX, textY);
        textY += lineHeight;
        g2.drawString("Dexterity", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack", textX, textY);
        textY += lineHeight;
        g2.drawString("Defense", textX, textY);
        textY += lineHeight;
        g2.drawString("Exp.", textX, textY);
        textY += lineHeight;
        g2.drawString("Next Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Coin", textX, textY);
        textY += lineHeight + 10;
        g2.drawString("Weapon", textX, textY);
        textY += lineHeight + 15;
        g2.drawString("Shield", textX, textY);

        // Values
        int tailX = (frameX + frameWidth) - 30;
        textY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = gp.player.life + "/" + gp.player.maxLife;
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = gp.player.mana + "/" + gp.player.maxMana;
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.strength);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.dexterity);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.attack);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.defense);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.exp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.coin);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY - 24, null);
        textY += gp.tileSize;

        if(gp.player.currentShield != null) {
            g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY - 24, null);
        }
    }

    public void drawInventory(Entity entity, boolean cursor) {

        int frameX;
        int frameY;
        int frameWidth;
        int frameHeight;
        int slotCol;
        int slotRow;

        if(entity == gp.player) {
            frameX = gp.tileSize * 12;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize * 6;
            frameHeight = gp.tileSize * 5;
            slotCol = playerSlotCol;
            slotRow = playerSlotRow;

        } else {
            frameX = gp.tileSize * 2;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize * 6;
            frameHeight = gp.tileSize * 5;
            slotCol = npcSlotCol;
            slotRow = npcSlotRow;
        }

        // Frame
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // Slot
        final int slotXStart = frameX + 20;
        final int slotYStart = frameY + 20;
        int slotX = slotXStart;
        int slotY = slotYStart;
        int slotSize = gp.tileSize + 3;

        // Draw Player's items
        int rowNumber = 1;
        int cont = 0;

        for(int i = 0; i < entity.inventory.size(); i++) {

            // Equip cursor
            if(entity.inventory.get(i) == entity.currentWeapon ||
                    entity.inventory.get(i) == entity.currentShield ||
                    entity.inventory.get(i) == entity.currentLight) {

                g2.setColor(new Color(240, 190, 90));
                g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
            }

            g2.drawImage(entity.inventory.get(i).down1, slotX, slotY, null);

            // Display amount
            if(entity == gp.player && entity.inventory.get(i).amount > 1) {
                g2.setFont(g2.getFont().deriveFont(32f));
                int amountX;
                int amountY;

                String s = entity.inventory.get(i).amount + "";
                amountX = getXforAlignToRightText(s, slotX + 44);
                amountY = slotY + gp.tileSize;

                // Shadow
                g2.setColor(new Color(60, 60, 60));
                g2.drawString(s, amountX, amountY);

                // Number
                g2.setColor(Color.white);
                g2.drawString(s, amountX - 3, amountY - 3);
            }

            slotX += slotSize;

            if(i == (maxSlotCol * rowNumber) + cont) {
                slotX = slotXStart;
                slotY += slotSize;

                rowNumber++;
                cont++;
            }
        }

        // Cursor
        if(cursor) {
            int cursorX = slotXStart + (slotSize * slotCol);
            int cursorY = slotYStart + (slotSize * slotRow);
            int cursorWidth = gp.tileSize;
            int cursorHeight = gp.tileSize;

            // Draw cursor
            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

            // Description frame
            final int dFrameY = frameY + frameHeight + 10;
            final int dFrameHeight = gp.tileSize * 3;

            // Draw description text
            int textX = frameX + 20;
            int textY = dFrameY + gp.tileSize;
            g2.setFont(g2.getFont().deriveFont(28F));

            int itemIndex = getItemIndexOnSlot(slotCol, slotRow);

            if(itemIndex < entity.inventory.size()) {

                // Draw description frame only if an item is being selected
                drawSubWindow(frameX, dFrameY, frameWidth, dFrameHeight);

                splitLine(entity.inventory.get(itemIndex).description, textX, textY);
            }
        }
    }

    public void drawGameOverScreen() {

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));

        text = "Game Over";
        x = getXForCenteredText(text);
        y = gp.tileSize * 5;

        // Shadow
        g2.setColor(Color.black);
        g2.drawString(text, x, y);

        // Main
        g2.setColor(Color.white);
        g2.drawString(text, x - 4, y - 4);

        // Retry
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Retry";
        x = getXForCenteredText(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);

        if(commandNum == 0) {
            g2.drawString(">", x - 40, y);
        }

        // Back to the title screen
        text = "Quit";
        x = getXForCenteredText(text);
        y += 55;
        g2.drawString(text, x, y);

        if(commandNum == 1) {
            g2.drawString(">", x - 40, y);
        }
    }

    public void drawOptionsScreen() {

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        final int frameX = gp.tileSize * 6;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize * 8;
        final int frameHeight = gp.tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch(subState) {
            case 0 -> options_top(frameX, frameY);
            case 1 -> options_fullScreenNotifications(frameX, frameY);
            case 2 -> options_control(frameX, frameY);
            case 3 -> options_endGameConfirmation(frameX, frameY);
        }

        gp.keyH.enterPressed = false;
    }

    public void options_top(int frameX, int frameY) {

        int textX;
        int textY;

        // Title
        String text = "Options";
        textX = getXForCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        // Full screen on/off
        textX = frameX + gp.tileSize;
        textY += gp.tileSize * 2;
        g2.drawString("Full Screen", textX, textY);
        if(commandNum == 0) {
            g2.drawString(">", textX - 25, textY);

            if(gp.keyH.enterPressed) {
                gp.fullScreenOn = !gp.fullScreenOn;
                subState = 1;
            }
        }

        // Music
        textY += gp.tileSize;
        g2.drawString("Music", textX, textY);
        if(commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
        }

        // SE
        textY += gp.tileSize;
        g2.drawString("Sound Effects", textX, textY);
        if(commandNum == 2) {
            g2.drawString(">", textX - 25, textY);
        }

        // Control
        textY += gp.tileSize;
        g2.drawString("Controls", textX, textY);
        if(commandNum == 3) {
            g2.drawString(">", textX - 25, textY);

            if(gp.keyH.enterPressed) {
                subState = 2;
                commandNum = 0;
            }
        }

        // End game
        textY += gp.tileSize;
        g2.drawString("End Game", textX, textY);
        if(commandNum == 4) {
            g2.drawString(">", textX - 25, textY);

            if(gp.keyH.enterPressed) {
                subState = 3;
                commandNum = 0;
            }
        }

        // Back
        textY += gp.tileSize * 2;
        g2.drawString("Back", textX, textY);
        if(commandNum == 5) {
            g2.drawString(">", textX - 25, textY);

            if(gp.keyH.enterPressed) {
                gp.gameState = gp.playState;
                commandNum = 0;
            }
        }

        // Full screen check box
        textX = (int) (frameX + gp.tileSize * 4.5);
        textY = frameY + (gp.tileSize * 2) + (gp.tileSize / 2);
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, gp.tileSize / 2, gp.tileSize / 2);
        if(gp.fullScreenOn) {
            g2.fillRect(textX, textY, gp.tileSize / 2, gp.tileSize / 2);
        }

        // Music volume
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, gp.tileSize / 2);

        int volumeWidth = 24 * gp.music.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        // SE volume
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, gp.tileSize / 2);

        volumeWidth = 24 * gp.se.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        gp.config.saveConfig();
    }

    public void options_fullScreenNotifications(int frameX, int frameY) {

        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize * 3;

        currentDialogue = "The change will take \neffect after restarting \nthe game";

        splitLine(currentDialogue, textX, textY);

        // Back
        textY = frameY + gp.tileSize * 9;
        g2.drawString("Back", textX, textY);
        if(commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed) {
                subState = 0;
            }
        }
    }

    public void options_control(int frameX, int frameY) {

        int textX;
        int textY;

        // Title
        String text = "Control";
        textX = getXForCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + gp.tileSize;
        textY += gp.tileSize;

        g2.drawString("Move", textX, textY); textY += gp.tileSize;
        g2.drawString("Confirm / Attack", textX, textY); textY += gp.tileSize;
        g2.drawString("Cast", textX, textY); textY += gp.tileSize;
        g2.drawString("Inventory & stats", textX, textY); textY += gp.tileSize;
        g2.drawString("Pause", textX, textY); textY += gp.tileSize;
        g2.drawString("Options", textX, textY);

        textX = frameX + gp.tileSize * 6;
        textY = frameY + gp.tileSize * 2;

        g2.drawString("WASD", textX, textY); textY += gp.tileSize;
        g2.drawString("ENTER", textX, textY); textY += gp.tileSize;
        g2.drawString("F", textX, textY); textY += gp.tileSize;
        g2.drawString("C", textX, textY); textY += gp.tileSize;
        g2.drawString("P", textX, textY); textY += gp.tileSize;
        g2.drawString("ESC", textX, textY);

        // Back
        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize * 9;

        g2.drawString("Back", textX, textY);
        if(commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed) {
                subState = 0;
                commandNum = 3;
            }
        }
    }

    public void options_endGameConfirmation(int frameX, int frameY) {

        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize * 3;

        currentDialogue = "Quit the game and \nreturn to the title screen?";
        splitLine(currentDialogue, textX, textY);

        // Yes

        String text = "Yes";
        textX = getXForCenteredText(text);
        textY += gp.tileSize * 3;
        g2.drawString(text, textX, textY);

        if(commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed) {
                subState = 0;
                gp.resetGame(true);
                gp.music.stop();
            }
        }

        // No

        text = "No";
        textX = getXForCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);

        if(commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed) {
                subState = 0;
                commandNum = 4;
            }
        }
    }

    public void drawTransition() {
        counter++;
        g2.setColor(new Color(0, 0, 0, counter * 5));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        if(counter == 50) {
            counter = 0;
            gp.gameState = gp.playState;
            gp.currentMap = gp.eHandler.tempMap;
            gp.player.worldX = gp.tileSize * gp.eHandler.tempCol;
            gp.player.worldY = gp.tileSize * gp.eHandler.tempRow;
            gp.eHandler.previousEventX = gp.player.worldX;
            gp.eHandler.previousEventY = gp.player.worldY;
            gp.changeArea();
        }
    }

    public void drawTradeScreen() {

        switch(subState) {
            case 0 -> trade_select();
            case 1 -> trade_buy();
            case 2 -> trade_sell();
        }

        gp.keyH.enterPressed = false;
    }

    public void trade_select() {

        npc.dialogueSet = 0;
        drawDialogueScreen();

        int x = gp.tileSize * 15;
        int y = gp.tileSize * 4;
        int width = gp.tileSize * 3;
        int height = (int) (gp.tileSize * 3.5);
        drawSubWindow(x, y, width, height);

        // Draw texts
        x += gp.tileSize;

        y += gp.tileSize;
        g2.drawString("Buy", x, y);
        if(commandNum == 0) {
            g2.drawString(">", x - 24, y);
            if(gp.keyH.enterPressed) {
                subState = 1;
            }
        }

        y += gp.tileSize;
        g2.drawString("Sell", x, y);
        if(commandNum == 1) {
            g2.drawString(">", x - 24, y);
            if(gp.keyH.enterPressed) {
                subState = 2;
            }
        }

        y += gp.tileSize;
        g2.drawString("Leave", x, y);
        if(commandNum == 2) {
            g2.drawString(">", x - 24, y);
            if(gp.keyH.enterPressed) {
                commandNum = 0;
                npc.startDialogue(npc, 1);
            }
        }
    }

    public void trade_buy() {

        // Draw player inventory
        drawInventory(gp.player, false);

        // Draw merchant inventory
        drawInventory(npc, true);

        // Draw hint window
        int x = gp.tileSize * 2;
        int y = (int) (gp.tileSize * 9.5);
        int width = gp.tileSize * 6;
        int height = gp.tileSize * 2;
        drawSubWindow(x, y, width, height);
        g2.drawString("[ESC] Back", x + 24, y + 60);

        // Draw player coin window
        x = gp.tileSize * 12;
        drawSubWindow(x, y, width, height);
        g2.drawString("Your coin " + gp.player.coin, x + 24, y + 60);

        // Draw price window
        int itemIndex = getItemIndexOnSlot(npcSlotCol, npcSlotRow);
        if(itemIndex < npc.inventory.size()) {

            x = (int) (gp.tileSize * 5.5);
            y = (int) (gp.tileSize * 5.5);
            width = (int) (gp.tileSize * 2.5);
            height = gp.tileSize;
            drawSubWindow(x, y, width, height);
            g2.drawImage(coin, x+10, y+8, 32, 32, null);

            int price = npc.inventory.get(itemIndex).price;
            String text = "" + price;
            x = getXforAlignToRightText(text, gp.tileSize * 8 - 20);
            g2.drawString(text, x, y + 34);

            // Buy an item
            if(gp.keyH.enterPressed) {
                if(npc.inventory.get(itemIndex).price > gp.player.coin) {
                    subState = 0;
                    npc.startDialogue(npc, 2);

                }
                else {
                    if(gp.player.canObtainItem(npc.inventory.get(itemIndex))) {
                        gp.player.coin -= npc.inventory.get(itemIndex).price;
                        gp.playSoundEffect(1);
                    }
                    else {
                        subState = 0;
                        npc.startDialogue(npc, 3);
                    }
                }
            }
        }
    }

    public void trade_sell() {
        // Draw player inventory
        drawInventory(gp.player, true);

        int x;
        int y;
        int width;
        int height;

        // Draw hint window
        x = gp.tileSize * 2;
        y = (int) (gp.tileSize * 9.5);
        width = gp.tileSize * 6;
        height = gp.tileSize * 2;
        drawSubWindow(x, y, width, height);
        g2.drawString("[ESC] Back", x + 24, y + 60);

        // Draw player coin window
        x = gp.tileSize * 12;
        drawSubWindow(x, y, width, height);
        g2.drawString("Your coin " + gp.player.coin, x + 24, y + 60);

        // Draw price window
        int itemIndex = getItemIndexOnSlot(playerSlotCol, playerSlotRow);
        if(itemIndex < gp.player.inventory.size()) {

            x = (int) (gp.tileSize * 15.5);
            y = (int) (gp.tileSize * 5.5);
            width = (int) (gp.tileSize * 2.5);
            height = gp.tileSize;
            drawSubWindow(x, y, width, height);
            g2.drawImage(coin, x+10, y+8, 32, 32, null);

            int price = (int) (gp.player.inventory.get(itemIndex).price -
                    (gp.player.inventory.get(itemIndex).price * 0.25));
            String text = "" + price;
            x = getXforAlignToRightText(text, gp.tileSize * 18 - 20);
            g2.drawString(text, x, y + 34);

            // Sell an item
            if(gp.keyH.enterPressed) {
                if(gp.player.inventory.get(itemIndex) == gp.player.currentWeapon ||
                        gp.player.inventory.get(itemIndex) == gp.player.currentShield) {

                    commandNum = 0;
                    subState = 0;
                    npc.startDialogue(npc, 4);

                } else {
                    if(gp.player.inventory.get(itemIndex).amount > 1) {
                        gp.player.inventory.get(itemIndex).amount--;
                    } else {
                        gp.player.inventory.remove((itemIndex));
                    }
                    gp.player.coin += price;
                    gp.playSoundEffect(1);
                }
            }
        }
    }

    public void drawSleepScreen() {

        counter++;

        if(counter < 120) {
            gp.eManager.lighting.filterAlpha += 0.01f;

            if(gp.eManager.lighting.filterAlpha > 1f) {
                gp.eManager.lighting.filterAlpha = 1f;
            }
        }

        if(counter >= 120) {
            gp.eManager.lighting.filterAlpha -= 0.01f;

            if(gp.eManager.lighting.filterAlpha <= 0f) {
                gp.eManager.lighting.filterAlpha = 0f;
                counter = 0;

                gp.eManager.lighting.dayState = gp.eManager.lighting.day;
                gp.eManager.lighting.dayCounter = 0;

                gp.gameState = gp.playState;
                gp.player.getImage();
            }
        }
    }

    public int getItemIndexOnSlot(int slotCol, int slotRow) {
        return slotCol + (slotRow * 5);
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public void splitLine(String text, int textX, int textY) {
        for(String line : text.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }
    }

    public int getXForCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return (gp.screenWidth/2) - (length/2);
    }

    public int getXforAlignToRightText(String text, int tailX) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return tailX - length;
    }
}
