package org.talesof.talesofamysticland.game.object;

import org.talesof.talesofamysticland.game.entity.Entity;
import org.talesof.talesofamysticland.game.main.GamePanel;

public class OBJ_Heart extends Entity {

    public final static String OBJ_NAME = "Heart";
    int heal;

    public OBJ_Heart(GamePanel gp) {

        super(gp);

        type = typePickup;
        name = OBJ_NAME;
        heal = 2;

        down1 = setup("/objects/heart_full");
        image = setup("/objects/heart_full");
        image2 = setup("/objects/heart_half");
        image3 = setup("/objects/heart_blank");
    }

    public boolean use(Entity entity) {

        gp.playSoundEffect(2);

        gp.ui.addMessage("Life +" + heal);
        entity.life += heal;

        return true;
    }
}
