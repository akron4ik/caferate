package caferate;


import workplace.model.Voice;
import java.time.LocalDate;
import java.util.List;


import static caferate.RestaurantTestData.*;
import static caferate.UserTestData.*;
import static org.assertj.core.api.Assertions.assertThat;

public class VoiceTestData {
    public static final int VOICE_1_ID = 100023;
    public static final int VOICE_2_ID = 100024;

    public static final Voice VOICE_1 = new Voice(100023, LocalDate.of(2015, 6, 1), RESTAURANT_1, USER_2);
    public static final Voice VOICE_2 = new Voice(100024, LocalDate.of(2015, 7, 2), RESTAURANT_1, USER_3);
    public static final Voice VOICE_3 = new Voice(100025, LocalDate.of(2015, 8, 3), RESTAURANT_2, USER_4);
    public static final Voice VOICE_4 = new Voice(100026, LocalDate.of(2015, 9, 4), RESTAURANT_3, USER_2);
    public static final Voice VOICE_5 = new Voice(100027, LocalDate.of(2015, 10, 5), RESTAURANT_3, USER_2);


    public static <T> void assertMatch(T actual, T expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant", "user");
    }

    public static void assertMatch(Iterable<Voice> actual, Voice... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static <T> void assertMatch (Iterable<T> actual, Iterable<T> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant", "user").isEqualTo(expected);
    }
}
