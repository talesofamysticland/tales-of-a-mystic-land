package org.talesof.talesofamysticland.game.object;

import org.talesof.talesofamysticland.game.entity.Entity;
import org.talesof.talesofamysticland.game.main.GamePanel;

public class OBJ_Tent extends Entity {

    public final static String OBJ_NAME = "Tenda";

    public OBJ_Tent(GamePanel gp) {
        super(gp);

        name = OBJ_NAME;
        type = typeConsumable;
        down1 = setup("/objects/tent");
        description = "[Tenda] \nVocê pode dormir até \na manhã seguinte.";
        price = 300;
    }

    public boolean use(Entity entity) {

        gp.gameState = gp.sleepState;
        gp.playSoundEffect(14);
        gp.player.restoreStatus();
        gp.player.getSleepingImage(down1);
        return true;
    }
}
