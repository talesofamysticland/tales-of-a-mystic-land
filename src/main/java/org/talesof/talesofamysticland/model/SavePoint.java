package org.talesof.talesofamysticland.model;

public class SavePoint{

    private Integer id;
    private String name;
    private Integer map;
    private Integer worldX;
    private Integer worldY;

    public SavePoint(Integer id, String name, Integer map, Integer worldX, Integer worldY){
        this.id = id;
        this.name = name;
        this.map = map;
        this.worldX = worldX;
        this.worldY = worldY;
    }

    public SavePoint(String name, Integer map, Integer worldX, Integer worldY){
        this(null, name, map, worldX, worldY);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMap(){
        return map;
    }

    public void setMap(Integer map) {
        this.map = map;
    }

    public Integer getWorldX() {
        return worldX;
    }

    public void setWorldX(Integer worldX) {
        this.worldX = worldX;
    }

    public Integer getWorldY() {
        return worldY;
    }

    public void setWorldY(Integer worldY) {
        this.worldY = worldY;
    }

    @Override
    public String toString() {
        return "SavePoint [id=" + id + ", name=" + name + ", map=" + map + ", worldX=" + worldX + ", worldY=" + worldY
                + "]\n";
    }
}   