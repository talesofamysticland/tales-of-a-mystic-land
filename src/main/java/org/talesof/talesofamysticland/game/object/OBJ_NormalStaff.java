package org.talesof.talesofamysticland.game.object;

import org.talesof.talesofamysticland.game.entity.Entity;
import org.talesof.talesofamysticland.game.main.GamePanel;

public class OBJ_NormalStaff extends Entity {

    public final static String OBJ_NAME = "Cajado de Liandry";

    public OBJ_NormalStaff(GamePanel gp) {
        super(gp);

        type = typeStaff;

        name = OBJ_NAME;
        description = "[" + name + "]\nUma poderosa arma, de um poderoso mestre" + 
        "\ne de uma poderosa origem.";

        down1 = setup("/objects/cajado_normal");

        attackValue = 0.5;
        attackArea.width = 36;
        attackArea.height = 36;
        knockBackPower = 1;

        motion1Duration = 10;
        motion2Duration = 30;

        price = 100;
    } 
}
