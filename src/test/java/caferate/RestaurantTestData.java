package caferate;

import org.springframework.test.web.servlet.ResultMatcher;
import workplace.model.Restaurant;
import workplace.to.RestaurantTo;

import java.time.LocalDateTime;
import java.util.List;

import static caferate.MealTestData.*;
import static caferate.TestUtil.readFromJsonMvcResult;
import static caferate.TestUtil.readListFromJsonMvcResult;
import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantTestData {
    public static final int RESTAURANT_1_ID = 100006;
    public static final int RESTAURANT_2_ID = 100007;
    public static final int RESTAURANT_3_ID = 100008;
    public static final Restaurant RESTAURANT_1 = new Restaurant(100006, "Капри");
    public static final Restaurant RESTAURANT_2 = new Restaurant(100007, "Ле Дюк");
    public static final Restaurant RESTAURANT_3 = new Restaurant(100008, "Шиннок");
    public static final Restaurant RESTAURANT_4 = new Restaurant(100009, "Шоколадница");
    public static final Restaurant RESTAURANT_5 = new Restaurant(100010, "МакДак");


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

    public static ResultMatcher contentJson(RestaurantTo expected) {
        return result -> assertMatch(readFromJsonMvcResult(result, RestaurantTo.class), expected);
    }

}
