package ssau.kuznetsov.autotests.configs;

import io.cucumber.java.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;
import ssau.kuznetsov.autotests.App;

// class to use Spring app context while running Cucumber
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = App.class, loader = SpringBootContextLoader.class)
public class CucumberConfig {

    private static final Logger LOG = LoggerFactory.getLogger(CucumberConfig.class);

    // need this method so Cucumber will recognize this class as glue and load Spring context config
    @Before
    public void setUp() {
        LOG.info("SPRING CONTEXT INITED FOR EXECUTING CUCUMBER TESTS");
    }
}
