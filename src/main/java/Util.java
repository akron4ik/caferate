import model.Voice;
import to.VoiceTo;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Util {

    public static final LocalTime TIME = LocalTime.of(11, 00);

    public Util() {
    }

    public static LocalTime formatTime(LocalDateTime currentTime){
        return currentTime.toLocalTime();

    }

    public static boolean isVote(LocalDateTime currentTime){
        LocalTime onlyTime = formatTime(currentTime);
        return TIME.compareTo(onlyTime) >= 0 ? false : true;
    }

    public static VoiceTo getVoiceTo (Voice voice){

        return new VoiceTo(voice.getId(), voice.getUser().getId(), voice.getRestaurant().getId(), voice.getLocalDateTime(), isVote(voice.getLocalDateTime()));

    }
}
