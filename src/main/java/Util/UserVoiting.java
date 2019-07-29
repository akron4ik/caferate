package Util;

import model.User;
import model.Voice;
import org.springframework.beans.factory.annotation.Autowired;
import repository.datajpa.DataJpaMealRepository;
import repository.datajpa.DataJpaRestaurantRepository;
import repository.datajpa.DataJpaUserRepository;
import repository.datajpa.DataJpaVoiceRepository;
import to.VoiceTo;

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
    public Voice canUserVoit(Voice voice){
        if(isVote(LocalTime.now())){
            voiceRepository.save(voice);
        }
        return voice;

    }

    public static boolean isVote(LocalTime currentTime){
        return TIME.compareTo(currentTime) < 0;
    }

}
