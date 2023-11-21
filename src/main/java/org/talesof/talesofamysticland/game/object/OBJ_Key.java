package org.talesof.talesofamysticland.game.object;

import org.talesof.talesofamysticland.game.entity.Entity;
import org.talesof.talesofamysticland.game.main.GamePanel;

public class OBJ_Key extends Entity {

    public final static String OBJ_NAME = "Key";

    public OBJ_Key(GamePanel gp) {
        super(gp);

        name = OBJ_NAME;
        description = "[" + name + "]\nIt opens a door.";
        down1 = setup("/objects/key");
        type = typeConsumable;
        stackable = true;

        price = 100;

        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = "You used the " + name + " to open the door";

        dialogues[1][0] = "It is not time for that.";
    }

    @Override
    public boolean use(Entity entity) {

        gp.gameState = gp.dialogueState;

        int objIndex = getDetected(entity, gp.obj, "Door");

        if(objIndex != 999) {
            startDialogue(this, 0);
            gp.playSoundEffect(3);
            gp.obj[gp.currentMap][objIndex] = null;
            return true;
        }
        else {
            startDialogue(this, 1);
            return false;
        }
    }
}
