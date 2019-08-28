package caferate.web.user;

import caferate.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import workplace.model.User;
import workplace.to.UserTo;
import workplace.util.UserUtil;
import workplace.web.json.JsonUtil;
import workplace.web.user.ProfileRestController;

import static caferate.TestUtil.*;
import static caferate.UserTestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProfileRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = ProfileRestController.REST_URL + "/";

    @Test
    void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(USER_2)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(USER_2));
    }

    @Test
    void getUnAuth() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_URL)
                .with(userHttpBasic(USER_2)))
                .andExpect(status().isNoContent());
        assertMatch(userService.getAll(), USER_1, USER_3, USER_4, USER_5, USER_6);
    }

    @Test
    void register() throws Exception {
        UserTo createdTo = new UserTo(null, "newName", "newemail@ya.ru", "newPassword");

        ResultActions action = mockMvc.perform(MockMvcRequestBuilders.post(REST_URL + "register")
                .with(userAuth(USER_2))
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonWithPassword(createdTo, createdTo.getPassword())))
                .andDo(print())
                .andExpect(status().isCreated());
        User returned = readFromJson(action, User.class);

        User created = UserUtil.createNewFromTo(createdTo);
        created.setId(returned.getId());

        assertMatch(returned, created);
        assertMatch(userService.getByEmail("newemail@ya.ru"), created);
    }

    @Test
    void update() throws Exception {
        UserTo updatedTo = new UserTo(null, "newName", "newemail@ya.ru", "newPassword");
        mockMvc.perform(MockMvcRequestBuilders.put(REST_URL)
                .with(userAuth(USER_2))
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonWithPassword(updatedTo, updatedTo.getPassword())))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertMatch(userService.getByEmail("newemail@ya.ru"), UserUtil.updateFromTo(new User(USER_2), updatedTo));
    }


   /* @Test
    void updateInvalid() throws Exception {
        UserTo updatedTo = new UserTo(null, null, "password", null);

        mockMvc.perform(MockMvcRequestBuilders.put(REST_URL).contentType(MediaType.APPLICATION_JSON)
                *//*.with(userHttpBasic(USER_2))*//*
                .with(userAuth(USER_2))
                .content(JsonUtil.writeValue(updatedTo)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }*/

    /*@Test
    @Transactional(propagation = Propagation.NEVER)
    void updateDuplicate() throws Exception {
        UserTo updatedTo = new UserTo(null, "newName", "admin@gmail.com", "newPassword");

        mockMvc.perform(MockMvcRequestBuilders.put(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER_2))
                .content(JsonUtil.writeValue(updatedTo)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }*/
}
