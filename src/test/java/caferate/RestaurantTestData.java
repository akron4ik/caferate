package caferate;

import org.springframework.test.web.servlet.ResultMatcher;
import workplace.model.Restaurant;

import java.util.List;
import static caferate.TestUtil.readListFromJsonMvcResult;
import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantTestData {
    public static final int RESTAURANT_1_ID = 100020;
    public static final int RESTAURANT_2_ID = 100021;
    public static final int RESTAURANT_3_ID = 100022;
    public static final Restaurant RESTAURANT_1 = new Restaurant(RESTAURANT_1_ID, "Капри");
    public static final Restaurant RESTAURANT_2 = new Restaurant(RESTAURANT_2_ID, "Маркет");
    public static final Restaurant RESTAURANT_3 = new Restaurant(RESTAURANT_3_ID, "Шинок");
    public static final Restaurant RESTAURANT_4 = new Restaurant(RESTAURANT_3_ID + 1, "Ля маре");
    public static final Restaurant RESTAURANT_5 = new Restaurant(RESTAURANT_3_ID + 2, "Боно");
    public static final Restaurant RESTAURANT_6 = new Restaurant(RESTAURANT_3_ID + 3, "Ермак");
    public static final Restaurant RESTAURANT_7 = new Restaurant(RESTAURANT_3_ID + 4, "Турандот");
    public static final Restaurant RESTAURANT_8 = new Restaurant(RESTAURANT_3_ID + 5, "Пушкин");
    public static final Restaurant RESTAURANT_9 = new Restaurant(RESTAURANT_3_ID + 6, "Уголек");
    public static final Restaurant RESTAURANT_10 = new Restaurant(RESTAURANT_3_ID + 7, "Обломов");

    public static final List<Restaurant> RESTAURANTS = List.of(RESTAURANT_1, RESTAURANT_2, RESTAURANT_3, RESTAURANT_4, RESTAURANT_5, RESTAURANT_6, RESTAURANT_7, RESTAURANT_8, RESTAURANT_9, RESTAURANT_10);


    public static <T> void assertMatch(T actual, T expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "meals");
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static <T> void assertMatch (Iterable<T> actual, Iterable<T> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("meals").isEqualTo(expected);
    }

    public static ResultMatcher contentJson(Restaurant... expected) {
        return result -> assertMatch(readListFromJsonMvcResult(result, Restaurant.class), List.of(expected));
    }

}
