package caferate.service;

import caferate.VoiceTestData;
import caferate.VoiceTestData.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import workplace.model.Voice;
import workplace.repository.voice.DataJpaVoiceRepository;
import workplace.service.VoiceService;
import workplace.util.Util;
import workplace.util.exception.NotFoundException;

import java.time.LocalDate;

import static caferate.RestaurantTestData.RESTAURANT_1;
import static caferate.RestaurantTestData.RESTAURANT_4;
import static caferate.UserTestData.*;
import static caferate.VoiceTestData.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DataJpaVoiceServiceTest extends AbstractServiceTest {

    @Autowired
    VoiceService voiceService;

    @Autowired
    DataJpaVoiceRepository repository;

    @BeforeEach
    void setUp() throws Exception {
        cacheManager.getCache("voices").clear();
        jpaUtil.clear2ndLevelHibernateCache();
    }

    @Test
    void createWithTimeRules() throws Exception{
        Voice voice = new Voice(null, LocalDate.of(2015, 6, 1), RESTAURANT_1, USER_4);
        int userId = USER_4.getId();
        if (Util.checkVoteTime()) {
            voiceService.create(voice, userId);
            VoiceTestData.assertMatch(voiceService.getAll(userId), VOICE_3, voice);
        }else{
            voiceService.create(voice, userId);
            VoiceTestData.assertMatch(voiceService.get(VOICE_3.getId(), userId), VOICE_3);
        }
    }

    @Test
    void create() throws Exception{
        Voice voice = new Voice(null, LocalDate.of(2015, 6, 1), RESTAURANT_1, USER_4);
        int userId = USER_4.getId();
        repository.save(voice, userId);
        VoiceTestData.assertMatch(voiceService.getAll(userId), VOICE_3, voice);
    }

    @Test
    void update() throws Exception{
        Voice voice = new Voice(VOICE_1);
        voice.setRestaurant(RESTAURANT_4);
        int userId = VOICE_1.getUser().getId();
        repository.save(voice, userId);
        VoiceTestData.assertMatch(voiceService.get(VOICE_1_ID, userId), voice);
    }

    @Test
    void updateWithTimeRules() throws Exception{
        Voice voice = new Voice(VOICE_1);
        voice.setRestaurant(RESTAURANT_4);
        int userId = VOICE_1.getUser().getId();
        if(Util.checkVoteTime()) {
            voiceService.update(voice, userId);
        }
        VoiceTestData.assertMatch(voiceService.get(VOICE_1_ID, userId), voice);
    }

    @Test
    void delete() throws Exception{
        repository.delete(VOICE_1_ID, USER_2_ID);
        VoiceTestData.assertMatch(voiceService.getAll(USER_2_ID), VOICE_4, VOICE_5);
    }

    @Test
    void deleteWithTimeRules() throws Exception{
       if(Util.checkVoteTime()) {
           voiceService.delete(VOICE_1_ID, USER_2_ID);
           VoiceTestData.assertMatch(voiceService.getAll(USER_2_ID), VOICE_4, VOICE_5);
       }else {
           VoiceTestData.assertMatch(voiceService.getAll(USER_2_ID), VOICE_1, VOICE_4, VOICE_5);
       }
    }

    @Test
    void getAll() throws Exception{
        VoiceTestData.assertMatch(voiceService.getAll(USER_2_ID), VOICE_1, VOICE_4, VOICE_5);
    }

    @Test
    void get() throws Exception{
        VoiceTestData.assertMatch(voiceService.get(VOICE_1_ID, USER_2_ID), VOICE_1);
    }

    @Test
    void deleteNotFound() throws Exception{
        if(Util.checkVoteTime()) {
            assertThrows(NotFoundException.class, () -> voiceService.delete(1, USER_2_ID));
        }
    }

    @Test
    void deleteNotOwn() throws Exception{
        if(Util.checkVoteTime()) {
            assertThrows(NotFoundException.class, () -> repository.delete(VOICE_2_ID, USER_2_ID));
        }
    }

    @Test
    void getNotFound() throws Exception{
        assertThrows(NotFoundException.class, ()-> voiceService.get(1,USER_2_ID));
    }

    @Test
    void getNotOwn() throws Exception{
        assertThrows(NotFoundException.class, ()-> voiceService.get(VOICE_2_ID, USER_2_ID));
    }
}
