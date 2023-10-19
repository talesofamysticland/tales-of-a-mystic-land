package main.model;

public class ItemInInventory {
    private Integer id;
    private Integer idCharacterState;
    private Integer idItem;
    private Integer amount;
    private Integer position;

    public ItemInInventory(Integer id, Integer idCharacterState, Integer idItem, Integer amount, Integer position){
        this.id = id;
        this.idCharacterState = idCharacterState;
        this.idItem = idItem;
        this.amount = amount;
        this.position = position;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getIdCharacterState() {
        return idCharacterState;
    }

    public void setIdCharacterState(Integer idCharacterState) {
        this.idCharacterState = idCharacterState;
    }

    
    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
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
}