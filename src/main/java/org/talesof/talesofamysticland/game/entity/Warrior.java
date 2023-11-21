package org.talesof.talesofamysticland.game.entity;

import org.talesof.talesofamysticland.game.main.GamePanel;
import org.talesof.talesofamysticland.game.object.OBJ_Axe;
import org.talesof.talesofamysticland.game.object.OBJ_Key;
import org.talesof.talesofamysticland.game.object.OBJ_ShieldWood;
import org.talesof.talesofamysticland.game.object.OBJ_SwordNormal;

public class Warrior extends PlayerCharacter {

    public Warrior(GamePanel gp) {
        super(gp);

        currentWeapon = new OBJ_SwordNormal(gp);
        currentShield = new OBJ_ShieldWood(gp);
        attack = getAttack();
        defense = getDefense();

        getGuardingImage();
    }

    @Override
    public void getImage() {
        up1 = setup("/player/warrior/boy_up_1");
        up2 = setup("/player/warrior/boy_up_2");
        up3 = setup("/player/warrior/boy_up_2");
        up4 = setup("/player/warrior/boy_up_2");
        up5 = setup("/player/warrior/boy_up_2");
        
        down1 = setup("/player/warrior/boy_up_2");
        down2 = setup("/player/warrior/boy_up_2");
        down3 = setup("/player/warrior/boy_up_2");
        down4 = setup("/player/warrior/boy_up_2");
        down5 = setup("/player/warrior/boy_up_2");

        left1 = setup("/player/warrior/boy_up_2");
        left2 = setup("/player/warrior/boy_up_2");
        left3 = setup("/player/warrior/boy_up_2");

        right1 = setup("/player/warrior/boy_up_2");
        right2 = setup("/player/warrior/boy_up_2");
        right3 = setup("/player/warrior/boy_up_2");
    }

    @Override
    public void getAttackImage() {

        if(currentWeapon.type == typeSword) {
            attackUp1 = setup("/player/boy_attack_up_1", gp.tileSize, gp.tileSize * 2);
            attackUp2 = setup("/player/boy_attack_up_2", gp.tileSize, gp.tileSize * 2);
            attackDown1 = setup("/player/boy_attack_down_1", gp.tileSize, gp.tileSize * 2);
            attackDown2 = setup("/player/boy_attack_down_2", gp.tileSize, gp.tileSize * 2);
            attackLeft1 = setup("/player/boy_attack_left_1", gp.tileSize * 2, gp.tileSize);
            attackLeft2 = setup("/player/boy_attack_left_2", gp.tileSize * 2, gp.tileSize);
            attackRight1 = setup("/player/boy_attack_right_1", gp.tileSize * 2, gp.tileSize);
            attackRight2 = setup("/player/boy_attack_right_2", gp.tileSize * 2, gp.tileSize);
        }

        if(currentWeapon.type == typeAxe) {
            attackUp1 = setup("/player/boy_axe_up_1", gp.tileSize, gp.tileSize * 2);
            attackUp2 = setup("/player/boy_axe_up_2", gp.tileSize, gp.tileSize * 2);
            attackDown1 = setup("/player/boy_axe_down_1", gp.tileSize, gp.tileSize * 2);
            attackDown2 = setup("/player/boy_axe_down_2", gp.tileSize, gp.tileSize * 2);
            attackLeft1 = setup("/player/boy_axe_left_1", gp.tileSize * 2, gp.tileSize);
            attackLeft2 = setup("/player/boy_axe_left_2", gp.tileSize * 2, gp.tileSize);
            attackRight1 = setup("/player/boy_axe_right_1", gp.tileSize * 2, gp.tileSize);
            attackRight2 = setup("/player/boy_axe_right_2", gp.tileSize * 2, gp.tileSize);
        }
    }

    public void getGuardingImage() {
        guardUp = setup("/player/boy_guard_up");
        guardDown = setup("/player/boy_guard_down");
        guardLeft = setup("/player/boy_guard_left");
        guardRight = setup("/player/boy_guard_right");
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
