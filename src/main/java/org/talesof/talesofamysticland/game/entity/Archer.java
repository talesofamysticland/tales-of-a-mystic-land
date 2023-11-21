package org.talesof.talesofamysticland.game.entity;

import org.talesof.talesofamysticland.game.main.GamePanel;
import org.talesof.talesofamysticland.game.main.KeyHandler;
import org.talesof.talesofamysticland.game.object.OBJ_Axe;
import org.talesof.talesofamysticland.game.object.OBJ_Fireball;
import org.talesof.talesofamysticland.game.object.OBJ_Key;
import org.talesof.talesofamysticland.game.object.OBJ_SwordNormal;

public class Archer extends PlayerCharacter {

    public Archer(String name, GamePanel gp, KeyHandler keyH) {
        super(name, gp, keyH);

        currentWeapon = new OBJ_SwordNormal(gp);
        projectile = new OBJ_Fireball(gp);
        attack = getAttack();
        defense = getDefense();
    }

    @Override
    public void getImage() {
        up1 = setup("/player/archer/archer_up_1");
        up2 = setup("/player/archer/archer_up_2");
        up3 = setup("/player/archer/archer_up_3");
        up4 = setup("/player/archer/archer_up_4");
        up5 = setup("/player/archer/archer_up_5");
        
        down1 = setup("/player/archer/archer_down_1");
        down2 = setup("/player/archer/archer_down_2");
        down3 = setup("/player/archer/archer_down_3");
        down4 = setup("/player/archer/archer_down_4");
        down5 = setup("/player/archer/archer_down_5");

        left1 = setup("/player/archer/archer_left_1");
        left2 = setup("/player/archer/archer_left_2");
        left3 = setup("/player/archer/archer_left_3");

        right1 = setup("/player/archer/archer_right_1");
        right2 = setup("/player/archer/archer_right_2");
        right3 = setup("/player/archer/archer_right_3");
    }

    @Override
    public void getAttackImage() {

        if(currentWeapon.type == typeAxe) {
            loadAxeImage();

        } else if(currentWeapon.type == typeStaff) {
            loadStaffImage();
        }
    }

    private void loadStaffImage() {
        attackUp1 = setup("/player/archer/attacking/archer_attack_up_1", gp.tileSize, gp.tileSize*2);
        attackUp2 = setup("/player/archer/attacking/archer_attack_up_2", gp.tileSize, gp.tileSize*2);
        attackUp3 = setup("/player/archer/attacking/archer_attack_up_3", gp.tileSize, gp.tileSize*2);

        attackDown1 = setup("/player/archer/attacking/archer_attack_down_1", gp.tileSize, gp.tileSize*2);
        attackDown2 = setup("/player/archer/attacking/archer_attack_down_2", gp.tileSize, gp.tileSize*2);
        attackDown3 = setup("/player/archer/attacking/archer_attack_down_3", gp.tileSize, gp.tileSize*2);

        attackLeft1 = setup("/player/archer/attacking/archer_attack_left_1", gp.tileSize*2, gp.tileSize);
        attackLeft2 = setup("/player/archer/attacking/archer_attack_left_2", gp.tileSize*2, gp.tileSize);
        attackLeft3 = setup("/player/archer/attacking/archer_attack_left_3", gp.tileSize*2, gp.tileSize);
        attackLeft4 = setup("/player/archer/attacking/archer_attack_left_3", gp.tileSize*2, gp.tileSize);

        attackRight1 = setup("/player/archer/attacking/archer_attack_right_1", gp.tileSize*2, gp.tileSize);
        attackRight2 = setup("/player/archer/attacking/archer_attack_right_2", gp.tileSize*2, gp.tileSize);
        attackRight3 = setup("/player/archer/attacking/archer_attack_right_3", gp.tileSize*2, gp.tileSize);
        attackRight4 = setup("/player/archer/attacking/archer_attack_right_3", gp.tileSize*2, gp.tileSize);
    }

    private void loadAxeImage() {
        attackUp1 = setup("/player/archer/axe/archer_axe_up_1", gp.tileSize, gp.tileSize*2);
        attackUp2 = setup("/player/archer/axe/archer_axe_up_2", gp.tileSize, gp.tileSize*2);
        attackUp3 = setup("/player/archer/axe/archer_axe_up_3", gp.tileSize, gp.tileSize*2);

        attackDown1 = setup("/player/archer/axe/archer_axe_down_1", gp.tileSize, gp.tileSize*2);
        attackDown2 = setup("/player/archer/axe/archer_axe_down_2", gp.tileSize, gp.tileSize*2);
        attackDown3 = setup("/player/archer/axe/archer_axe_down_3", gp.tileSize, gp.tileSize*2);

        attackLeft1 = setup("/player/archer/axe/archer_axe_left_1", gp.tileSize*2, gp.tileSize);
        attackLeft2 = setup("/player/archer/axe/archer_axe_left_2", gp.tileSize*2, gp.tileSize);
        attackLeft3 = setup("/player/archer/axe/archer_axe_left_3", gp.tileSize*2, gp.tileSize);
        attackLeft4 = setup("/player/archer/axe/archer_axe_left_3", gp.tileSize*2, gp.tileSize);

        attackRight1 = setup("/player/archer/axe/archer_axe_right_1", gp.tileSize*2, gp.tileSize);
        attackRight2 = setup("/player/archer/axe/archer_axe_right_2", gp.tileSize*2, gp.tileSize);
        attackRight3 = setup("/player/archer/axe/archer_axe_right_3", gp.tileSize*2, gp.tileSize);
        attackRight4 = setup("/player/archer/axe/archer_axe_right_3", gp.tileSize*2, gp.tileSize);
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
