package caferate.service;

import org.junit.jupiter.api.BeforeEach;
import workplace.model.Meal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import workplace.service.MealService;
import workplace.util.exception.NotFoundException;

import java.time.LocalDate;

import static caferate.MealTestData.*;
import static caferate.RestaurantTestData.RESTAURANT_1;
import static caferate.RestaurantTestData.RESTAURANT_1_ID;
import static caferate.UserTestData.USER_2;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
        mealService.delete(MEAL_1_ID);
        assertMatch(mealService.getAll(RESTAURANT_1_ID), MEAL_2, MEAL_3);
    }

    @Test
    void create() throws Exception {
        Meal newMeal = new Meal(null, "newMeal", LocalDate.of(2019,8,13), 222, RESTAURANT_1);
        Meal created = mealService.create(newMeal);
        newMeal.setId(created.getId());
        assertMatch(newMeal, created);
        assertMatch(mealService.getAll(RESTAURANT_1_ID),  MEAL_1, MEAL_2, MEAL_3, newMeal);
    }

    @Test
    void get() throws Exception {
        Meal actual = mealService.get(100011);
        assertMatch(actual, MEAL_1);
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
        assertMatch(mealService.get(100011), updated);
    }


    /*@Test
    void getAll() throws Exception {
        assertMatch(service.getAll(USER_ID), MEALS);
    }
*/
    /*@Test
    void getBetween() throws Exception {
        assertMatch(service.getBetweenDates(
                LocalDate.of(2015, Month.MAY, 30),
                LocalDate.of(2015, Month.MAY, 30), USER_ID), MEAL3, MEAL2, MEAL1);
    }*/

}
