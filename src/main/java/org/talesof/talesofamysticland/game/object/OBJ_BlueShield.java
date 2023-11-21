package org.talesof.talesofamysticland.game.object;

import org.talesof.talesofamysticland.game.entity.Entity;
import org.talesof.talesofamysticland.game.main.GamePanel;

public class OBJ_BlueShield extends Entity {

    public final static String OBJ_NAME = "Escudo Azul";

    public OBJ_BlueShield(GamePanel gp) {
        super(gp);

        type = typeShield;

        name = OBJ_NAME;
        description = "[" + name + "]\nUm brilhante Escudo Azul.\nBem azul mesmo.";
        down1 = setup("/objects/shield_blue");

        defenseValue = 2;

        price = 250;
    }
}
