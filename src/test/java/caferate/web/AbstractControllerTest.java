package caferate.web;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;

import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import workplace.repository.JpaUtil;
import workplace.service.MealService;
import workplace.service.RestaurantService;
import workplace.service.UserService;

import javax.annotation.PostConstruct;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringJUnitWebConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-mvc.xml",
        "classpath:spring/spring-db.xml"
})

@Transactional
abstract public class AbstractControllerTest {

    private static final CharacterEncodingFilter CHARACTER_ENCODING_FILTER = new CharacterEncodingFilter();

    static {
        CHARACTER_ENCODING_FILTER.setEncoding("UTF-8");
        CHARACTER_ENCODING_FILTER.setForceEncoding(true);
    }

    protected MockMvc mockMvc;

    @Autowired
    protected CacheManager cacheManager;

    @Autowired(required = false)
    protected JpaUtil jpaUtil;

    @Autowired
    protected UserService userService;

    @Autowired
    protected RestaurantService restaurantService;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @PostConstruct
    private void postConstruct() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilter(CHARACTER_ENCODING_FILTER)
                .apply(springSecurity())
                .build();
    }

    @BeforeEach
    void setUp() {
        cacheManager.getCache("users").clear();
        cacheManager.getCache("meals").clear();
        jpaUtil.clear2ndLevelHibernateCache();
    }

}
