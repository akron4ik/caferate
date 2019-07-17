package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "voices")
public class Voice extends AbstractBaseEntity {

    @Column(name = "date_time")
    @NotNull
    private LocalDateTime localDateTime;//дата когда проголосовали

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id", nullable = false)
    @NotNull
    private Cafe cafe;//айди кафе за которое проголосовали

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;//айди юзера который проголосовал

    public Voice(){

    }

    public Voice(LocalDateTime localDateTime){
        this(null, localDateTime);
    }

    public Voice(Integer id, LocalDateTime localDateTime){
        super(id);
        this.localDateTime = localDateTime;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Cafe getCafe() {
        return cafe;
    }

    public void setCafe(Cafe cafe) {
        this.cafe = cafe;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
