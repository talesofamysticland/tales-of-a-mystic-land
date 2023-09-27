package main.entity;

public class SavePoint{
    private Integer id;
    private String name;
    private Integer map;
    private Integer worldX;
    private Integer worldY;

    public Item(Integer id, String name, Integer map, Integer worldX, Integer worldY){
        this.id = id;
        this.name = name;
        this.map = map;
        this.worldX = worldX;
        this.worldY = worldY;
    }

    public String getName(){
        return name;
    }

    public Integer map(){
        return map;
    }

    public Integer worldX(){
        return worldX;
    }

    public Integer worldY(){
        return worldY;
    }
}   