package model;

import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    public static final int START_SEQ = 100000;

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private int id;

    @Column(name = "name")
    private String name;//имя

    @Column(name = "appreciated")
    private boolean appreciated;//проголосовал

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
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
