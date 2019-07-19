package repository.datajpa;

import model.Voice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataJpaVoiceRepository {

    @Autowired
    CrudVoiceRepository voiceRepository;

    public Voice save(Voice voice) {
        return voiceRepository.save(voice);
    }

    public boolean delete(int id) {
        return voiceRepository.delete(id) != 0;
    }

    public Voice get(int id) {
        return voiceRepository.get(id);
    }

    public List<Voice> getAll() {
        return voiceRepository.getAll();
    }
}
