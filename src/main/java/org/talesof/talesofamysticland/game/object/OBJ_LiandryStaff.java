package org.talesof.talesofamysticland.game.object;

import org.talesof.talesofamysticland.game.entity.Entity;
import org.talesof.talesofamysticland.game.main.GamePanel;

public class OBJ_LiandryStaff extends Entity {

    public final static String OBJ_NAME = "Cajado de Liandry";

    public OBJ_LiandryStaff(GamePanel gp) {
        super(gp);

        type = typeStaff;

        name = OBJ_NAME;
        description = "[" + name + "]\nUma poderosa arma,\nde um poderoso mestre.";

        down1 = setup("/objects/staff");

        attackValue = 0.5;
        attackArea.width = 30;
        attackArea.height = 30;
        knockBackPower = 1;

        motion1Duration = 10;
        motion2Duration = 30;

        price = 100;
    } 
}
