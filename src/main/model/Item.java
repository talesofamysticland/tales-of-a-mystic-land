package main.model;

public class Item{

    private Integer id;
    private String name;

    public Item(Integer id, String name){
        this.id = id;
        this.name = name;
    }

    public Item(String name){
        this(null, name);
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

    @Override
    public String toString() {
        return "Item [id=" + id + ", name=" + name + "]\n";
    }
}   