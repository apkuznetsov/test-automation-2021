package ssau.kuznetsov.autotests.helpers;

import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ssau.kuznetsov.autotests.dtos.PassportResponse;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class PassportHttpClient {

    private static final RestTemplate restTemplate = new RestTemplate();

    private static final int port = 8080;

    private static ResponseEntity<PassportResponse> response;

    private static String endpoint() {
        return "http://localhost:" + port + "/api/passport/";
    }

    public static ResponseEntity<PassportResponse> latestResponse() {
        return response;
    }

    public static void get(long serialNumber) {
        response = restTemplate.exchange(
                endpoint() + serialNumber,
                HttpMethod.GET,
                new HttpEntity<>(null, new HttpHeaders()),
                new ParameterizedTypeReference<>() {
                });
    }
}
