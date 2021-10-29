package ssau.kuznetsov.autotests;

import org.flywaydb.test.FlywayTestExecutionListener;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.testcontainers.containers.PostgreSQLContainer;

/* with @ActiveProfiles we define that
 * the application is started with the profile "it";
 * if needed, we can use this profile to activate special settings in our code */
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("it")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        FlywayTestExecutionListener.class})
public abstract class PostgresqlContainer {

    private static final PostgreSQLContainer POSTGRE_SQL_CONTAINER;

    /* in the static section, the POSTGRE_SQL_CONTAINER is configured and started
     *
     * with the parameter withReuse(true),
     * the Docker container is not automatically terminated at the end of the tests,
     * but available for reusage without a longer waiting period;
     * occasionally, the container must be terminated manually to fully reset the database
     *
     * to enable reuse, the file /<usersdir>/.testcontainers.properties must be extended
     * by the entry testcontainers.reuse.enable=true */
    static {
        POSTGRE_SQL_CONTAINER = (PostgreSQLContainer) (new PostgreSQLContainer("postgres:10.18")
                .withUsername("testcontainersroot")
                .withPassword("testcontainersqwerty")
                .withReuse(true));
        POSTGRE_SQL_CONTAINER.start();
    }

    /* in the section after @DynamicPropertySource we set the properties
     * to link our application context to the Docker containers database
     *
     * depending on how we initialize our database schema – for example with Flyway or Liquibase – this
     * will be automatically applied during context startup.*/
    @DynamicPropertySource
    public static void setDatasourceProperties(final DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRE_SQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.password", POSTGRE_SQL_CONTAINER::getPassword);
        registry.add("spring.datasource.username", POSTGRE_SQL_CONTAINER::getUsername);
    }
}
