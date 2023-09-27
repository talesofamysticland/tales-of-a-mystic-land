package main.entity;

public class Item{
    private Integer id;
    private String name;

    public Item(Integer id, String name){
        this.id = id;
        this.name = name;
    }

    public String getName(){
        return name;
    }
}   