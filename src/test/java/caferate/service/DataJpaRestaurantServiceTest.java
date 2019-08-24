package caferate.service;

import caferate.MealTestData;
import caferate.RestaurantTestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import workplace.model.Restaurant;
import workplace.service.RestaurantService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static caferate.MealTestData.*;
import static caferate.RestaurantTestData.*;
import static org.assertj.core.api.Assertions.assertThat;


public class DataJpaRestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    RestaurantService restaurantService;

    @BeforeEach
    void setUp() throws Exception {
        cacheManager.getCache("meals").clear();
        jpaUtil.clear2ndLevelHibernateCache();
    }

    @Test
    void getAll() throws Exception{
       RestaurantTestData.assertMatch(restaurantService.getAll(), RESTAURANT_1, RESTAURANT_2, RESTAURANT_3, RESTAURANT_4, RESTAURANT_5);
    }

    @Test
    void get() throws Exception {
        Restaurant actual = restaurantService.get(RESTAURANT_1_ID);
        RestaurantTestData.assertMatch(actual, RESTAURANT_1);
    }

    @Test
    void create() throws Exception {
        Restaurant newRestaurant = new Restaurant(null, "newRestaurant");
        restaurantService.create(newRestaurant);
        RestaurantTestData.assertMatch(restaurantService.getAll(), RESTAURANT_1, RESTAURANT_2, RESTAURANT_3, RESTAURANT_4, RESTAURANT_5, newRestaurant);
    }

    @Test
    void update() throws Exception {
        Restaurant updated = new Restaurant(RESTAURANT_1);
        updated.setName("UpdateRest");
        restaurantService.update(updated);
        RestaurantTestData.assertMatch(restaurantService.get(RESTAURANT_1_ID), updated);
    }

    @Test
    void delete() throws Exception{
        restaurantService.delete(RESTAURANT_1_ID);
        RestaurantTestData.assertMatch(restaurantService.getAll(), RESTAURANT_2, RESTAURANT_3, RESTAURANT_4, RESTAURANT_5 );
    }

    @Test
    void getRestaurantWithMeals() throws Exception{
        Restaurant restaurant = restaurantService.getWithMeal(RESTAURANT_3_ID);
        RestaurantTestData.assertMatch(restaurant, RESTAURANT_3);
        MealTestData.assertMatch(restaurant.getMeals(), MEAL_7, MEAL_8, MEAL_9);
    }

    /*@Test
    void getAllRestaurantsByMealDate() throws Exception{
        List<Restaurant> restaurants = restaurantService.getRestaurantsByDate(LocalDate.of(2015,6,3));
        RestaurantTestData.assertMatch(restaurants, RESTAURANT_3, RESTAURANT_4);

    }*/

}
