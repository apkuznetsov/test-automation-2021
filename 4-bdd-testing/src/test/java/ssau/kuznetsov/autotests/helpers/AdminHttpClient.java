package ssau.kuznetsov.autotests.helpers;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import static ssau.kuznetsov.autotests.helpers.HttpClientConfig.*;

public class AdminHttpClient {

    private final String URL;
    private final RestTemplate REST;

    public AdminHttpClient() {
        URL = HOST + PORT;
        REST = new RestTemplate();
    }

    public void truncatePassport() {
        REST.exchange(
                URL + ADMIN_TRUNCATE_PASSPORT,
                HttpMethod.GET,
                new HttpEntity<>(null, new HttpHeaders()),
                new ParameterizedTypeReference<>() {
                });
    }
}
