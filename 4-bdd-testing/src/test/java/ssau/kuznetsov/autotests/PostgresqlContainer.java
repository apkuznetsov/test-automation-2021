package ssau.kuznetsov.autotests;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.flywaydb.test.FlywayTestExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import ssau.kuznetsov.autotests.repository.CitizenRepository;
import ssau.kuznetsov.autotests.repository.PassportRepository;

@ActiveProfiles("test")
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = PostgresqlContainer.Initializer.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        FlywayTestExecutionListener.class})
public class PostgresqlContainer {

    public static PostgreSQLContainer<?> postgresqlContainer;

    public static IDatabaseTester tester = null;

    static {
        try {
            postgresqlContainer = new PostgreSQLContainer<>("postgres:10.18")
                    .withUsername("testcontainersroot")
                    .withPassword("testcontainersqwerty");
            postgresqlContainer.start();

            tester = new JdbcDatabaseTester(
                    postgresqlContainer.getDriverClassName(),
                    postgresqlContainer.getJdbcUrl(),
                    postgresqlContainer.getUsername(),
                    postgresqlContainer.getPassword()
            );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    public TestRestTemplate restTemplate;
    @Autowired
    public CitizenRepository citRep;
    @Autowired
    public PassportRepository passRep;

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues values = TestPropertyValues.of(
                    "spring.datasource.url=" + postgresqlContainer.getJdbcUrl(),
                    "spring.datasource.password=" + postgresqlContainer.getPassword(),
                    "spring.datasource.username=" + postgresqlContainer.getUsername()
            );
            values.applyTo(configurableApplicationContext);
        }
    }
}
