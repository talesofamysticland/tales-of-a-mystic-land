package org.talesof.talesofamysticland.game.object;

import org.talesof.talesofamysticland.game.entity.Entity;
import org.talesof.talesofamysticland.game.main.GamePanel;

public class OBJ_Chest extends Entity {

    public final static String OBJ_NAME = "Baú";

    public OBJ_Chest(GamePanel gp) {
        super(gp);

        name = OBJ_NAME;
        image = setup("/objects/chest");
        image2 = setup("/objects/chest_opened");
        down1 = image;
        type = typeObstacle;

        collision = true;

        solidArea.x = 4;
        solidArea.y = 16;
        solidArea.width = 40;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    @Override
    public void setLoot(Entity loot) {
        this.loot = loot;
        setDialogue();
    }

    public void setDialogue() {

        dialogues[0][0] = "Você abriu um baú e encontrou " + loot.name + "!\n" +
                "...Mas sua mochila ja esta cheia";
        dialogues[1][0] = "Você abriu um baú e encontrou " + loot.name + "!\n" +
                "Você pegou um " + loot.name + "!";
        dialogues[2][0] = "Estava vazio.";

    }

    @Override
    public void interact() {

        if(!opened) {
            gp.playSoundEffect(3);

            if(!gp.player.canObtainItem(loot)) {
                startDialogue(this, 0);
            } else {
                startDialogue(this, 1);
                down1 = image2;
                opened = true;
            }

        } else {
            startDialogue(this, 2);
        }
    }
}
