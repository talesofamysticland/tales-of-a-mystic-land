package main.entity;

public class Resolution {

    private Integer id;
    private Integer width;
    private Integer height;

    public Resolution(Integer id, Integer width, Integer height) {
        this.id = id;
        this.width = width;
        this.height = height;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Resolution [ " + getId + ", " + getWidth + " : " + getHeight + " ]";
    }
}