package workplace.repository.voice;

import org.springframework.transaction.annotation.Transactional;
import workplace.model.User;
import workplace.model.Voice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import workplace.repository.user.CrudUserRepository;

import java.util.List;

@Repository
public class DataJpaVoiceRepository {

    @Autowired
    CrudVoiceRepository voiceRepository;

    @Autowired
    CrudUserRepository userRepository;

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

    public boolean delete(int id, int userId) {
        return voiceRepository.delete(id, userId) != 0;
    }

    public Voice get(int id, int userId) {
        return voiceRepository.getVoiceByIdAndUserId(id, userId);
    }

    public List<Voice> getAll() {
        return voiceRepository.getAll();
    }

    public List<Voice> getAllVoicesByUser(int userId){
        return voiceRepository.getAllUserVoices(userId);
    }
}
