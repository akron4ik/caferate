package caferate.service;

import caferate.VoiceTestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import workplace.model.Voice;
import workplace.repository.voice.CrudVoiceRepository;
import workplace.service.VoiceService;
import workplace.util.exception.NotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;

import static caferate.RestaurantTestData.RESTAURANT_4;
import static caferate.UserTestData.*;
import static caferate.VoiceTestData.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

public class VoiceServiceTest extends AbstractServiceTest {

    @Autowired
    VoiceService voiceService;

    @Autowired
    CrudVoiceRepository repository;

    @Test
    void create() throws Exception{
        Voice voice = new Voice(null, LocalDate.of(2015, 6, 1), RESTAURANT_4, USER_12);
        int userId = USER_12.getId();
        voiceService.create(voice, userId);
        VoiceTestData.assertMatch(voiceService.getAll(userId), VOICE_11, voice);

    }

    @Test
    void update() throws Exception{
        assumeFalse(LocalTime.now().isAfter(LocalTime.of(11,0)), "Time for voting is Over, you may change your voice before 11AM");
        Voice voice = new Voice(VOICE_1);
        voice.setRestaurant(RESTAURANT_4);
        int userId = VOICE_1.getUser().getId();
        voiceService.create(voice, userId);
        VoiceTestData.assertMatch(voiceService.get(VOICE_1_ID, userId), voice);
    }

    @Test
    void delete() throws Exception{
        assumeFalse(LocalTime.now().isAfter(LocalTime.of(11,0)), "Time for voting is Over, you may change your voice before 11AM");
        voiceService.delete(VOICE_3_ID, USER_4_ID);
        VoiceTestData.assertMatch(voiceService.get(VOICE_3_ID + 19, USER_4_ID ), VOICE_22);
    }

    @Test
    void getAll() throws Exception{
        VoiceTestData.assertMatch(voiceService.getAll(USER_2_ID), VOICE_1, VOICE_20);
    }

    @Test
    void get() throws Exception{
        VoiceTestData.assertMatch(voiceService.get(VOICE_1_ID, USER_2_ID), VOICE_1);
    }

    @Test
    void deleteNotFound() throws Exception{
        assumeFalse(LocalTime.now().isAfter(LocalTime.of(11,0)), "Time for voting is Over, you may change your voice before 11AM");
            assertThrows(NotFoundException.class, () -> voiceService.delete(1, USER_2_ID));

    }

    @Test
    void deleteNotOwn() throws Exception{
        assumeFalse(LocalTime.now().isAfter(LocalTime.of(11,0)), "Time for voting is Over, you may change your voice before 11AM");
        assertThrows(NotFoundException.class, () -> repository.deleteVoiceByIdAndUser_id(VOICE_2_ID, USER_2_ID));

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
