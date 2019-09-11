package caferate;

import org.springframework.test.web.servlet.ResultMatcher;
import workplace.model.Role;
import workplace.model.User;
import workplace.to.UserTo;
import workplace.web.json.JsonUtil;
import java.util.List;

import static caferate.TestUtil.readFromJsonMvcResult;
import static caferate.TestUtil.readListFromJsonMvcResult;
import static org.assertj.core.api.Assertions.assertThat;

public class UserTestData {

    public static final int ADMIN_ID = 100000;
    public static final int USER_2_ID = 100001;
    public static final int USER_3_ID = 100002;
    public static final int USER_4_ID = 100003;

    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@admin.ru", "admin", Role.ROLE_ADMIN);
    public static final User USER_2 = new User(USER_2_ID, "Tom", "tom@tom.ru", "tom", Role.ROLE_USER);
    public static final User USER_3 = new User(USER_3_ID, "Homer", "homer@homer.ru", "homer", Role.ROLE_USER);
    public static final User USER_4 = new User(USER_4_ID, "Bart", "bart@bart.ru", "bart", Role.ROLE_USER);
    public static final User USER_5 = new User(USER_4_ID + 1, "Liza", "liza@liza.ru", "liza", Role.ROLE_USER);
    public static final User USER_6 = new User(USER_4_ID + 2, "Meggi", "meggi@meggi.ru", "meggi", Role.ROLE_USER);
    public static final User USER_7 = new User(USER_4_ID + 3, "Marge", "marge@marge.ru", "marge", Role.ROLE_USER);
    public static final User USER_8 = new User(USER_4_ID + 4, "Felix", "felix@felix.ru", "felix", Role.ROLE_USER);
    public static final User USER_9 = new User(USER_4_ID + 5, "Peter", "peter@peter.ru", "peter", Role.ROLE_USER);
    public static final User USER_10 = new User(USER_4_ID + 6, "Gregori", "gregori@gregori.ru", "gregori", Role.ROLE_USER);
    public static final User USER_11 = new User(USER_4_ID + 7, "Harry", "harry@harry.ru", "harry", Role.ROLE_USER);
    public static final User USER_12 = new User(USER_4_ID + 8, "Germiona", "germiona@germiona.ru", "germiona", Role.ROLE_USER);
    public static final User USER_13 = new User(USER_4_ID + 9, "Ron", "ron@ron.ru", "ron", Role.ROLE_USER);
    public static final User USER_14 = new User(USER_4_ID + 10, "Malfoi", "malfoi@malfoi.ru", "malfoi", Role.ROLE_USER);
    public static final User USER_15 = new User(USER_4_ID + 11, "Dambldor", "dambldor@dambldor.ru", "dambldor", Role.ROLE_USER);
    public static final User USER_16 = new User(USER_4_ID + 12, "Volandemort", "volandemort@volandemort.ru", "volandemort", Role.ROLE_USER);
    public static final User USER_17 = new User(USER_4_ID + 13, "Nevil", "nevil@nevil.ru", "nevil", Role.ROLE_USER);
    public static final User USER_18 = new User(USER_4_ID + 14, "Jameson", "jameson@jameson.ru", "jameson", Role.ROLE_USER);
    public static final User USER_19 = new User(USER_4_ID + 15, "Patric", "patric@patric.ru", "patric", Role.ROLE_USER);
    public static final User USER_20 = new User(USER_4_ID + 16, "Batman", "batman@batman.ru", "batman", Role.ROLE_USER);

    public static <T> void assertMatch(T actual, T expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "password");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static <T> void assertMatch (Iterable<T> actual, Iterable<T> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "password").isEqualTo(expected);
    }

    public static ResultMatcher contentJson(User... expected) {
        return result -> assertMatch(readListFromJsonMvcResult(result, User.class), List.of(expected));
    }

    public static ResultMatcher contentJson(User expected) {
        return result -> assertMatch(readFromJsonMvcResult(result, User.class), expected);
    }

    public static String jsonWithPassword(UserTo user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }

}
