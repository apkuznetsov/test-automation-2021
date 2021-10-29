package ssau.kuznetsov.autotests;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;

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
    public void is_citizen_table_there() throws Exception {
        ITable expectedTable = new FlatXmlDataSetBuilder().build(
                new FileInputStream("dataset-citizen.xml")).getTable("citizen");
        ITable actualTable = tester.getConnection().createDataSet().getTable("citizen");

        Assertion.assertEquals(expectedTable, actualTable);
    }
}
