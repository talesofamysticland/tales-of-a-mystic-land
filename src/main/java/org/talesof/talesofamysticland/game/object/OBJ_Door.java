package org.talesof.talesofamysticland.game.object;

import org.talesof.talesofamysticland.game.entity.Entity;
import org.talesof.talesofamysticland.game.main.GamePanel;

public class OBJ_Door extends Entity {

    public final static String OBJ_NAME = "Porta";

    public OBJ_Door(GamePanel gp) {
        super(gp);

        name = OBJ_NAME;
        down1 = setup("/objects/door");
        type = typeObstacle;

        collision = true;

        solidArea.x = 0;
        solidArea.y = 12;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = "Você precisa de uma chave para abrir isso!";
    }

    @Override
    public void interact() {

        startDialogue(this, 0);
    }
}
