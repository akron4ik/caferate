package caferate;

import org.springframework.test.web.servlet.ResultMatcher;
import workplace.model.Restaurant;
import workplace.model.Role;
import workplace.model.User;
import workplace.to.UserTo;
import workplace.web.json.JsonUtil;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static caferate.TestUtil.readFromJsonMvcResult;
import static caferate.TestUtil.readListFromJsonMvcResult;
import static org.assertj.core.api.Assertions.assertThat;

public class UserTestData {

    public static final int ADMIN_ID = 100000;
    public static final int USER_2_ID = 100001;
    public static final int USER_3_ID = 100002;
    public static final int USER_4_ID = 100003;

    public static final User ADMIN = new User(100000, "Admin", "admin@admin.ru", "admin", Role.ROlE_ADMIN);
    public static final User USER_2 = new User(100001, "Tom", "tom@tom.ru", "tom", Role.ROlE_USER);
    public static final User USER_3 = new User(100002, "Homer", "homer@homer.ru", "homer", Role.ROlE_USER);
    public static final User USER_4 = new User(100003, "Bart", "bart@bart.ru", "bart", Role.ROlE_USER);
    public static final User USER_5 = new User(100004, "Liza", "liza@liza.ru", "liza", Role.ROlE_USER);
    public static final User USER_6 = new User(100005, "Meggi", "meggi@meggi.ru", "meggi", Role.ROlE_USER);


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
