package org.talesof.talesofamysticland.game.monster;

import org.talesof.talesofamysticland.game.entity.Entity;
import org.talesof.talesofamysticland.game.main.GamePanel;
import org.talesof.talesofamysticland.game.object.OBJ_BronzeCoin;
import org.talesof.talesofamysticland.game.object.OBJ_Heart;
import org.talesof.talesofamysticland.game.object.OBJ_ManaCrystal;
import org.talesof.talesofamysticland.game.object.OBJ_Rock;

import java.util.Random;

public class MON_GreenSlime extends Entity {

    public MON_GreenSlime(GamePanel gp) {
        super(gp);

        type = typeMonster;
        name = "Green Slime";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 4;
        life = maxLife;
        attack = 5;
        defense = 0;
        exp = 2;

        projectile = new OBJ_Rock(gp);

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 40;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getMonsterImage();
    }

    public void getMonsterImage() {
        up1 = setup("/monster/greenslime_down_1");
        up2 = setup("/monster/greenslime_down_2");
        down1 = setup("/monster/greenslime_down_1");
        down2 = setup("/monster/greenslime_down_2");
        left1= setup("/monster/greenslime_down_1");
        left2 = setup("/monster/greenslime_down_2");
        right1 = setup("/monster/greenslime_down_1");
        right2 = setup("/monster/greenslime_down_2");
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
