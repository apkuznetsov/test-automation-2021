package ssau.kuznetsov.autotests;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.HttpStatus;
import ssau.kuznetsov.autotests.dtos.PassportResponse;
import ssau.kuznetsov.autotests.helpers.PassportHttpClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CucumberIt {

    @When("^клиент посылает запрос GET /api/passport/ (\\d+)$")
    public void client_gets_passport_by_serial_number(long serialNumber) {
        PassportHttpClient.get(serialNumber);
    }

    @Then("^получает код состояния (\\d+)$")
    public void client_receives_status_code_of(int expectedStatusCode) {
        HttpStatus actualStatusCode = PassportHttpClient.latestResponse().getStatusCode();
        assertEquals(HttpStatus.OK, actualStatusCode);
    }

    @Then("^получает паспорт с серийным номером (\\d+)$")
    public void the_client_receives_status_code_of(long expectedSerialNumber) {
        PassportResponse result = PassportHttpClient.latestResponse().getBody();
        assertEquals(expectedSerialNumber, result.getSerialNumber());
    }
}
