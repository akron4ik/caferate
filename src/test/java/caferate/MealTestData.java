package caferate;

import org.springframework.test.web.servlet.ResultMatcher;
import workplace.model.Meal;

import java.time.LocalDate;
import java.util.List;

import static caferate.RestaurantTestData.*;
import static caferate.TestUtil.readListFromJsonMvcResult;
import static org.assertj.core.api.Assertions.assertThat;

public class MealTestData {

    public static final int MEAL_1_ID = 100030;
    public static final int MEAL_2_ID = 100031;
    public static final int MEAL_7_ID = 100036;

    public static final Meal MEAL_1 = new Meal(MEAL_1_ID, "Цезарь", LocalDate.of(2019,8, 10), 350.5, RESTAURANT_1);
    public static final Meal MEAL_2 = new Meal(MEAL_2_ID, "Ризотто", LocalDate.of(2019,8, 10), 500.5, RESTAURANT_1);
    public static final Meal MEAL_3 = new Meal(MEAL_2_ID + 1, "Шашлык", LocalDate.of(2019,8, 10), 440.8, RESTAURANT_1);
    public static final Meal MEAL_4 = new Meal(MEAL_2_ID + 2, "Яичница", LocalDate.of(2019,8, 10), 30.5, RESTAURANT_2);
    public static final Meal MEAL_5 = new Meal(MEAL_2_ID + 3, "Фетучини", LocalDate.of(2019,8, 10), 600.3, RESTAURANT_2);
    public static final Meal MEAL_6 = new Meal(MEAL_2_ID + 4, "Греческий", LocalDate.of(2019,8, 10), 270.7, RESTAURANT_2);
    public static final Meal MEAL_7 = new Meal(MEAL_2_ID + 5, "Оливье", LocalDate.of(2019,8, 10), 200.4, RESTAURANT_3);
    public static final Meal MEAL_8 = new Meal(MEAL_2_ID + 6, "Селедка", LocalDate.of(2019,8, 10), 345, RESTAURANT_3);
    public static final Meal MEAL_9 = new Meal(MEAL_2_ID + 7, "Цыпленок табака", LocalDate.of(2019,8, 10), 570.8, RESTAURANT_3);
    public static final Meal MEAL_10 = new Meal(MEAL_2_ID + 8, "Омлет", LocalDate.of(2019,8, 10), 45.5, RESTAURANT_4);
    public static final Meal MEAL_11 = new Meal(MEAL_2_ID + 9, "Мясо по-французки", LocalDate.of(2019,8, 10), 770.3, RESTAURANT_4);
    public static final Meal MEAL_12 = new Meal(MEAL_2_ID + 10, "Каре ягненка", LocalDate.of(2019,8, 10), 885.5, RESTAURANT_4);
    public static final Meal MEAL_13 = new Meal(MEAL_2_ID + 11, "Борщ", LocalDate.of(2019,8, 10), 432.5, RESTAURANT_5);
    public static final Meal MEAL_14 = new Meal(MEAL_2_ID + 12, "Паэлья", LocalDate.of(2019,8, 10), 78.2, RESTAURANT_5);
    public static final Meal MEAL_15 = new Meal(MEAL_2_ID + 13, "Пицца", LocalDate.of(2019,8, 10), 150, RESTAURANT_5);

    public static final List<Meal> MEALS = List.of(MEAL_1, MEAL_2, MEAL_3, MEAL_4, MEAL_5, MEAL_6, MEAL_7, MEAL_8, MEAL_9, MEAL_10, MEAL_11, MEAL_12, MEAL_13, MEAL_14, MEAL_15);

    public static <T> void assertMatch(T actual, T expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant");
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static <T> void assertMatch (Iterable<T> actual, Iterable<T> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant").isEqualTo(expected);


    }

    public static ResultMatcher contentJson(Meal... expected) {
        return result -> assertMatch(readListFromJsonMvcResult(result, Meal.class), expected);
    }

}
