package ssau.kuznetsov.autotests.Bdd;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ssau.kuznetsov.autotests.Bdd.helpers.AdminHttpClient;
import ssau.kuznetsov.autotests.Bdd.helpers.UserHttpClient;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BddSteps {

    private final AdminHttpClient admin = new AdminHttpClient();
    private final UserHttpClient user = new UserHttpClient();

    @Before
    public void resetDatabase() {
        admin.truncateDatabase();
    }

    @Given("^в базе данных есть паспорт с серийным номером (\\d+)$")
    public void there_is_passport_with_such_serial_number(long serialNumber) {
        admin.createTestPassportWithSuchSerialNumber(serialNumber);
    }

    @Given("^в базе данных есть паспорты с фамилией и именем (.+) (.+)$")
    public void there_is_passports_with_such_surname_and_name(String surname, String name) {
        admin.createTestPassportsWithSuchSurnameAndName(surname, name);
    }

    @When("^пользователь посылает запрос GET /api/passport/ (\\d+)$")
    public void user_gets_passport_by_serial_number(long serialNumber) {
        user.getPassportBySerialNumber(serialNumber);
    }

    @When("^пользователь посылает запрос GET /api/passport/surname/ (.+) /name/ (.+)$")
    public void user_gets_passports_by_surname_and_name(String surname, String name) {
        user.getPassportsBySurnameAndName(surname, name);
    }

    @Then("^получает код состояния (\\d+)$")
    public void user_got_status_code_of(int expectedStatusCode) {
        int actualStatusCode = user.latestResponse().getStatusCode().value();
        assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Then("^получает паспорт с серийным номером (\\d+)$")
    public void user_got_status_code_of(long expectedSerialNumber) {
        LinkedHashMap passportResponse = (LinkedHashMap) user.latestResponse().getBody();
        System.out.println(passportResponse);

        assertEquals(expectedSerialNumber, passportResponse.get("Серия и номер"));
    }

    @Then("^получает список паспортов с фамилией и именем (.+) (.+)$")
    public void user_got_passport_with_such_surname_and_name(String expectedSurname, String expectedName) {
        ArrayList<LinkedHashMap> passportResponseList = (ArrayList<LinkedHashMap>) user.latestResponse().getBody();
        System.out.println(passportResponseList);

        String expectedSurnameAndName = expectedSurname + " " + expectedName;
        for (LinkedHashMap p : passportResponseList) {
            assertTrue(p.get("ФИО").toString().startsWith(expectedSurnameAndName));
        }
    }
}
