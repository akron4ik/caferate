package caferate.service;

import caferate.UserTestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import workplace.model.Role;
import workplace.model.User;
import workplace.service.UserService;
import workplace.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;

import static caferate.UserTestData.*;
import static org.junit.jupiter.api.Assertions.*;

public class DataJpaUserServiceTest extends AbstractServiceTest {

    @Autowired
    UserService userService;

    @BeforeEach
    void setUp() throws Exception {
        cacheManager.getCache("users").clear();
        jpaUtil.clear2ndLevelHibernateCache();
    }

    @Test
    void create() throws Exception{
        User newUser = new User(null, "Created", "created@created.ru", "created", new Date(), true, Collections.singleton(Role.USER));
        User created = userService.create(newUser);
        newUser.setId(created.getId());
        assertMatch(newUser, created);
        assertMatch(userService.getAll(), newUser, USER_1, USER_2, USER_3, USER_4, USER_5, USER_6);

    }

    @Test
    void duplicateMailCreate() throws Exception {
        assertThrows(DataAccessException.class, () ->
                userService.create(new User(null, "Duplicate", "admin@admin.ru", "newPass", new Date(), true, Collections.singleton(Role.USER))));
    }

    @Test
    void get() throws Exception{
        User user = userService.get(USER_2_ID);
        UserTestData.assertMatch(user, USER_2);
    }

    @Test
    void delete() throws Exception{
        userService.delete(USER_2_ID);
        assertMatch(userService.getAll(), USER_1, USER_3, USER_4, USER_5, USER_6);
    }

    @Test
    void deletedNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                userService.delete(1));
    }

    @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                userService.get(1));
    }

    @Test
    void getByEmail() throws Exception {
        User user = userService.getByEmail("admin@admin.ru");
        assertMatch(user, USER_1);
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
}
