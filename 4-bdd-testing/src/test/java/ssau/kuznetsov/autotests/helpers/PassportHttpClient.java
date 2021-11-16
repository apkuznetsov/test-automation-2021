package ssau.kuznetsov.autotests.helpers;

import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class PassportHttpClient {

    private final String SERVER_URL = "http://localhost";
    private final String PASSPORT_ENDPOINT = "/api/passport";
    private final RestTemplate restTemplate = new RestTemplate();
    @LocalServerPort
    private int port;

    private String thingsEndpoint() {
        return SERVER_URL + ":" + port + PASSPORT_ENDPOINT;
    }

    public int put(final String something) {
        return restTemplate.postForEntity(thingsEndpoint(), something, Void.class).getStatusCodeValue();
    }
}