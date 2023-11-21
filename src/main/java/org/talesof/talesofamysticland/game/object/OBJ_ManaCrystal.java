package org.talesof.talesofamysticland.game.object;

import org.talesof.talesofamysticland.game.entity.Entity;
import org.talesof.talesofamysticland.game.main.GamePanel;

public class OBJ_ManaCrystal extends Entity {

    public final static String OBJ_NAME = "Cristal de Mana";
    int mana;

    public OBJ_ManaCrystal(GamePanel gp) {
        super(gp);

        type = typePickup;

        name = OBJ_NAME;
        mana = 1;

        down1 = setup("/objects/manacrystal_full");
        image = setup("/objects/manacrystal_full");
        image2 = setup("/objects/manacrystal_blank");
    }

    public boolean use(Entity entity) {

        gp.playSoundEffect(2);

        gp.ui.addMessage("Mana +" + mana);
        entity.mana += mana;

        return true;
    }
}
