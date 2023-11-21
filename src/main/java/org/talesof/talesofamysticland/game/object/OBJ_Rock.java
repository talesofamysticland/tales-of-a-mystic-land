package org.talesof.talesofamysticland.game.object;

import org.talesof.talesofamysticland.game.entity.Entity;
import org.talesof.talesofamysticland.game.entity.Projectile;
import org.talesof.talesofamysticland.game.main.GamePanel;

import java.awt.*;

public class OBJ_Rock extends Projectile {

    public final static String OBJ_NAME = "Pedra";

    public OBJ_Rock(GamePanel gp) {
        super(gp);

        name = OBJ_NAME;

        speed = 5;
        maxLife = 40;
        life = maxLife;

        attack = 2;
        useCost = 1;
        alive = false;

        getImage();
    }

    public void getImage() {

        up1 = setup("/projectile/rock_down_1");
        up2 = setup("/projectile/rock_down_1");
        down1 = setup("/projectile/rock_down_1");
        down2 = setup("/projectile/rock_down_1");
        left1 = setup("/projectile/rock_down_1");
        left2 = setup("/projectile/rock_down_1");
        right1 = setup("/projectile/rock_down_1");
        right2 = setup("/projectile/rock_down_1");
    }

    public boolean haveResource(Entity user) {
        return user.ammo >= useCost;
    }

    public Color getParticleColor() {
        return new Color(40, 50, 0);
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
