package org.talesof.talesofamysticland.game.npc;

import org.talesof.talesofamysticland.game.entity.Entity;
import org.talesof.talesofamysticland.game.main.GamePanel;
import org.talesof.talesofamysticland.game.object.*;

public class NPC_Merchant extends Entity {

    public NPC_Merchant(GamePanel gp) {
        super(gp);

        type = typeNPC;

        direction = "down";
        speed = 1;

        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getNPCImage();
        setDialogue();
        setItems();
    }

    public void getNPCImage() {
        up1 = setup("/npc/merchant_down_1");
        up2 = setup("/npc/merchant_down_2");
        down1 = setup("/npc/merchant_down_1");
        down2 = setup("/npc/merchant_down_2");
        left1 = setup("/npc/merchant_down_1");
        left2 = setup("/npc/merchant_down_2");
        right1 = setup("/npc/merchant_down_1");
        right2 = setup("/npc/merchant_down_2");
    }

    public void setDialogue() {
        dialogues[0][0] = "He he, então você me encontrou.\nEu tenho algums coisas boas pra você.\nVocê quer fazer algumas trocas?";
        dialogues[1][0] = "Volte sempre, he he!";
        dialogues[2][0] = "Você precisa de mais moedas para comprar isso!";
        dialogues[3][0] = "Você não possui mais espaço na mochila!";
        dialogues[4][0] = "Você não pode vender um item que está equipado";
    }

    public void setItems() {

        inventory.add(new OBJ_Potion(gp));
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_SwordNormal(gp));
        inventory.add(new OBJ_Axe(gp));
        inventory.add(new OBJ_ShieldWood(gp));
        inventory.add(new OBJ_BlueShield(gp));
    }

    public void speak() {

        facePlayer();
        gp.gameState = gp.tradeState;
        gp.ui.npc = this;
    }
}
