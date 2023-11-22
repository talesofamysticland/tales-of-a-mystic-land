package org.talesof.talesofamysticland.game.main;

import org.talesof.talesofamysticland.game.entity.Entity;
import org.talesof.talesofamysticland.game.object.*;

public class EntityGenerator {

    GamePanel gp;

    public EntityGenerator(GamePanel gp) {
        this.gp = gp;
    }

    public Entity getObject(String itemName) {

        Entity obj = null;

        switch(itemName) {
            case OBJ_Axe.OBJ_NAME -> obj = new OBJ_Axe(gp);
            case OBJ_Boots.OBJ_NAME  -> obj = new OBJ_Boots(gp);
            case OBJ_Key.OBJ_NAME  -> obj = new OBJ_Key(gp);
            case OBJ_Lantern.OBJ_NAME  -> obj = new OBJ_Lantern(gp);
            case OBJ_Potion.OBJ_NAME  -> obj = new OBJ_Potion(gp);
            case OBJ_BlueShield.OBJ_NAME  -> obj = new OBJ_BlueShield(gp);
            case OBJ_ShieldWood.OBJ_NAME  -> obj = new OBJ_ShieldWood(gp);
            case OBJ_WarriorSword.OBJ_NAME  -> obj = new OBJ_WarriorSword(gp);
            case OBJ_Tent.OBJ_NAME  -> obj = new OBJ_Tent(gp);
            case OBJ_Door.OBJ_NAME  -> obj = new OBJ_Door(gp);
            case OBJ_Chest.OBJ_NAME  -> obj = new OBJ_Chest(gp);
            case OBJ_Bow.OBJ_NAME  -> obj = new OBJ_Bow(gp);
            case OBJ_LiandryStaff.OBJ_NAME  -> obj = new OBJ_LiandryStaff(gp);
        }

        return obj;
    }
}
