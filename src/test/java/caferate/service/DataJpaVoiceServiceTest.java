package caferate.service;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import workplace.service.VoiceService;

public class DataJpaVoiceServiceTest extends AbstractServiceTest {

    @Autowired
    VoiceService voiceService;

    @BeforeEach
    void setUp() throws Exception {
        cacheManager.getCache("voices").clear();
        jpaUtil.clear2ndLevelHibernateCache();
    }



}
