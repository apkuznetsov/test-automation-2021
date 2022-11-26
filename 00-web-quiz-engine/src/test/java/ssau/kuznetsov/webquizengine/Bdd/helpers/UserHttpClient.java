package ssau.kuznetsov.webquizengine.Bdd.helpers;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ssau.kuznetsov.webquizengine.models.Quiz;

import static ssau.kuznetsov.webquizengine.Bdd.helpers.HttpClientConfig.*;

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

    public void getQuizzes() {
        latestResponse = REST.exchange(
                URL + CLIENT_API_QUIZZES,
                HttpMethod.GET,
                ADMIN_HTTP_ENTITY,
                new ParameterizedTypeReference<>() {
                });
    }

    public void getQuizPage(int page) {
        latestResponse = REST.exchange(
                URL + CLIENT_API_QUIZ_PAGE + page,
                HttpMethod.GET,
                ADMIN_HTTP_ENTITY,
                new ParameterizedTypeReference<>() {
                });
    }

    public void postQuiz(String title, String question, String answer) {
        HttpEntity<Quiz> quizHttpEntity = new HttpEntity<>(
                new Quiz(title, question, answer), ADMIN_HTTP_HEADERS);

        latestResponse = REST.exchange(
                URL + CLIENT_API_QUIZ,
                HttpMethod.POST,
                quizHttpEntity,
                new ParameterizedTypeReference<>() {
                });
    }
}
