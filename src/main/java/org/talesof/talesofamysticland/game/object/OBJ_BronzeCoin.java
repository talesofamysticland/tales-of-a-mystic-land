package org.talesof.talesofamysticland.game.object;

import org.talesof.talesofamysticland.game.entity.Entity;
import org.talesof.talesofamysticland.game.main.GamePanel;

public class OBJ_BronzeCoin extends Entity {

    public final static String OBJ_NAME = "Bronze Coin";
    int value;

    public OBJ_BronzeCoin(GamePanel gp) {
        super(gp);

        type = typePickup;
        name = OBJ_NAME;
        value = 1;
        down1 = setup("/objects/coin_bronze");
    }

    public boolean use(Entity entity) {

        gp.playSoundEffect(1);
        gp.ui.addMessage("Coin +" + value);
        gp.player.coin += value;

        return true;
    }
}
