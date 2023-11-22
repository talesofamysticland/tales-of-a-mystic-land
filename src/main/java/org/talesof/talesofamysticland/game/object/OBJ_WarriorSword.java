package org.talesof.talesofamysticland.game.object;

import org.talesof.talesofamysticland.game.entity.Entity;
import org.talesof.talesofamysticland.game.main.GamePanel;

public class OBJ_WarriorSword extends Entity {

    public final static String OBJ_NAME = "Espada do Guerreiro";

    public OBJ_WarriorSword(GamePanel gp) {
        super(gp);

        type = typeSword;

        name = OBJ_NAME;
        description = "[" + name + "]\nUma velha espada.";

        down1 = setup("/objects/normal_sword");

        attackValue = 1;
        attackArea.width = 36;
        attackArea.height = 36;
        knockBackPower = 2;
        motion1Duration = 5;
        motion2Duration = 25;

        price = 20;
    }
}
