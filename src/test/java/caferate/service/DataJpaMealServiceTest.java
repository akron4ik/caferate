package caferate.service;

import caferate.MealTestData;
import caferate.RestaurantTestData;
import org.junit.jupiter.api.BeforeEach;
import workplace.model.Meal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import workplace.service.MealService;
import workplace.util.exception.NotFoundException;

import java.time.LocalDate;

import static caferate.MealTestData.*;
import static caferate.RestaurantTestData.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DataJpaMealServiceTest extends AbstractServiceTest {

    @Autowired
    MealService mealService;

    @BeforeEach
    void setUp() throws Exception {
        cacheManager.getCache("meals").clear();
        jpaUtil.clear2ndLevelHibernateCache();
    }

    @Test
    void delete() throws Exception {
        mealService.delete(MEAL_7_ID);
        MealTestData.assertMatch(mealService.getAllByRestaurantId(RESTAURANT_3_ID), MEAL_8, MEAL_9);
    }

    @Test
    void create() throws Exception {
        Meal newMeal = new Meal(null, "newMeal", LocalDate.of(2019,8,13), 222, RESTAURANT_3);
        Meal created = mealService.create(newMeal);
        newMeal.setId(created.getId());
        MealTestData.assertMatch(newMeal, created);
        assertMatch(mealService.getAllByRestaurantId(RESTAURANT_3_ID), newMeal, MEAL_7, MEAL_8, MEAL_9);
    }

    @Test
    void get() throws Exception {
        Meal actual = mealService.get(MEAL_1_ID);
        MealTestData.assertMatch(actual, MEAL_1);
    }

   @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                mealService.get(1));
    }

    @Test
    void update() throws Exception {
        Meal updated = new Meal(MEAL_1);
        updated.setName("Updated");
        updated.setPrice(111);
        mealService.update(new Meal(updated));
        MealTestData.assertMatch(mealService.get(MEAL_1_ID), updated);
    }


    @Test
    void getAllByRestaurantId() throws Exception {
        assertMatch(mealService.getAllByRestaurantId(RESTAURANT_3_ID),MEAL_7, MEAL_8, MEAL_9);
    }

    @Test
    void getMealsByDateAndRestaurantId() throws Exception {
        assertMatch(mealService.getMealsByDate(LocalDate.of(2019,8,10), RESTAURANT_3_ID),MEAL_7, MEAL_8, MEAL_9);

    }

    @Test
    void getWithRestaurant() throws Exception {
        Meal meal = mealService.getMealWithRestaurant(MEAL_1_ID);
        MealTestData.assertMatch(meal, MEAL_1);
        RestaurantTestData.assertMatch(meal.getRestaurant(), RESTAURANT_1);
    }

    @Test
    void getAll() throws Exception {
        MealTestData.assertMatch(mealService.getAll(), MEALS);
    }

}
