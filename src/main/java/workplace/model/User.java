package workplace.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import org.hibernate.annotations.Cache;
import workplace.HasId;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email", name = "users_unique_email_idx"))
public class User extends AbstractBaseEntity implements HasId {

    @Column(name = "name", nullable = false)
    @Size(min = 2, max = 200)
    @NotBlank
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(min = 4, max = 100)
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 3, max = 200)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "enabled", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean enabled;

    @Column(name = "registered", nullable = false, columnDefinition = "TIMESTAMP DEFAULT now()")
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date registered = new Date();

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @BatchSize(size = 200)
    private Set<Role> roles;

    public User() {
    }

    public User(User u){
        this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.getRegistered(), u.isEnabled() ,u.getRoles());

    }

    public User(Integer id, String name, String email, String password, Role role, Role... roles) {
        this(id, name, email, password, new Date(), true, EnumSet.of(role, roles));
    }

    public User(Integer id, String name, String email, String password, Date registered, boolean enabled, Collection<Role> roles) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
        this.registered = registered;
        this.enabled = enabled;
        setRoles(roles);

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", registered=" + registered +
                ", enabled=" + enabled +
                ", roles=" + roles +
                ", id=" + id +
                '}';
    }
}
