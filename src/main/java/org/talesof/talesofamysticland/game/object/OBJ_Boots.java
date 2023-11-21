package org.talesof.talesofamysticland.game.object;

import org.talesof.talesofamysticland.game.entity.Entity;
import org.talesof.talesofamysticland.game.main.GamePanel;

public class OBJ_Boots extends Entity {

    public final static String OBJ_NAME = "Botas";

    public OBJ_Boots(GamePanel gp) {
        super(gp);

        name = OBJ_NAME;
        down1 = setup("/objects/boots");
    }
}
