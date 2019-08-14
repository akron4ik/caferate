package workplace.util;

import workplace.model.Voice;
import org.springframework.beans.factory.annotation.Autowired;
import workplace.repository.meal.DataJpaMealRepository;
import workplace.repository.restaurant.DataJpaRestaurantRepository;
import workplace.repository.user.DataJpaUserRepository;
import workplace.repository.voice.DataJpaVoiceRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class UserVoiting {
    public static final LocalTime TIME = LocalTime.of(11, 00);

    @Autowired
    DataJpaRestaurantRepository restaurantRepository;
    @Autowired
    DataJpaMealRepository mealRepository;
    @Autowired
    DataJpaUserRepository userRepository;
    @Autowired
    DataJpaVoiceRepository voiceRepository;

    public UserVoiting() {
    }

    public static LocalTime formatTime(LocalDateTime currentTime){
        return currentTime.toLocalTime();

    }
    /*public Voice canUserVoit(Voice voice){
        if(isVote(LocalTime.now())){
            voiceRepository.save(voice);
        }
        return voice;

    }*/

    public static boolean isVote(LocalTime currentTime){
        return TIME.compareTo(currentTime) < 0;
    }

}
