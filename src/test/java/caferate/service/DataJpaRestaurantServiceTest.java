package caferate.service;

import caferate.ActiveDbProfileResolver;
import caferate.TimingExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import workplace.model.Meal;
import workplace.model.Restaurant;
import workplace.repository.datajpa.DataJpaRestaurantRepository;

import static caferate.MealTestData.assertMatch;
import static caferate.RestaurantTestData.RESTAURANT_1;
import static workplace.Profiles.DATAJPA;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})

@ActiveProfiles(DATAJPA)
public class DataJpaRestaurantServiceTest extends AbstractServiceTest {
    @Autowired
    DataJpaRestaurantRepository dataJpaRestaurantRepository;

    @Test
    void get() throws Exception {
        Restaurant actual = dataJpaRestaurantRepository.get(100006);
        assertMatch(actual, RESTAURANT_1);
    }
}
