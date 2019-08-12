package workplace.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "voices", uniqueConstraints = @UniqueConstraint(columnNames = {"date_time", "user_id"}, name = "voices_unique_datetime_idx"))
public class Voice extends AbstractBaseEntity {

    @Column(name = "date_time")
    @NotNull
    private LocalDateTime localDateTime;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    private Restaurant restaurant;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    public Voice(){
    }

    public Voice(Voice v){
        this(v.getId(), v.getLocalDateTime(), v.getUser(), v.getRestaurant());
    }

    public Voice(Integer id, LocalDateTime localDateTime, User user, Restaurant restaurant){
        super(id);
        this.localDateTime = localDateTime;
        this.user = user;
        this.restaurant = restaurant;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
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
                "localDateTime=" + localDateTime +
                ", restaurant=" + restaurant +
                ", user=" + user +
                ", id=" + id +
                '}';
    }
}
