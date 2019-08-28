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
import workplace.util.Util;
import workplace.web.json.JsonUtil;
import workplace.web.voice.VoiceRestController;
import java.time.LocalDate;
import static caferate.RestaurantTestData.*;
import static caferate.TestUtil.*;
import static caferate.UserTestData.*;
import static caferate.VoiceTestData.*;
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
                .andExpect(result -> VoiceTestData.assertMatch(readFromJsonMvcResult(result, Voice.class), VOICE_1));
    }

    @Test
    void createWithLocation() throws Exception {//проголосовать можно только до 11:00
        Voice expected = new Voice(null, LocalDate.now(), RESTAURANT_2, USER_4);
        if(Util.checkVoteTime()) {

            ResultActions action = mockMvc.perform(MockMvcRequestBuilders.post(REST_VOICE)
                    .with(userAuth(USER_4))
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtil.writeValue(expected)))
                    .andDo(print())
                    .andExpect(status().isCreated());

            Voice returned = readFromJson(action, Voice.class);
            expected.setId(returned.getId());

            VoiceTestData.assertMatch(returned, expected);//после 11:00 тест работать не будет
        }
        else{
            System.out.println("You can't voice after 11:00");
        }
    }

    @Test
    void delete() throws Exception {
        if(Util.checkVoteTime()) {
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_VOICE + VOICE_1_ID)
                .with(userAuth(USER_2)))
                .andDo(print())
                .andExpect(status().isOk());
        VoiceTestData.assertMatch(voiceService.getAll(USER_2_ID), VOICE_4, VOICE_5 );
        }
        else{
            System.out.println("You can't delete voice after 11:00");
        }

    }

    @Test
    void update() throws Exception {
        Voice updated = VOICE_3;
        updated.setRestaurant(RESTAURANT_3);
        if(Util.checkVoteTime()) {
        mockMvc.perform(MockMvcRequestBuilders.put(REST_VOICE + VOICE_3_ID)
                .with(userAuth(USER_4))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        VoiceTestData.assertMatch(voiceService.get(VOICE_3_ID, USER_4_ID), updated);
    }
        else{
        System.out.println("You can't update voice after 11:00");
    }


}

    @Test
    void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_VOICE+ "all")
                .with(userAuth(USER_2)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(VOICE_1, VOICE_4, VOICE_5));

    }
}
