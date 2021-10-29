package ssau.kuznetsov.autotests;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.junit.Before;

public class DbUt extends PostgresqlContainer {

    private IDatabaseTester tester;

    @Before
    public void setUp() throws Exception {
        tester = new JdbcDatabaseTester(
                "org.postgresql.Driver",
                "jdbc:postgresql://localhost:5432/passport_db",
                "root",
                "qwerty"
        );
    }
}
