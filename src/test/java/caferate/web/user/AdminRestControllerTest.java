package caferate.web.user;

import caferate.UserTestData;
import caferate.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import workplace.model.Role;
import workplace.model.User;
import workplace.web.user.AdminRestController;

import java.util.Collections;

import static caferate.TestUtil.*;
import static caferate.UserTestData.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdminRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = AdminRestController.REST_URL + '/';

    @Test
    void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + ADMIN_ID)
                /*.with(userHttpBasic(ADMIN)))*/
                .with(userAuth(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(ADMIN));
    }

    @Test
    void getByEmail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + "by?email=" + ADMIN.getEmail())
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(ADMIN));
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_URL + USER_2_ID)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertMatch(userService.getAll(), ADMIN, USER_3, USER_4, USER_5, USER_6);
    }

    @Test
    void update() throws Exception {
        User updated = new User(USER_2);
        updated.setName("UpdatedName");
        updated.setRoles(Collections.singletonList(Role.ROlE_ADMIN));
        mockMvc.perform(MockMvcRequestBuilders.put(REST_URL + USER_2_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(jsonWithPassword(updated, USER_2.getPassword())))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertMatch(userService.get(USER_2_ID), updated);
    }

    @Test
    void createWithLocation() throws Exception {
        User expected = new User(null, "New", "new@gmail.com", "newPass", Role.ROlE_USER);
        ResultActions action = mockMvc.perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(jsonWithPassword(expected, "newPass")))
                .andDo(print())
                .andExpect(status().isCreated());

        User returned = readFromJson(action, User.class);
        expected.setId(returned.getId());

        assertMatch(returned, expected);
        assertMatch(userService.getAll(), expected, ADMIN, USER_2, USER_3, USER_4, USER_5, USER_6);
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL)
                /*.with(userHttpBasic(ADMIN)))*/
                .with(userAuth(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(UserTestData.contentJson(ADMIN, USER_2, USER_3, USER_4, USER_5, USER_6));
    }

    @Test
    void enable() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch(REST_URL + USER_2_ID).param("enabled", "false")
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertFalse(userService.get(USER_2_ID).isEnabled());
    }
}
