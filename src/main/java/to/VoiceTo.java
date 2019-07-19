package to;

import java.time.LocalDateTime;

public class VoiceTo {
private int id;
private int userId;
private int restaurantId;
private LocalDateTime time;
private boolean vote;

    public VoiceTo(int id, int userId, int restaurantId, LocalDateTime time, boolean vote) {
        this.id = id;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.time = time;
        this.vote = vote;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public boolean isVote() {
        return vote;
    }

    public void setVote(boolean vote) {
        this.vote = vote;
    }
}
