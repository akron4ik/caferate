package caferate.web.restaurant;

import caferate.MealTestData;
import caferate.RestaurantTestData;
import caferate.web.AbstractControllerTest;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import workplace.model.Restaurant;
import workplace.util.RestaurantUtil;
import workplace.web.json.JsonUtil;
import workplace.web.restaurant.RestaurantRestController;


import static caferate.RestaurantTestData.*;
import static caferate.TestUtil.*;

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
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        RestaurantTestData.assertMatch(restaurantService.get(RESTAURANT_1_ID), updated);
    }

    @Test
    void get() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + RESTAURANT_3_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(RESTAURANT_3));
    }

    @Test
    void delete() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_URL + RESTAURANT_1_ID))
                .andExpect(status().isOk());
        RestaurantTestData.assertMatch(restaurantService.getAll(), RESTAURANT_2, RESTAURANT_3, RESTAURANT_4, RESTAURANT_5);
    }

    @Test
    void getAll() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL+ "all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(RESTAURANT_1, RESTAURANT_2, RESTAURANT_3, RESTAURANT_4, RESTAURANT_5));

    }

    @Test
    void createWithLocation() throws Exception {
        Restaurant expected = new Restaurant(null, "New");
        ResultActions action = mockMvc.perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated());

        Restaurant returned = readFromJson(action, Restaurant.class);
        expected.setId(returned.getId());

        RestaurantTestData.assertMatch(returned, expected);
    }

    @Test
    void getAllByDate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL+ "/all/date").param("localDate", "2015-06-01"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(RESTAURANT_1,  RESTAURANT_3, RESTAURANT_4));
    }


}
