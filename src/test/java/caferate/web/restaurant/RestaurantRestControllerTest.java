package caferate.web.restaurant;

import caferate.RestaurantTestData;
import caferate.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import workplace.model.Restaurant;
import workplace.web.json.JsonUtil;
import workplace.web.restaurant.RestaurantRestController;
import static caferate.RestaurantTestData.*;
import static caferate.TestUtil.*;
import static caferate.UserTestData.ADMIN;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RestaurantRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = RestaurantRestController.REST_REST_URL + "/";


    @Test
    void update() throws Exception{
        Restaurant updated = RESTAURANT_1;
        updated.setName("Updated");

        mockMvc.perform(MockMvcRequestBuilders.put(REST_URL + RESTAURANT_1_ID)
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        RestaurantTestData.assertMatch(restaurantService.get(RESTAURANT_1_ID), updated);
    }

    @Test
    void get() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + RESTAURANT_3_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(RESTAURANT_3));
    }

    @Test
    void delete() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_URL + RESTAURANT_3_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk());
        RestaurantTestData.assertMatch(restaurantService.getAll(), RESTAURANT_1, RESTAURANT_2, RESTAURANT_4, RESTAURANT_5, RESTAURANT_6, RESTAURANT_7, RESTAURANT_8, RESTAURANT_9, RESTAURANT_10);
    }

    @Test
    void getAll() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL+ "all")
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(RESTAURANT_1, RESTAURANT_2, RESTAURANT_3, RESTAURANT_4, RESTAURANT_5, RESTAURANT_6, RESTAURANT_7, RESTAURANT_8, RESTAURANT_9, RESTAURANT_10));
    }

    @Test
    void createWithLocation() throws Exception {
        Restaurant expected = new Restaurant(null, "New");
        ResultActions action = mockMvc.perform(MockMvcRequestBuilders.post(REST_URL)
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated());

        Restaurant returned = readFromJson(action, Restaurant.class);
        expected.setId(returned.getId());

        RestaurantTestData.assertMatch(returned, expected);
    }

    @Test
    void getAllByDate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL+ "/all/date")
                .param("localDate", "2019-08-10")
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(RESTAURANT_1, RESTAURANT_2, RESTAURANT_3, RESTAURANT_4, RESTAURANT_5));
    }

    @Test
    void getRestaurantRating() throws Exception {
        ResultActions action = mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + "/rating/bydate/" + RESTAURANT_1_ID)
                .param("localDate", "2019-08-10")
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        Integer rating = readFromJson(action, Integer.class);
        System.out.println(rating);
    }

    @Test
    void getRatingBetweenDates() throws Exception {
        ResultActions action = mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + "/rating/" + RESTAURANT_1_ID)
                .param("startDate", "2019-08-10")
                .param("endDate", "2019-08-20")
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        Integer rating = readFromJson(action, Integer.class);
        System.out.println(rating);
    }
}
