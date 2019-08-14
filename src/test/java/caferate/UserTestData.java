package caferate;

import workplace.model.Restaurant;
import workplace.model.Role;
import workplace.model.User;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTestData {
    public static final User USER_1 = new User(100000, "Admin", "admin@admin.ru", "admin", LocalDateTime.of(2019,8,8, 10,00), true, Collections.singleton(Role.ADMIN));
    public static final User USER_2 = new User(100001, "Tom", "tom@tom.ru", "tom", LocalDateTime.of(2019,8,9, 10,00), true, Collections.singleton(Role.USER));
    public static final User USER_3 = new User(100002, "Homer", "homer@homer.ru", "homer", LocalDateTime.of(2019,8,2, 10,00), true, Collections.singleton(Role.USER));
    public static final User USER_4 = new User(100003, "Bart", "bart@bart.ru", "bart", LocalDateTime.of(2019,8,1, 10,00), true, Collections.singleton(Role.USER));
    public static final User USER_5 = new User(100004, "Liza", "liza@liza.ru", "liza", LocalDateTime.of(2019,8,4, 10,00), true, Collections.singleton(Role.USER));
    public static final User USER_6 = new User(100005, "Meggi", "meggi@meggi.ru", "meggi", LocalDateTime.of(2019,8,8, 10,00), true, Collections.singleton(Role.USER));


    public static <T> void assertMatch(T actual, T expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static <T> void assertMatch (Iterable<T> actual, Iterable<T> expected) {
        assertThat(actual).isEqualTo(expected);
    }

}
