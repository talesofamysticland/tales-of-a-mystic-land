package org.talesof.talesofamysticland.game.entity;

import org.talesof.talesofamysticland.game.main.GamePanel;
import org.talesof.talesofamysticland.game.object.OBJ_Axe;
import org.talesof.talesofamysticland.game.object.OBJ_Fireball;
import org.talesof.talesofamysticland.game.object.OBJ_Key;
import org.talesof.talesofamysticland.game.object.OBJ_ShieldWood;
import org.talesof.talesofamysticland.game.object.OBJ_SwordNormal;

public class Wizard extends PlayerCharacter {

    public Wizard(GamePanel gp, KeyHandler keyH) {
        super(gp, keyH);

        currentWeapon = new OBJ_SwordNormal(gp);
        currentShield = new OBJ_ShieldWood(gp);
        projectile = new OBJ_Fireball(gp);
        attack = getAttack();
        defense = getDefense();
    }

    @Override
    public void getImage() {
        up1 = setup("/player/wizard/wizard_up_1");
        up2 = setup("/player/wizard/wizard_up_2");
        up3 = setup("/player/wizard/wizard_up_3");
        up4 = setup("/player/wizard/wizard_up_4");
        up5 = setup("/player/wizard/wizard_up_5");
        
        down1 = setup("/player/wizard/wizard_down_1");
        down2 = setup("/player/wizard/wizard_down_2");
        down3 = setup("/player/wizard/wizard_down_3");
        down4 = setup("/player/wizard/wizard_down_4");
        down5 = setup("/player/wizard/wizard_down_5");

        left1 = setup("/player/wizard/wizard_left_1");
        left3 = setup("/player/wizard/wizard_left_2");
        left2 = setup("/player/wizard/wizard_left_3");

        right1 = setup("/player/wizard/wizard_right_1");
        right2 = setup("/player/wizard/wizard_right_2");
        right3 = setup("/player/wizard/wizard_right_3");
    }

    @Override
    public void getAttackImage() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAttackImage'");
    }

    @Override
    public void setItems() {
        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_Axe(gp));
    }    
}
