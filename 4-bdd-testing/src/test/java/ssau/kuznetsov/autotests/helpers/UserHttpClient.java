package ssau.kuznetsov.autotests.helpers;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ssau.kuznetsov.autotests.dtos.PassportResponse;

import static ssau.kuznetsov.autotests.helpers.HttpClientConfig.*;

public class UserHttpClient {

    private final String URL;
    private final RestTemplate REST;
    private ResponseEntity latestResponse;

    public UserHttpClient() {
        URL = HOST + PORT;
        REST = new RestTemplate();
    }

    public ResponseEntity latestResponse() {
        return latestResponse;
    }

    public void getPassportBySerialNumber(long serialNumber) {
        latestResponse = REST.exchange(
                URL + CLIENT_API_PASSPORT + serialNumber,
                HttpMethod.GET,
                new HttpEntity<>(null, new HttpHeaders()),
                new ParameterizedTypeReference<>() {
                });
    }

    public void getPassportsBySurnameAndName(String surname, String name) {
        latestResponse = REST.exchange(
                URL + CLIENT_API_PASSPORT + "/surname/" + surname + "/name/" + name,
                HttpMethod.GET,
                new HttpEntity<>(null, new HttpHeaders()),
                new ParameterizedTypeReference<>() {
                });
    }
}
