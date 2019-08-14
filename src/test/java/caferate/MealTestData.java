package caferate;

import workplace.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static caferate.RestaurantTestData.*;
import static org.assertj.core.api.Assertions.assertThat;

public class MealTestData {

    public static final int MEAL_1_ID = 100011;

    public static final Meal MEAL_1 = new Meal(100011, "Цезарь", LocalDate.of(2015,06, 01), 350.5, RESTAURANT_1);
    public static final Meal MEAL_2 = new Meal(100012, "Cпагетти", LocalDate.of(2015,06, 01), 500.5, RESTAURANT_1);
    public static final Meal MEAL_3 = new Meal(100013, "Шашлык", LocalDate.of(2015,06, 01), 440.8, RESTAURANT_1);
    public static final Meal MEAL_4 = new Meal(100014, "Яичница", LocalDate.of(2015,06, 01), 30.5, RESTAURANT_2);
    public static final Meal MEAL_5 = new Meal(100015, "Фетучини", LocalDate.of(2015,06, 01), 600.3, RESTAURANT_2);
    public static final Meal MEAL_6 = new Meal(100016, "Греческий", LocalDate.of(2015,06, 01), 270.7, RESTAURANT_2);
    public static final Meal MEAL_7 = new Meal(100017, "Оливье", LocalDate.of(2015,06, 01), 200.4, RESTAURANT_3);
    public static final Meal MEAL_8 = new Meal(100018, "Селедка", LocalDate.of(2015,06, 01), 345, RESTAURANT_3);
    public static final Meal MEAL_9 = new Meal(100019, "Цыпленок табака", LocalDate.of(2015,06, 01), 570.8, RESTAURANT_3);
    public static final Meal MEAL_10 = new Meal(100020, "Омлет", LocalDate.of(2015,06, 01), 45.5, RESTAURANT_4);
    public static final Meal MEAL_11 = new Meal(100021, "Мясо по-французски", LocalDate.of(2015,06, 01), 770.3, RESTAURANT_4);
    public static final Meal MEAL_12 = new Meal(100022, "Каре ягненка", LocalDate.of(2015,06, 01), 885.5, RESTAURANT_4);




    public static <T> void assertMatch(T actual, T expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant");
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static <T> void assertMatch (Iterable<T> actual, Iterable<T> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant").isEqualTo(expected);

    }

    /*public static ResultMatcher contentJson(Iterable<MealTo> expected) {
        return result -> assertMatch(readListFromJsonMvcResult(result, MealTo.class), expected);
    }

    public static ResultMatcher contentJson(Meal expected) {
        return result -> assertMatch(readFromJsonMvcResult(result, Meal.class), expected);
    }*/
}
