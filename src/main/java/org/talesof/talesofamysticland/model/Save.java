package org.talesof.talesofamysticland.model;

public class Save {
    
    private Integer id;
    private Integer playerId;
    private Integer slot;
    private String characterName;
    private String characterClass;

    public Save(Integer id, Integer playerId, Integer slot, String characterName, String characterClass) {
        this.id = id;
        this.playerId = playerId;
        this.slot = slot;
        this.characterName = characterName;
        this.characterClass = characterClass;
    }

    public Save(Integer playerId, Integer slot, String characterName, String characterClass) {
        this(null, playerId, slot, characterName, characterClass);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public void setcharacterName(String characterName) {
        this.characterName = characterName;
    }

    @Override
    public String toString() {
        return "Save [id=" + id + ", playerId=" + playerId + ", characterName=" + characterName + ", characterClass="
                + characterClass + "]\n";
    }
}
