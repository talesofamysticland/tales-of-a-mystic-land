package org.talesof.talesofamysticland.game.object;

import org.talesof.talesofamysticland.game.entity.Entity;
import org.talesof.talesofamysticland.game.main.GamePanel;

public class OBJ_Bow extends Entity {

    public final static String OBJ_NAME = "Arco do sr. Dominik";

    public OBJ_Bow(GamePanel gp) {
        super(gp);

        type = typeBow;

        name = OBJ_NAME;
        description = "[" + name + "]\nPode ser seu último\nsussurro.";

        down1 = setup("/objects/normal_bow");

        attackValue = 0.1;
        attackArea.width = 28;
        attackArea.height = 28;
        knockBackPower = 1;

        motion1Duration = 10;
        motion2Duration = 30;

        price = 100;
    } 
    
}
