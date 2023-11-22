package org.talesof.talesofamysticland.model;

public class ItemInInventory {

    private Integer id;
    private Integer characterStateId;
    private Integer itemId;
    private Integer amount;
    private boolean currentEquipped;

    public ItemInInventory(Integer id, Integer characterStateId, Integer itemId, Integer amount, boolean currentEquipped){
        this.id = id;
        this.characterStateId = characterStateId;
        this.itemId = itemId;
        this.amount = amount;
        this.currentEquipped = currentEquipped;
    }

    public ItemInInventory(Integer characterStateId, Integer itemId, Integer amount, boolean currentEquipped){
        this(null, characterStateId, itemId, amount, currentEquipped);
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getcharacterStateId() {
        return characterStateId;
    }

    public void setcharacterStateId(Integer characterStateId) {
        this.characterStateId = characterStateId;
    }

    
    public Integer getitemId() {
        return itemId;
    }

    public void setitemId(Integer itemId) {
        this.itemId = itemId;
    }
    
    public Integer getAmount() {
        return amount;
    }
    
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getCharacterStateId() {
        return characterStateId;
    }

    public void setCharacterStateId(Integer characterStateId) {
        this.characterStateId = characterStateId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public boolean isCurrentEquipped() {
        return currentEquipped;
    }

    public void setCurrentEquipped(boolean currentEquipped) {
        this.currentEquipped = currentEquipped;
    }

    @Override
    public String toString() {
        return "ItemInInventory [id=" + id + ", characterStateId=" + characterStateId + ", itemId=" + itemId
                + ", amount=" + amount + ", currentEquipped=" + currentEquipped + "]\n";
    }
}