package workplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import workplace.model.Voice;
import workplace.repository.restaurant.DataJpaRestaurantRepository;
import workplace.repository.user.DataJpaUserRepository;
import workplace.repository.voice.DataJpaVoiceRepository;
import workplace.to.VoiceTo;
import workplace.util.Util;

import java.time.LocalDate;
import java.util.List;

import static workplace.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoiceService {

    private final DataJpaVoiceRepository voiceRepository;
    private final DataJpaRestaurantRepository restaurantRepository;
    private final DataJpaUserRepository userRepository;

    @Autowired
    public VoiceService(DataJpaVoiceRepository voiceRepository, DataJpaRestaurantRepository restaurantRepository, DataJpaUserRepository userRepository) {
        this.voiceRepository = voiceRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
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
    public Voice create(VoiceTo voiceTo, int userId){
        Assert.notNull(voiceTo, " voice must be not null ");
        Voice voice = new Voice(null, voiceTo.getDate(), restaurantRepository.get(voiceTo.getRestaurantId()), userRepository.get(voiceTo.getUserId()));
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

    @CacheEvict(value = "voices", allEntries = true)
    public void update(VoiceTo voiceTo, int userId){
        Assert.notNull(voiceTo, " voiceTo must be not null ");
        Voice voice = get(voiceTo.getId(), userId);
        voice.setRestaurant(restaurantRepository.get(voiceTo.getRestaurantId()));
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

    public int getBetweenDates(int restaurantId, @Nullable LocalDate startDate, @Nullable LocalDate endDate){
        return voiceRepository.getRatingBetween(restaurantId, Util.adjustStartDate(startDate), Util.adjustEndDate(endDate));
    }
}
