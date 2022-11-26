package ssau.kuznetsov.webquizengine.Bdd.helpers;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import static ssau.kuznetsov.webquizengine.Bdd.helpers.HttpClientConfig.*;

public class AdminHttpClient {

    private final String URL;
    private final RestTemplate REST;

    public AdminHttpClient() {
        URL = HOST + PORT;
        REST = new RestTemplate();
    }

    public void truncateDatabase() {
        REST.exchange(
                URL + ADMIN_TRUNCATE_DATABASE,
                HttpMethod.GET,
                ADMIN_HTTP_ENTITY,
                new ParameterizedTypeReference<>() {
                });
    }

    public void createTestQuizzes() {
        REST.exchange(
                URL + ADMIN_CREATE_QUIZZES,
                HttpMethod.GET,
                ADMIN_HTTP_ENTITY,
                new ParameterizedTypeReference<>() {
                });
    }
}
