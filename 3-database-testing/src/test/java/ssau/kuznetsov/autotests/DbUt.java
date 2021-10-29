package ssau.kuznetsov.autotests;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.ITable;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.jupiter.api.Test;

public class DbUt extends PostgresqlContainer {

    private final IDatabaseTester tester;

    public DbUt() throws ClassNotFoundException {
        tester = new JdbcDatabaseTester(
                POSTGRE_SQL_CONTAINER.getDriverClassName(),
                POSTGRE_SQL_CONTAINER.getJdbcUrl(),
                POSTGRE_SQL_CONTAINER.getUsername(),
                POSTGRE_SQL_CONTAINER.getPassword()
        );
    }

    @Test
    @FlywayTest
    public void sample() throws Exception {
        ITable expectedTable = tester.getConnection().createDataSet().getTable("passport");
        ITable actualTable = tester.getConnection().createDataSet().getTable("passport");

        Assertion.assertEquals(expectedTable, actualTable);
    }
}
