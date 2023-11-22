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

    @Override
    public void getNPCImage() {
        up1 = setup("/npc/merchant/merchant_away");
        up2 = setup("/npc/merchant/merchant_away");
        down1 = setup("/npc/merchant/merchant_away");
        down2 = setup("/npc/merchant/merchant_away");
        left1 = setup("/npc/merchant/merchant_away");
        left2 = setup("/npc/merchant/merchant_away");
        right1 = setup("/npc/merchant/merchant_away");
        right2 = setup("/npc/merchant/merchant_away");
    }

    public void getNPCImageDuringInteraction() {
        up1 = setup("/npc/merchant/merchant_player_interaction");
        up2 = setup("/npc/merchant/merchant_player_interaction");
        down1 = setup("/npc/merchant/merchant_player_interaction");
        down2 = setup("/npc/merchant/merchant_player_interaction");
        left1 = setup("/npc/merchant/merchant_player_interaction");
        left2 = setup("/npc/merchant/merchant_player_interaction");
        right1 = setup("/npc/merchant/merchant_player_interaction");
        right2 = setup("/npc/merchant/merchant_player_interaction");
    }

    public void setDialogue() {
        dialogues[0][0] = "He he, então você me encontrou.\nEu tenho algumas coisas boas pra você.\nQuer fazer algumas trocas?";
        dialogues[1][0] = "Volte sempre, he he!";
        dialogues[2][0] = "Você precisa de mais moedas para comprar isso!";
        dialogues[3][0] = "Você não possui mais espaço na mochila!";
        dialogues[4][0] = "Você não pode vender um item que está equipado.";
    }

    public void setItems() {

        inventory.add(new OBJ_Potion(gp));
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_WarriorSword(gp));
        inventory.add(new OBJ_Axe(gp));
        inventory.add(new OBJ_ShieldWood(gp));
        inventory.add(new OBJ_BlueShield(gp));
    }

    public void speak() {

        facePlayer();
        getNPCImageDuringInteraction();
        gp.gameState = gp.tradeState;
        gp.ui.npc = this;
    }
}
