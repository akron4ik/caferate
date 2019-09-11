package caferate.web.voice;


import caferate.VoiceTestData;
import caferate.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import workplace.model.Voice;
import workplace.service.VoiceService;
import workplace.to.VoiceTo;
import workplace.util.VoiceUtil;
import workplace.web.json.JsonUtil;
import workplace.web.voice.VoiceRestController;
import java.time.LocalTime;

import static caferate.RestaurantTestData.*;
import static caferate.TestUtil.*;
import static caferate.UserTestData.*;
import static caferate.VoiceTestData.*;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VoiceRestControllerTest extends AbstractControllerTest {
    private final String REST_VOICE = VoiceRestController.REST_VOICE_URL +"/";

    @Autowired
    VoiceService voiceService;

    @Test
    void get() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get(REST_VOICE + VOICE_1_ID)
                .with(userAuth(USER_2))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> VoiceTestData.assertMatch(readFromJsonMvcResult(result, VoiceTo.class), VoiceUtil.asTo(VOICE_1)));
    }

    @Test
    void createWithLocation() throws Exception {
        VoiceTo expected = new VoiceTo(null, RESTAURANT_2_ID);

            ResultActions action = mockMvc.perform(MockMvcRequestBuilders.post(REST_VOICE)
                    .with(userAuth(USER_4))
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtil.writeValue(expected)))
                    .andDo(print())
                    .andExpect(status().isCreated());

            Voice returned = readFromJson(action, Voice.class);
            expected.setId(returned.getId());
            VoiceTo actual = new VoiceTo(returned.getId(), returned.getLocalDate(), returned.getRestaurant().getId());

            VoiceTestData.assertMatch(actual, expected);
    }

    @Test
    void delete() throws Exception {

        assumeFalse(LocalTime.now().isAfter(LocalTime.of(11,0)), "Time for voting is Over, you may change your voice before 11AM");
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_VOICE + VOICE_1_ID)
                .with(userAuth(USER_2)))
                .andDo(print())
                .andExpect(status().isOk());


    }

    @Test
    void update() throws Exception {
        assumeFalse(LocalTime.now().isAfter(LocalTime.of(11,0)), "Time for voting is Over, you may change your voice before 11AM");
        VoiceTo updated = VoiceUtil.asTo(VOICE_3);
        updated.setRestaurantId(RESTAURANT_3_ID);
        mockMvc.perform(MockMvcRequestBuilders.put(REST_VOICE + VOICE_3_ID)
                .with(userAuth(USER_4))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))).andDo(print())
                .andExpect(status().isNoContent());

        VoiceTo actual = VoiceUtil.asTo(voiceService.get(VOICE_3_ID, USER_4_ID));
        VoiceTestData.assertMatch(actual, updated);


}

    @Test
    void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_VOICE)
                .with(userAuth(USER_2)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}
