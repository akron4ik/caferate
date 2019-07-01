package model;

import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

public class User {
    private int id;
    private String name;//имя
    private boolean appreciated;//проголосовал
    private Set<Role> roles;//роль

    public User() {
    }

    public User(User u){
        this(u.getId(), u.getName(), u.isAppreciated(), u.getRoles());

    }
    public User(int id, String name, boolean appreciated, Role role, Role... roles){
        this(id, name, appreciated, EnumSet.of(role, roles));
    }

    public User(int id, String name, boolean appreciated, Collection<Role> roles) {
        this.id = id;
        this.name = name;
        this.appreciated = appreciated;
        setRoles(roles);

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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
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
