package org.talesof.talesofamysticland.game.object;

import org.talesof.talesofamysticland.game.entity.Entity;
import org.talesof.talesofamysticland.game.entity.Projectile;
import org.talesof.talesofamysticland.game.main.GamePanel;

import java.awt.*;

public class OBJ_Fireball extends Projectile {

    public final static String OBJ_NAME = "Bola de Fogo";

    public OBJ_Fireball(GamePanel gp) {
        super(gp);

        name = OBJ_NAME;

        speed = 8;
        maxLife = 80;
        life = maxLife;

        attack = 2;
        useCost = 1;
        alive = false;

        knockBackPower = 5;

        getImage();
    }

    public void getImage() {
        up1 = setup("/projectile/fireball/fireball_up_1");
        up2 = setup("/projectile/fireball/fireball_up_2");
        down1 = setup("/projectile/fireball/fireball_down_1");
        down2 = setup("/projectile/fireball/fireball_down_2");
        left1 = setup("/projectile/fireball/fireball_left_1");
        left2 = setup("/projectile/fireball/fireball_left_2");
        right1 = setup("/projectile/fireball/fireball_right_1");
        right2 = setup("/projectile/fireball/fireball_right_2");
    }

    public boolean haveResource(Entity user) {
        return user.mana >= useCost;
    }

    public void subtractResource(Entity user) {
        user.mana -= useCost;
    }

    public Color getParticleColor() {
        return new Color(240, 50, 0);
    }

    public int getParticleSize() {
        return 10;
    }

    public int getParticleSpeed() {
        return 1;
    }

    public int getParticleMaxLife() {
        return 20;
    }
}
