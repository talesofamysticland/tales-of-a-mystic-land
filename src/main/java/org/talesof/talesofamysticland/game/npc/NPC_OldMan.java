package org.talesof.talesofamysticland.game.npc;

import org.talesof.talesofamysticland.game.entity.Entity;
import org.talesof.talesofamysticland.game.main.GamePanel;

import java.util.Random;

public class NPC_OldMan extends Entity {

    public NPC_OldMan(GamePanel gp) {
        super(gp);

        type = typeNPC;

        direction = "down";
        speed = 1;

        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        dialogueSet = -1;

        getNPCImage();
        setDialogue();
    }

    public void getNPCImage() {
        up1 = setup("/npc/oldman_up_1");
        up2 = setup("/npc/oldman_up_2");
        down1 = setup("/npc/oldman_down_1");
        down2 = setup("/npc/oldman_down_2");
        left1 = setup("/npc/oldman_left_1");
        left2 = setup("/npc/oldman_left_2");
        right1 = setup("/npc/oldman_right_1");
        right2 = setup("/npc/oldman_right_2");
    }

    public void setDialogue() {
        dialogues[0][0] = "Olá, jovem.";
        dialogues[0][1] = "Então você veio até esta ilha pra procurar \nO Tesouro?";
        dialogues[0][2] = "Antigamente, eu era um grandioso bruxo, mas agora...\nEstou um pouco velho para aventuras.";
        dialogues[0][3] = "Bom, que a sorte lhe acompanhe.";

        dialogues[1][0] = "Se você ficar cansado, descanse na agua.";
        dialogues[1][1] = "Porém, os monstros vão reaparecer se você descansar\nEu não sei por quê, mas é assim que funciona.";
        dialogues[1][2] = "Em todos os casos, não se esforce demais.";

        dialogues[2][0] = "Eu me pergunto como abrir esta porta...";
    }

    public void setAction() {

        if(onPath) {
            // NPC goes to a specified position on the map
            int goalCol = 12;
            int goalRow = 9;
            searchPath(goalCol, goalRow);

            // NPC follows player
//            int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
//            int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;
//            searchPlayer(goalCol, goalRow);

        } else {
            actionLockCounter++;

            if(actionLockCounter == 120) {
                Random random = new Random();
                int i = random.nextInt(100) + 1;

                if(i <= 25)
                    direction = "up";
                if(i > 25 && i <= 50)
                    direction = "down";
                if(i > 50 && i <= 75)
                    direction = "left";
                if(i > 75)
                    direction = "right";

                actionLockCounter = 0;
            }
        }
    }

    public void speak() {

        // Do this character specific stuff
        facePlayer();
        startDialogue(this, dialogueSet);

        dialogueSet++;

        if(dialogues[dialogueSet][0] == null) {
            dialogueSet--;
        }
    }
}
