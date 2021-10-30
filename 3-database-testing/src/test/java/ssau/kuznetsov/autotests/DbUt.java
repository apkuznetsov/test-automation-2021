package ssau.kuznetsov.autotests;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.SortedTable;
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
        ITable xml = new SortedTable(
                new FlatXmlDataSetBuilder().build(new FileInputStream("dataset-citizen.xml"))
                        .getTable("citizen"));
        ITable db = new SortedTable(
                tester.getConnection().createDataSet()
                        .getTable("citizen"));

        Assertion.assertEquals(xml, db);
    }

    @Test
    @FlywayTest
    public void is_passport_table_there() throws Exception {
        ITable xml = new SortedTable(
                new FlatXmlDataSetBuilder().build(new FileInputStream("dataset-passport.xml"))
                        .getTable("passport"));
        ITable db = new SortedTable(
                tester.getConnection().createDataSet()
                        .getTable("passport"));

        Assertion.assertEquals(xml, db);
    }

    @Test
    @FlywayTest
    public void is_foreign_passport_table_there() throws Exception {
        ITable xml = new SortedTable(
                new FlatXmlDataSetBuilder().build(new FileInputStream("dataset-foreign-passport.xml"))
                        .getTable("foreign_passport"));
        ITable db = new SortedTable(
                tester.getConnection().createDataSet()
                        .getTable("foreign_passport"));

        Assertion.assertEquals(xml, db);
    }
}
