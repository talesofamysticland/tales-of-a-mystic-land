package org.talesof.talesofamysticland.game.object;

import org.talesof.talesofamysticland.game.entity.Entity;
import org.talesof.talesofamysticland.game.main.GamePanel;

public class OBJ_ShieldWood extends Entity {

    public final static String OBJ_NAME = "Escudo de Madeira";

    public OBJ_ShieldWood(GamePanel gp) {
        super(gp);

        type = typeShield;

        name = OBJ_NAME;
        description = "[" + name + "]\nFeito de madeira.";
        down1 = setup("/objects/shield_wood");

        defenseValue = 1;

        price = 35;
    }
}
