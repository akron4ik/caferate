package workplace.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import workplace.HasId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Entity
@Table(name = "voices", uniqueConstraints = @UniqueConstraint(columnNames = {"date_time", "user_id"}, name = "voices_unique_datetime_idx"))
public class Voice extends AbstractBaseEntity implements HasId {

    @Column(name = "date_time", nullable = false)
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate localDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", foreignKey = @ForeignKey(name = "GLOBAL_SEQ", foreignKeyDefinition = "START WITH 100000"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
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


}
