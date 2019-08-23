package workplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import workplace.model.Voice;
import workplace.repository.voice.DataJpaVoiceRepository;
import workplace.util.Util;

import java.time.LocalDate;
import java.util.List;

import static workplace.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoiceService {

    private final DataJpaVoiceRepository voiceRepository;

    @Autowired
    public VoiceService(DataJpaVoiceRepository voiceRepository) {
        this.voiceRepository = voiceRepository;
    }

    @CacheEvict(value = "voices", allEntries = true)
    public Voice create(Voice voice, int userId){
        Assert.notNull(voice, " voice must be not null ");
        if (Util.checkVoteTime()) {
            return voiceRepository.save(voice, userId);
        }
        else{
            return null;
        }
    }

    @CacheEvict(value = "voices", allEntries = true)
    public void delete(int id, int userId){
        if(Util.checkVoteTime()) {
            checkNotFoundWithId(voiceRepository.delete(id, userId), id);
        }
    }

    public Voice get(int id, int userId){

        return checkNotFoundWithId(voiceRepository.get(id, userId), id);
    }

    @CacheEvict(value = "voices", allEntries = true)
    public void update(Voice voice, int userId){
        Assert.notNull(voice, " voice must be not null ");
        if(Util.checkVoteTime()) {
            checkNotFoundWithId(voiceRepository.save(voice, userId), voice.getId());
        }
    }

    @Cacheable("voices")
    public List<Voice> getAll(int userId){
        return voiceRepository.getAll(userId);
    }

    public int getRating(int restaurantId, LocalDate date){
        return voiceRepository.getRateByRestaurant(restaurantId, date);
    }
}
