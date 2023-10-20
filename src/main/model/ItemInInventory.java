package main.model;

public class ItemInInventory {

    private Integer id;
    private Integer characterStateId;
    private Integer itemId;
    private Integer amount;
    private Integer position;

    public ItemInInventory(Integer id, Integer characterStateId, Integer itemId, Integer amount, Integer position){
        this.id = id;
        this.characterStateId = characterStateId;
        this.itemId = itemId;
        this.amount = amount;
        this.position = position;
    }

    public ItemInInventory(Integer characterStateId, Integer itemId, Integer amount, Integer position){
        this(null, characterStateId, itemId, amount, position);
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

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "ItemInInventory [id=" + id + ", characterStateId=" + characterStateId + ", itemId=" + itemId
                + ", amount=" + amount + ", position=" + position + "]\n";
    }
}