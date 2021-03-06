package caferate.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import workplace.model.Role;
import workplace.model.User;
import workplace.service.UserService;
import workplace.util.exception.NotFoundException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static caferate.UserTestData.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest extends AbstractServiceTest {

    @Autowired
    UserService userService;

    @BeforeEach
    void setUp() throws Exception {
        cacheManager.getCache("users").clear();
        jpaUtil.clear2ndLevelHibernateCache();
    }

    @Test
    void create() throws Exception{
        User newUser = new User(null, "Created", "created@created.ru", "created", new Date(), true, Collections.singleton(Role.ROLE_USER));
        User created = userService.create(newUser);
        newUser.setId(created.getId());
        assertMatch(newUser, created);
        assertMatch(userService.getAll(), ADMIN, USER_4, USER_20, newUser, USER_15, USER_8, USER_12, USER_10, USER_11, USER_3, USER_18, USER_5, USER_14, USER_7, USER_6, USER_17, USER_19, USER_9, USER_13, USER_2, USER_16);

    }

    @Test
    void duplicateMailCreate() throws Exception {
        assertThrows(DataAccessException.class, () ->
                userService.create(new User(null, "Duplicate", "admin@admin.ru", "newPass", new Date(), true, Collections.singleton(Role.ROLE_USER))));
    }

    @Test
    void get() throws Exception{
        User user = userService.get(USER_2_ID);
        assertMatch(user, USER_2);
    }

    @Test
    void delete() throws Exception{
        userService.delete(USER_2_ID);
        assertMatch(userService.getAll(), ADMIN, USER_4, USER_20, USER_15, USER_8, USER_12, USER_10, USER_11, USER_3, USER_18, USER_5, USER_14, USER_7, USER_6, USER_17, USER_19, USER_9, USER_13, USER_16);
    }

    @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                userService.get(1));
    }

    @Test
    void getByEmail() throws Exception {
        User user = userService.getByEmail("admin@admin.ru");
        assertMatch(user, ADMIN);
    }

    @Test
    void update() throws Exception {
        User updated = new User(USER_2);
        updated.setName("UpdatedName");
        updated.setEmail("update@update.ru");
        userService.update(new User(updated));
        assertMatch(userService.get(USER_2_ID), updated);
    }

    @Test
    void enable() {
        userService.enable(USER_2.getId(), false);
        assertFalse(userService.get(USER_2.getId()).isEnabled());
        userService.enable(USER_2.getId(), true);
        assertTrue(userService.get(USER_2.getId()).isEnabled());
    }

    @Test
    void getAll(){
        List<User> actual = userService.getAll();
        assertMatch(actual, ADMIN, USER_4, USER_20, USER_15, USER_8, USER_12, USER_10, USER_11, USER_3, USER_18, USER_5, USER_14, USER_7, USER_6, USER_17, USER_19, USER_9, USER_13, USER_2, USER_16);
    }
}
