package workplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import workplace.model.User;
import workplace.model.Voice;
import workplace.repository.restaurant.CrudRestaurantRepository;
import workplace.repository.user.CrudUserRepository;
import workplace.repository.voice.CrudVoiceRepository;
import workplace.to.VoiceTo;
import workplace.util.Util;

import java.util.List;

import static workplace.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoiceService {

    private final CrudVoiceRepository voiceRepository;
    private final CrudRestaurantRepository restaurantRepository;
    private final CrudUserRepository userRepository;

    @Autowired
    public VoiceService(CrudVoiceRepository voiceRepository, CrudRestaurantRepository restaurantRepository, CrudUserRepository userRepository) {
        this.voiceRepository = voiceRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    public Voice create(Voice voice, int userId){
        Assert.notNull(voice, " voice must be not null ");
        User user = userRepository.getOne(userId);
        voice.setUser(user);
        if(voice.isNew()){
            return voiceRepository.save(voice);
        } else {
                return get(voice.getId(), userId) == null ? null : voiceRepository.save(voice);
        }

    }

    public Voice create(VoiceTo voiceTo, int userId){
        Assert.notNull(voiceTo, " voice must be not null ");
        Voice voice = new Voice(null, voiceTo.getDate(),
                restaurantRepository.getRestaurantById(voiceTo.getRestaurantId()),
                userRepository.getOne(userId));
        if(voice.isNew()){
            return voiceRepository.save(voice);
        } else {
            return get(voice.getId(), userId) == null ? null : voiceRepository.save(voice);
        }
    }

    public void delete(int id, int userId){
        Util.checkVoteTime();
        checkNotFoundWithId(voiceRepository.deleteVoiceByIdAndUser_id(id, userId), id);

    }

    public Voice get(int id, int userId){
        return checkNotFoundWithId(voiceRepository.getVoiceByIdAndUser_Id(id, userId), id);
    }

    public void update(VoiceTo voiceTo, int userId){
        Assert.notNull(voiceTo, " voiceTo must be not null ");
        Voice voice = get(voiceTo.getId(), userId);
        Util.checkVoteDate(voiceTo.getDate(), voice.getLocalDate());
        Util.checkVoteTime();
        voice.setRestaurant(restaurantRepository.getRestaurantById(voiceTo.getRestaurantId()));
        checkNotFoundWithId(create(voice, userId), voice.getId());


    }
    public List<Voice> getAll(int userId){
        return voiceRepository.getAllByUser_Id(userId);
    }

}
