package workplace.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import workplace.HasId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "voices", uniqueConstraints = @UniqueConstraint(columnNames = {"date_time", "user_id"}, name = "voices_unique_datetime_idx"))
public class Voice extends AbstractBaseEntity implements HasId {

    @Column(name = "date_time", nullable = false, columnDefinition = "DATE DEFAULT now()")
    @NotNull
    private LocalDate localDate;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", foreignKey = @ForeignKey(name = "GLOBAL_SEQ", foreignKeyDefinition = "START WITH 100000"))
    private Restaurant restaurant;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "GLOBAL_SEQ", foreignKeyDefinition = "START WITH 100000"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public Voice(){
    }

    public Voice(Voice v){
        this(v.getId(), v.getLocalDate(), v.getRestaurant(), v.getUser());
    }

    public Voice(Integer id, LocalDate localDate, Restaurant restaurant, User user){
        super(id);
        this.localDate = localDate;
        this.restaurant = restaurant;
        this.user = user;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Voice{" +
                "localDate=" + localDate +
                ", restaurant=" + restaurant +
                ", user=" + user +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voice voice = (Voice) o;
        return Objects.equals(localDate, voice.localDate) &&
                Objects.equals(restaurant, voice.restaurant) &&
                Objects.equals(user, voice.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(localDate, restaurant, user);
    }
}
