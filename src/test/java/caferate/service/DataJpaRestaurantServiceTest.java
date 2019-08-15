package caferate.service;

import caferate.RestaurantTestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import workplace.model.Restaurant;
import workplace.service.RestaurantService;
import static caferate.RestaurantTestData.*;


public class DataJpaRestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    RestaurantService restaurantService;

    @BeforeEach
    void setUp() throws Exception {
        cacheManager.getCache("meals").clear();
        jpaUtil.clear2ndLevelHibernateCache();
    }

    @Test
    void getAll() throws Exception{
       assertMatch(restaurantService.getAll(), RESTAURANT_1, RESTAURANT_2,RESTAURANT_3, RESTAURANT_4, RESTAURANT_5);
    }

    @Test
    void get() throws Exception {
        Restaurant actual = restaurantService.get(RESTAURANT_1_ID);
        assertMatch(actual, RESTAURANT_1);
    }

}
