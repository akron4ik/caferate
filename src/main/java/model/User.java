package model;

public class User {
    private int id;
    private String name;
    private boolean appreciated;

    public User() {
    }

    public User(int id, String name, boolean appreciated) {
        this.id = id;
        this.name = name;
        this.appreciated = appreciated;
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

    public boolean isAppreciated() {
        return appreciated;
    }

    public void setAppreciated(boolean appreciated) {
        this.appreciated = appreciated;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", appreciated=" + appreciated +
                '}';
    }
}
