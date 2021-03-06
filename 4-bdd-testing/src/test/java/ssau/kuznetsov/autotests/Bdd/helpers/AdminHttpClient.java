package ssau.kuznetsov.autotests.Bdd.helpers;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import static ssau.kuznetsov.autotests.Bdd.helpers.HttpClientConfig.*;

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

    public void truncateCitizen() {
        REST.exchange(
                URL + ADMIN_TRUNCATE_CITIZEN,
                HttpMethod.GET,
                new HttpEntity<>(null, new HttpHeaders()),
                new ParameterizedTypeReference<>() {
                });
    }

    public void truncateDatabase() {
        REST.exchange(
                URL + ADMIN_TRUNCATE_DATABASE,
                HttpMethod.GET,
                new HttpEntity<>(null, new HttpHeaders()),
                new ParameterizedTypeReference<>() {
                });
    }

    public void createTestPassportWithSuchSerialNumber(long serialNumber) {
        REST.exchange(
                URL + ADMIN_CREATE_PASSPORT + "/" + serialNumber,
                HttpMethod.GET,
                new HttpEntity<>(null, new HttpHeaders()),
                new ParameterizedTypeReference<>() {
                });
    }

    public void createTestPassportsWithSuchSurnameAndName(String surname, String name) {
        REST.exchange(
                URL + ADMIN_CREATE_PASSPORT + "/surname/" + surname + "/name/" + name,
                HttpMethod.GET,
                new HttpEntity<>(null, new HttpHeaders()),
                new ParameterizedTypeReference<>() {
                });
    }
}
