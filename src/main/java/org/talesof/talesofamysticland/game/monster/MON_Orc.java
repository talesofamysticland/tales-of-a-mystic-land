package org.talesof.talesofamysticland.game.monster;

import org.talesof.talesofamysticland.game.entity.Entity;
import org.talesof.talesofamysticland.game.main.GamePanel;
import org.talesof.talesofamysticland.game.object.OBJ_BronzeCoin;
import org.talesof.talesofamysticland.game.object.OBJ_Heart;
import org.talesof.talesofamysticland.game.object.OBJ_ManaCrystal;
import java.util.Random;

public class MON_Orc extends Entity {

    public MON_Orc(GamePanel gp) {
        super(gp);

        type = typeMonster;
        name = "Orc";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 10;
        life = maxLife;
        attack = 8;
        defense = 2;
        exp = 10;
        knockBackPower = 5;

        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 40;
        solidArea.height = 44;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        attackArea.width = 48;
        attackArea.height = 48;
        motion1Duration = 40;
        motion2Duration = 85;

        getMonsterImage();
        getAttackImage();
    }

    public void getMonsterImage() {
        up1 = setup("/monster/orc_up_1");
        up2 = setup("/monster/orc_up_2");
        down1 = setup("/monster/orc_down_1");
        down2 = setup("/monster/orc_down_2");
        left1= setup("/monster/orc_left_1");
        left2 = setup("/monster/orc_left_2");
        right1 = setup("/monster/orc_right_1");
        right2 = setup("/monster/orc_right_2");
    }

    public void getAttackImage() {
        attackUp1 = setup("/monster/orc_attack_up_1", gp.tileSize, gp.tileSize * 2);
        attackUp2 = setup("/monster/orc_attack_up_2", gp.tileSize, gp.tileSize * 2);
        attackDown1 = setup("/monster/orc_attack_down_1", gp.tileSize, gp.tileSize * 2);
        attackDown2 = setup("/monster/orc_attack_down_2", gp.tileSize, gp.tileSize * 2);
        attackLeft1 = setup("/monster/orc_attack_left_1", gp.tileSize * 2, gp.tileSize);
        attackLeft2 = setup("/monster/orc_attack_left_2", gp.tileSize * 2, gp.tileSize);
        attackRight1 = setup("/monster/orc_attack_right_1", gp.tileSize * 2, gp.tileSize);
        attackRight2 = setup("/monster/orc_attack_right_2", gp.tileSize * 2, gp.tileSize);
    }

    public void setAction() {

        if(onPath) {

            // Check if it stops chasing
            checkStopChasing(gp.player, 15, 100);

            // Monster aggro the player
            searchPlayer(getGoalCol(gp.player), getGoalRow(gp.player));
        }
        else {

            // Check if it starts chasing
            checkStartChasing(gp.player, 5, 100);

            // Get a random direction if not on path
            getRandomDirection();
        }

        // Check if it attacks
        if(!attacking) {
            checkAttack(40, gp.tileSize * 4, gp.tileSize);
        }
    }

    public void damageReaction() {
        actionLockCounter = 0;
        onPath = true;
    }

    public void checkDrop() {

        // Cast a die
        int i = new Random().nextInt(100) + 1;

        // Set the monster drop
        if(i < 50) {
            dropItem(new OBJ_BronzeCoin(gp));
        }

        if(i >= 50 && i < 75) {
            dropItem(new OBJ_Heart(gp));
        }

        if(i >= 75 && i < 100) {
            dropItem(new OBJ_ManaCrystal(gp));
        }
    }
}
