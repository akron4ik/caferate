package caferate.web.meal;

import caferate.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import workplace.model.Meal;
import workplace.service.MealService;
import workplace.web.meal.MealRestController;

import static caferate.MealTestData.*;
import static caferate.TestUtil.readFromJsonMvcResult;
import static caferate.TestUtil.userHttpBasic;
import static caferate.UserTestData.USER_1;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static workplace.web.meal.MealRestController.REST_MEAL_URL;

public class MealRestControllerTest extends AbstractControllerTest {
    private static final String REST_MEAL_URL = MealRestController.REST_MEAL_URL + "/";

    @Autowired
    private MealService service;

    @Test
    void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_MEAL_URL + MEAL_1_ID)
                .with(userHttpBasic(USER_1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertMatch(readFromJsonMvcResult(result, Meal.class), MEAL_1));
    }
}
