package main.model;

public class CharacterState {
    
    private Integer id;
    private Integer playTime;
    private Integer experience;
    private Integer coins;
    private Integer strength;
    private Integer resistance;
    private Integer constitution;
    private Integer dexterity;
    private Integer wisdom;

    public CharacterState (Integer id, Integer playTime, Integer experience, Integer coins, Integer strength, Integer resistance, Integer constitution, Integer dexterity, Integer wisdom){
        this.id = id;
        this.playTime = playTime;
        this.experience = experience;
        this.coins = coins;
        this.strength = strength;
        this.resistance = resistance;
        this.constitution = constitution;
        this.dexterity = dexterity;
        this.wisdom = wisdom;
    }

    public CharacterState (Integer id){
        this.id = id;
        this.playTime = 0;
        this.experience = 0;
        this.coins = 0;
        this.strength = 0;
        this.resistance = 0;
        this.constitution = 0;
        this.dexterity = 0;
        this.wisdom = 0;
    }

    public Integer getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    public Integer getConstitution() {
        return constitution;
    }

    public void setConstitution(Integer constitution) {
        this.constitution = constitution;
    }
    
    public Integer getDexterity() {
        return dexterity;
    }

    public void setDexterity(Integer dexterity) {
        this.dexterity = dexterity;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlayTime() {
        return playTime;
    }

    public void setPlayTime(Integer playTime) {
        this.playTime = playTime;
    }

    public Integer getResistance() {
        return resistance;
    }

    public void setResistance(Integer resistance) {
        this.resistance = resistance;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getWisdom() {
        return wisdom;
    }

    public void setWisdom(Integer wisdom) {
        this.wisdom = wisdom;
    }
}
