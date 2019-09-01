package caferate;


import org.springframework.test.web.servlet.ResultMatcher;
import workplace.model.Voice;
import workplace.to.VoiceTo;
import java.time.LocalDate;
import java.util.List;
import static caferate.RestaurantTestData.*;
import static caferate.TestUtil.readFromJsonMvcResult;
import static caferate.TestUtil.readListFromJsonMvcResult;
import static caferate.UserTestData.*;
import static org.assertj.core.api.Assertions.assertThat;

public class VoiceTestData {
    public static final int VOICE_1_ID = 100045;
    public static final int VOICE_2_ID = 100046;
    public static final int VOICE_3_ID = 100047;

    public static final Voice VOICE_1 = new Voice(VOICE_1_ID, LocalDate.of(2019, 8, 10), RESTAURANT_1, USER_2);
    public static final Voice VOICE_2 = new Voice(VOICE_2_ID, LocalDate.of(2019, 8, 10), RESTAURANT_1, USER_3);
    public static final Voice VOICE_3 = new Voice(VOICE_3_ID, LocalDate.of(2019, 8, 10), RESTAURANT_1, USER_4);
    public static final Voice VOICE_4 = new Voice(VOICE_3_ID + 1, LocalDate.of(2019, 8, 10), RESTAURANT_1, USER_5);
    public static final Voice VOICE_5 = new Voice(VOICE_3_ID + 2, LocalDate.of(2019, 8, 10), RESTAURANT_1, USER_6);
    public static final Voice VOICE_6 = new Voice(VOICE_3_ID + 3, LocalDate.of(2019, 8, 10), RESTAURANT_1, USER_7);
    public static final Voice VOICE_7 = new Voice(VOICE_3_ID + 4, LocalDate.of(2019, 8, 10), RESTAURANT_1, USER_8);
    public static final Voice VOICE_8 = new Voice(VOICE_3_ID + 5, LocalDate.of(2019, 8, 10), RESTAURANT_1, USER_9);
    public static final Voice VOICE_9 = new Voice(VOICE_3_ID + 6, LocalDate.of(2019, 8, 10), RESTAURANT_1, USER_10);
    public static final Voice VOICE_10 = new Voice(VOICE_3_ID + 7, LocalDate.of(2019, 8, 10), RESTAURANT_1, USER_11);
    public static final Voice VOICE_11 = new Voice(VOICE_3_ID + 8, LocalDate.of(2019, 8, 10), RESTAURANT_2, USER_12);
    public static final Voice VOICE_12 = new Voice(VOICE_3_ID + 9, LocalDate.of(2019, 8, 10), RESTAURANT_2, USER_13);
    public static final Voice VOICE_13 = new Voice(VOICE_3_ID + 10, LocalDate.of(2019, 8, 10), RESTAURANT_2, USER_14);
    public static final Voice VOICE_14 = new Voice(VOICE_3_ID + 11, LocalDate.of(2019, 8, 10), RESTAURANT_3, USER_15);
    public static final Voice VOICE_15 = new Voice(VOICE_3_ID + 12, LocalDate.of(2019, 8, 10), RESTAURANT_3, USER_16);
    public static final Voice VOICE_16 = new Voice(VOICE_3_ID + 13, LocalDate.of(2019, 8, 10), RESTAURANT_4, USER_17);
    public static final Voice VOICE_17 = new Voice(VOICE_3_ID + 14, LocalDate.of(2019, 8, 10), RESTAURANT_4, USER_18);
    public static final Voice VOICE_18 = new Voice(VOICE_3_ID + 15, LocalDate.of(2019, 8, 10), RESTAURANT_5, USER_19);
    public static final Voice VOICE_19 = new Voice(VOICE_3_ID + 16, LocalDate.of(2019, 8, 10), RESTAURANT_6, USER_20);
    public static final Voice VOICE_20 = new Voice(VOICE_3_ID + 17, LocalDate.of(2019, 8, 20), RESTAURANT_1, USER_2);
    public static final Voice VOICE_21 = new Voice(VOICE_3_ID + 18, LocalDate.of(2019, 8, 20), RESTAURANT_1, USER_3);
    public static final Voice VOICE_22 = new Voice(VOICE_3_ID + 19, LocalDate.of(2019, 8, 20), RESTAURANT_1, USER_4);
    public static final Voice VOICE_23 = new Voice(VOICE_3_ID + 20, LocalDate.of(2019, 8, 20), RESTAURANT_1, USER_5);
    public static final Voice VOICE_24 = new Voice(VOICE_3_ID + 21, LocalDate.of(2019, 8, 20), RESTAURANT_2, USER_6);
    public static final Voice VOICE_25 = new Voice(VOICE_3_ID + 22, LocalDate.of(2019, 8, 20), RESTAURANT_2, USER_7);
    public static final Voice VOICE_26 = new Voice(VOICE_3_ID + 23, LocalDate.of(2019, 8, 20), RESTAURANT_2, USER_8);
    public static final Voice VOICE_27 = new Voice(VOICE_3_ID + 24, LocalDate.of(2019, 8, 20), RESTAURANT_3, USER_9);
    public static final Voice VOICE_28 = new Voice(VOICE_3_ID + 25, LocalDate.of(2019, 8, 20), RESTAURANT_3, USER_10);
    public static final Voice VOICE_29 = new Voice(VOICE_3_ID + 26, LocalDate.of(2019, 8, 20), RESTAURANT_3, USER_11);

    public static final List<Voice> VOICES = List.of(VOICE_1, VOICE_2, VOICE_3, VOICE_4, VOICE_5, VOICE_6, VOICE_7, VOICE_8, VOICE_9, VOICE_10, VOICE_11, VOICE_12, VOICE_13, VOICE_14, VOICE_15, VOICE_16, VOICE_17, VOICE_18, VOICE_19, VOICE_20, VOICE_21, VOICE_22, VOICE_23, VOICE_24, VOICE_25, VOICE_26, VOICE_27, VOICE_28, VOICE_29);


    public static <T> void assertMatch(T actual, T expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant", "user");
    }

    public static  void assertMatch(VoiceTo actual, VoiceTo expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Voice> actual, Voice... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static <T> void assertMatch (Iterable<T> actual, Iterable<T> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant", "user").isEqualTo(expected);
    }

    public static ResultMatcher contentJson(Voice... expected) {
        return result -> assertMatch(readListFromJsonMvcResult(result, Voice.class), expected);
    }

    public static ResultMatcher contentJson(Voice expected) {
        return result -> assertMatch(readFromJsonMvcResult(result, Voice.class), expected);
    }
}
