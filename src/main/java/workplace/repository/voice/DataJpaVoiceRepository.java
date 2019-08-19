package workplace.repository.voice;

import org.springframework.transaction.annotation.Transactional;
import workplace.model.User;
import workplace.model.Voice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import workplace.repository.user.CrudUserRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class DataJpaVoiceRepository {

    @Autowired
    private CrudVoiceRepository voiceRepository;

    @Autowired
    private CrudUserRepository userRepository;

    @Transactional
    public Voice save(Voice voice, int userId) {
        User user = userRepository.findById(userId).orElse(null);
        voice.setUser(user);
        if(voice.isNew()){
            return voiceRepository.save(voice);
        } else {
            return get(voice.getId(), userId) == null ? null : voiceRepository.save(voice);
        }
    }

    @Transactional
    public boolean delete(int id, int userId) {
        return voiceRepository.deleteVoiceByIdAndUser_id(id, userId) != 0;
    }

    public Voice get(int id, int userId) {
        return voiceRepository.getVoiceByIdAndUser_Id(id, userId);
    }

    public List<Voice> getAll(int userId) {
        return voiceRepository.getAllByUser_Id(userId);
    }

    public int getRateByRestaurant(int restaurantId, LocalDate date){
        return voiceRepository.getCountRestaurantVoicesByDate(restaurantId, date);
    }
}
