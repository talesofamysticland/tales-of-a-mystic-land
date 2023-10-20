package main.model;

public class CharacterState {
    
    private Integer id;
    private Long playTime;
    private Integer experience;
    private Integer coins;
    private Integer strength;
    private Integer resistance;
    private Integer constitution;
    private Integer dexterity;
    private Integer wisdom;

    public CharacterState (Integer id, Long playTime, Integer experience, Integer coins, Integer strength, Integer resistance, Integer constitution, Integer dexterity, Integer wisdom){
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

    public CharacterState (Long playTime, Integer experience, Integer coins, Integer strength, Integer resistance, Integer constitution, Integer dexterity, Integer wisdom){
        this(null, playTime, experience, coins, strength, resistance, constitution, dexterity, wisdom);
    }

    public CharacterState (Integer id){
        this(id, 0L, 1, 0, 1, 1, 1, 1, 1);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getPlayTime() {
        return playTime;
    }

    public void setPlayTime(Long playTime) {
        this.playTime = playTime;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getResistance() {
        return resistance;
    }

    public void setResistance(Integer resistance) {
        this.resistance = resistance;
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

    public Integer getWisdom() {
        return wisdom;
    }

    public void setWisdom(Integer wisdom) {
        this.wisdom = wisdom;
    }

    @Override
    public String toString() {
        return "CharacterState [id=" + id + ", playTime=" + playTime + ", experience=" + experience + ", coins=" + coins
                + ", strength=" + strength + ", resistance=" + resistance + ", constitution=" + constitution
                + ", dexterity=" + dexterity + ", wisdom=" + wisdom + "]\n";
    }
}
