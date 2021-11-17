package ssau.kuznetsov.autotests;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import ssau.kuznetsov.autotests.dtos.PassportResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BddIt {

    private final int port = 8080;
    private final String url = "http://localhost:" + port + "/api/passport/";

    private final RestTemplate restTemplate = new RestTemplate();
    private ResponseEntity<PassportResponse> latestResponse;

    public void get(long serialNumber) {
        latestResponse = restTemplate.exchange(
                url + serialNumber,
                HttpMethod.GET,
                new HttpEntity<>(null, new HttpHeaders()),
                new ParameterizedTypeReference<>() {
                });
    }

    @When("^клиент посылает запрос GET /api/passport/ (\\d+)$")
    public void client_gets_passport_by_serial_number(long serialNumber) {
        get(serialNumber);
    }

    @Then("^получает код состояния (\\d+)$")
    public void client_receives_status_code_of(int expectedStatusCode) {
        HttpStatus actualStatusCode = latestResponse.getStatusCode();
        assertEquals(HttpStatus.OK, actualStatusCode);
    }

    @Then("^получает паспорт с серийным номером (\\d+)$")
    public void the_client_receives_status_code_of(long expectedSerialNumber) {
        PassportResponse result = latestResponse.getBody();
        assertEquals(expectedSerialNumber, result.getSerialNumber());
    }
}
