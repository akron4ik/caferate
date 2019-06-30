package model;

import java.util.List;

public class Cafe {

    private int id;
    private String name;
    private List<String> menu;
    private int rating;

    public Cafe() {
    }

    public Cafe(int id, String name, List<String> menu, int rating) {
        this.id = id;
        this.name = name;
        this.menu = menu;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getMenu() {
        return menu;
    }

    public void setMenu(List<String> menu) {
        this.menu = menu;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Cafe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", menu=" + menu +
                ", rating=" + rating +
                '}';
    }
}
