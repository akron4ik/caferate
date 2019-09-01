package caferate.web.meal;

import caferate.MealTestData;
import caferate.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import workplace.model.Meal;
import workplace.service.MealService;
import workplace.web.json.JsonUtil;
import workplace.web.meal.MealRestController;

import java.time.LocalDate;

import static caferate.MealTestData.*;
import static caferate.RestaurantTestData.*;
import static caferate.TestUtil.*;
import static caferate.UserTestData.ADMIN;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class MealRestControllerTest extends AbstractControllerTest {
    private static final String REST_MEAL_URL = MealRestController.REST_MEAL_URL + "/";

    @Autowired
    MealService mealService;

    @Test
    void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_MEAL_URL + MEAL_2_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> MealTestData.assertMatch(readFromJsonMvcResult(result, Meal.class), MEAL_2));
    }

    @Test
    void createWithLocation() throws Exception{
        Meal expected = new Meal(null, "New",  LocalDate.of(2015,06, 01), 200.4, RESTAURANT_3);
        ResultActions action = mockMvc.perform(MockMvcRequestBuilders.post(REST_MEAL_URL)
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected))).andDo(print())
                .andExpect(status().isCreated());

        Meal returned = readFromJson(action, Meal.class);
        expected.setId(returned.getId());

        MealTestData.assertMatch(returned, expected);
    }

    @Test
    void update() throws Exception {
        Meal updated = new Meal(MEAL_7);
        updated.setName("Update");
        updated.setPrice(666);

        mockMvc.perform(MockMvcRequestBuilders.put(REST_MEAL_URL + MEAL_7_ID)
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))).andDo(print())
                .andExpect(status().isNoContent());

        MealTestData.assertMatch(mealService.get(MEAL_7_ID), updated);
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_MEAL_URL + MEAL_1_ID)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isNoContent());

        MealTestData.assertMatch(mealService.getAll(),  MEAL_2, MEAL_3, MEAL_4, MEAL_5, MEAL_6, MEAL_7, MEAL_8, MEAL_9, MEAL_10, MEAL_11, MEAL_12, MEAL_13, MEAL_14, MEAL_15);

    }

    @Test
    void getAllByRestaurant() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_MEAL_URL + "all/" + RESTAURANT_3_ID )
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(MEAL_7, MEAL_8, MEAL_9));
    }

    @Test
    void getAllByRestaurantAndDate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_MEAL_URL + "all/" + RESTAURANT_1_ID)
                .param("localDate","2019-08-10")
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(MEAL_1, MEAL_2, MEAL_3));
    }

}
