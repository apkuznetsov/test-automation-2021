package ssau.kuznetsov.autotests;

import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.HttpStatus;
import ssau.kuznetsov.autotests.dtos.PassportResponse;
import ssau.kuznetsov.autotests.helpers.AdminHttpClient;
import ssau.kuznetsov.autotests.helpers.UserHttpClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BddIt {

    private final AdminHttpClient admin = new AdminHttpClient();
    private final UserHttpClient user = new UserHttpClient();

    @Before
    public void resetDatabase() {
        admin.truncateDatabase();
    }

    @When("^пользователь посылает запрос GET /api/passport/ (\\d+)$")
    public void user_gets_passport_by_serial_number(long serialNumber) {
        user.get(serialNumber);
    }

    @Then("^получает код состояния (\\d+)$")
    public void user_receives_status_code_of(int expectedStatusCode) {
        HttpStatus actualStatusCode = user.latestResponse().getStatusCode();
        assertEquals(HttpStatus.OK, actualStatusCode);
    }

    @Then("^получает паспорт с серийным номером (\\d+)$")
    public void user_receives_status_code_of(long expectedSerialNumber) {
        PassportResponse result = user.latestResponse().getBody();
        assertEquals(expectedSerialNumber, result.getSerialNumber());
    }
}
