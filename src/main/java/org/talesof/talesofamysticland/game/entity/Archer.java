package org.talesof.talesofamysticland.game.entity;

import org.talesof.talesofamysticland.game.main.GamePanel;
import org.talesof.talesofamysticland.game.object.OBJ_Axe;
import org.talesof.talesofamysticland.game.object.OBJ_Fireball;
import org.talesof.talesofamysticland.game.object.OBJ_Key;
import org.talesof.talesofamysticland.game.object.OBJ_SwordNormal;

public class Archer extends PlayerCharacter {

    public Archer(GamePanel gp) {
        super(gp);

        currentWeapon = new OBJ_SwordNormal(gp);
        projectile = new OBJ_Fireball(gp);
        attack = getAttack();
        defense = getDefense();
    }

    @Override
    public void getImage() {
        up1 = setup("/player/archer/boy_up_1");
        up2 = setup("/player/archer/boy_up_2");
        up3 = setup("/player/archer/boy_up_2");
        up4 = setup("/player/archer/boy_up_2");
        up5 = setup("/player/archer/boy_up_2");
        
        down1 = setup("/player/archer/boy_up_2");
        down2 = setup("/player/archer/boy_up_2");
        down3 = setup("/player/archer/boy_up_2");
        down4 = setup("/player/archer/boy_up_2");
        down5 = setup("/player/archer/boy_up_2");

        left1 = setup("/player/archer/boy_up_2");
        left2 = setup("/player/archer/boy_up_2");
        left3 = setup("/player/archer/boy_up_2");

        right1 = setup("/player/archer/boy_up_2");
        right2 = setup("/player/archer/boy_up_2");
        right3 = setup("/player/archer/boy_up_2");
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
