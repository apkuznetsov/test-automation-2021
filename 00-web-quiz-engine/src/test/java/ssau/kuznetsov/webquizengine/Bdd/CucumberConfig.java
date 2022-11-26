package ssau.kuznetsov.webquizengine.Bdd;

import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// class to use Spring app context while running Cucumber
@CucumberContextConfiguration
public class CucumberConfig {

    private static final Logger LOG = LoggerFactory.getLogger(CucumberConfig.class);

    // need this method so Cucumber will recognize this class as glue and load Spring context config
    @Before
    public void setUp() {
        LOG.info("SPRING CONTEXT INITED FOR EXECUTING CUCUMBER TESTS");
    }
}
