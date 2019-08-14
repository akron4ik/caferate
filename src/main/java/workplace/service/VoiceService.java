package workplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workplace.model.Voice;
import workplace.repository.voice.DataJpaVoiceRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class VoiceService {

    private final DataJpaVoiceRepository voiceRepository;

    @Autowired
    public VoiceService(DataJpaVoiceRepository voiceRepository) {
        this.voiceRepository = voiceRepository;
    }

    public Voice create(Voice voice, int userId){
       return voiceRepository.save(voice, userId);
    }

    public Voice get(int id, int userId){
        return voiceRepository.get(id, userId);
    }

    public void update(Voice voice, int userId){
        voiceRepository.save(voice, userId);
    }

    public List<Voice> getAll(int userId){
        return voiceRepository.getAll(userId);
    }

    public int getRaiting(int restaurantId, LocalDate date){
        return voiceRepository.getRateByRestaurant(restaurantId, date);
    }
}
