package workplace.to;

import workplace.model.Voice;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class VoiceTo extends BaseTo implements Serializable {

private int userId;

private int restaurantId;

@NotNull
private LocalDate date = LocalDate.now();

public VoiceTo(){

}

public VoiceTo(VoiceTo voiceTo){
       this(voiceTo.getId(), voiceTo.getDate(), voiceTo.getRestaurantId(), voiceTo.getUserId());
     }

     public VoiceTo(Integer id, LocalDate date, int restaurantId, int userId) {
       super(id);
       this.date = date;
       this.restaurantId = restaurantId;
       this.userId = userId;


    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "VoiceTo{" +
                "userId=" + userId +
                ", restaurantId=" + restaurantId +
                ", date=" + date +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoiceTo voiceTo = (VoiceTo) o;
        return userId == voiceTo.userId &&
                restaurantId == voiceTo.restaurantId &&
                Objects.equals(date, voiceTo.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, restaurantId, date);
    }
}
