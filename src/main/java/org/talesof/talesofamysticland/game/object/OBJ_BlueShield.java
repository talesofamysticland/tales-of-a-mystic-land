package org.talesof.talesofamysticland.game.object;

import org.talesof.talesofamysticland.game.entity.Entity;
import org.talesof.talesofamysticland.game.main.GamePanel;

public class OBJ_BlueShield extends Entity {

    public final static String OBJ_NAME = "Blue Shield";

    public OBJ_BlueShield(GamePanel gp) {
        super(gp);

        type = typeShield;

        name = OBJ_NAME;
        description = "[" + name + "]\nA shiny blue shield.\nVery blue.";
        down1 = setup("/objects/shield_blue");

        defenseValue = 2;

        price = 250;
    }
}
