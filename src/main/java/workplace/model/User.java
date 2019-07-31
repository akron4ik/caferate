package workplace.model;

import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends AbstractBaseEntity{

    @Column(name = "name")
    private String name;//имя

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;//роль

    public User() {
    }

    public User(User u){
        this(u.getId(), u.getName(), u.getRoles());

    }
    public User(int id, String name, Role role, Role... roles){
        this(id, name,  EnumSet.of(role, roles));
    }

    public User(Integer id, String name, Collection<Role> roles) {
        super(id);
        this.name = name;
        setRoles(roles);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                ", name='" + name +"";
    }
}
