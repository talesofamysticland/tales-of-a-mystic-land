package org.talesof.talesofamysticland.game.object;

import org.talesof.talesofamysticland.game.entity.Entity;
import org.talesof.talesofamysticland.game.main.GamePanel;

public class OBJ_Potion extends Entity {

    public final static String OBJ_NAME = "Potion";
    int heal;

    public OBJ_Potion(GamePanel gp) {
        super(gp);

        type = typeConsumable;

        name = OBJ_NAME;
        heal = 5;
        description = "[" + name + "]\nHeals your life by " + heal + ".";
        down1 = setup("/objects/potion_red");
        stackable = true;

        price = 25;

        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = "You drink the " + name + "!\n" +
                "Your life has been recovered by " + heal + ".";

        dialogues[1][0] = "Your life is full!";
    }

    public boolean use(Entity entity) {

        gp.gameState = gp.dialogueState;

        if(entity.life == maxLife) {
            startDialogue(this, 1);
            return false;
        }

        startDialogue(this, 0);
        entity.life += heal;

        gp.playSoundEffect(2);

        return true;
    }
}
