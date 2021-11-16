package ssau.kuznetsov.autotests;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.SortedTable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.io.FileInputStream;

public class DbUt extends PostgresqlContainer {

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

    @Test
    @FlywayTest
    public void citizen_11_deleted_with_his_passports() throws Exception {
        String testUrl = "/api/passport/citizen/11";
        final HttpEntity<String> request = new HttpEntity<>(null, new HttpHeaders());
        restTemplate.exchange(
                testUrl, HttpMethod.GET, request, new ParameterizedTypeReference<>() {
                });

        IDataSet db = tester.getConnection().createDataSet();

        ITable xml_citizen = new SortedTable(new FlatXmlDataSetBuilder().build(
                        new FileInputStream("dataset-citizen-deleted-citizen-11.xml"))
                .getTable("citizen"));
        ITable db_citizen = new SortedTable(db.getTable("citizen"));

        ITable xml_passports = new SortedTable(new FlatXmlDataSetBuilder().build(
                        new FileInputStream("dataset-passport-deleted-citizen-11.xml"))
                .getTable("passport"));
        ITable db_passports = new SortedTable(db.getTable("passport"));

        ITable xml_foreign_passports = new SortedTable(new FlatXmlDataSetBuilder().build(
                        new FileInputStream("dataset-foreign-passport-deleted-citizen-11.xml"))
                .getTable("foreign_passport"));
        ITable db_foreign_passports = new SortedTable(db.getTable("foreign_passport"));

        Assertion.assertEquals(xml_citizen, db_citizen);
        Assertion.assertEquals(xml_passports, db_passports);
        Assertion.assertEquals(xml_foreign_passports, db_foreign_passports);
    }

    @Test
    @FlywayTest
    public void is_valid_password_view_there_when_call_create_it_procedure() throws Exception {
        passRep.callCreateValidPassportView();

        ITable xml = new SortedTable(
                new FlatXmlDataSetBuilder().build(new FileInputStream("dataset-valid-passport-view.xml"))
                        .getTable("valid_passport_view"));
        ITable db = new SortedTable(
                tester.getConnection()
                        .createQueryTable("valid_passport_view", "SELECT * from valid_passport_view"));

        Assertion.assertEquals(xml, db);
    }
}
