package org.talesof.talesofamysticland.game.object;

import java.awt.Color;

import org.talesof.talesofamysticland.game.entity.Entity;
import org.talesof.talesofamysticland.game.entity.Projectile;
import org.talesof.talesofamysticland.game.main.GamePanel;



public class OBJ_Arrow extends Projectile {

    public final static String OBJ_NAME = "Flecha";
    int ammo;

    public OBJ_Arrow(GamePanel gp) {
        super(gp);

        type = typePickup;

        name = OBJ_NAME;
        ammo = 2;

        speed = 10;
        maxLife = 100;
        life = maxLife;

        attack = 2.5;
        useCost = 1;
        alive = false;

        knockBackPower = 3;

        image = setup("/projectile/arrow/arrow_up");

        getImage();
    }

    public void getImage() {
        up1 = setup("/projectile/arrow/arrow_up");
        up2 = setup("/projectile/arrow/arrow_up");
        down1 = setup("/projectile/arrow/arrow_down");
        down2 = setup("/projectile/arrow/arrow_down");
        left1 = setup("/projectile/arrow/arrow_left");
        left2 = setup("/projectile/arrow/arrow_left");
        right1 = setup("/projectile/arrow/arrow_right");
        right2 = setup("/projectile/arrow/arrow_right");
    }

    public boolean haveResource(Entity user) {
        return user.ammo >= useCost;
    }

    public void subtractResource(Entity user) {
        user.ammo -= useCost;
    }

    public Color getParticleColor() {
        return new Color(255, 0, 0);
    }

    public int getParticleSize() {
        return 3;
    }

    public int getParticleSpeed() {
        return 2;
    }

    public int getParticleMaxLife() {
        return 30;
    }

    public boolean use(Entity entity) {

        gp.playSoundEffect(2);

        gp.ui.addMessage("Ammo +" + ammo);
        entity.ammo += ammo;

        return true;
    }
    
}
