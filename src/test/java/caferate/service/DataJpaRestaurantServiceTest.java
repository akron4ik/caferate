package caferate.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import workplace.model.Restaurant;
import workplace.service.RestaurantService;

import static caferate.MealTestData.assertMatch;
import static caferate.RestaurantTestData.RESTAURANT_1;
import static caferate.RestaurantTestData.RESTAURANT_1_ID;


@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})

public class DataJpaRestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    RestaurantService restaurantService;

    @BeforeEach
    void setUp() throws Exception {
        cacheManager.getCache("restaurants").clear();
        jpaUtil.clear2ndLevelHibernateCache();
    }

    @Test
    void get() throws Exception {
        Restaurant actual = restaurantService.get(RESTAURANT_1_ID);
        assertMatch(actual, RESTAURANT_1);
    }

}
