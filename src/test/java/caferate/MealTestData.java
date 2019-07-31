package caferate;

import workplace.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MealTestData {

    //public static final MEAL_ID

    public static final Meal MEAL_1 = new Meal(100011, "Цезарь", LocalDateTime.of(2015,06, 01, 10, 00), 350.5);
    public static final Meal MEAL_2 = new Meal(100018, "Цезарь", LocalDateTime.of(2015,06, 01, 10, 00), 350.5);
    public static final Meal MEAL_3 = new Meal(100019, "Цезарь", LocalDateTime.of(2015,06, 01, 10, 00), 350.5);
    public static final Meal MEAL_4 = new Meal(100020, "Цезарь", LocalDateTime.of(2015,06, 01, 10, 00), 350.5);
    public static final Meal MEAL_5 = new Meal(100021, "Цезарь", LocalDateTime.of(2015,06, 01, 10, 00), 350.5);
    public static final Meal MEAL_6 = new Meal(100017, "Цезарь", LocalDateTime.of(2015,06, 01, 10, 00), 350.5);
    public static final Meal MEAL_7 = new Meal(100017, "Цезарь", LocalDateTime.of(2015,06, 01, 10, 00), 350.5);
    public static final Meal MEAL_8 = new Meal(100017, "Цезарь", LocalDateTime.of(2015,06, 01, 10, 00), 350.5);
    public static final Meal MEAL_9 = new Meal(100017, "Цезарь", LocalDateTime.of(2015,06, 01, 10, 00), 350.5);
    public static final Meal MEAL_10 = new Meal(100017, "Цезарь", LocalDateTime.of(2015,06, 01, 10, 00), 350.5);
    public static final Meal MEAL_11 = new Meal(100017, "Цезарь", LocalDateTime.of(2015,06, 01, 10, 00), 350.5);
    public static final Meal MEAL_12 = new Meal(100017, "Цезарь", LocalDateTime.of(2015,06, 01, 10, 00), 350.5);




    public static <T> void assertMatch(T actual, T expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static <T> void assertMatch (Iterable<T> actual, Iterable<T> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("user").isEqualTo(expected);
    }

    /*public static ResultMatcher contentJson(Iterable<MealTo> expected) {
        return result -> assertMatch(readListFromJsonMvcResult(result, MealTo.class), expected);
    }

    public static ResultMatcher contentJson(Meal expected) {
        return result -> assertMatch(readFromJsonMvcResult(result, Meal.class), expected);
    }*/
}
