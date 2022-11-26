package ssau.kuznetsov.webquizengine.Bdd;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ssau.kuznetsov.webquizengine.Bdd.helpers.AdminHttpClient;
import ssau.kuznetsov.webquizengine.Bdd.helpers.UserHttpClient;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BddSteps {

    private final AdminHttpClient admin = new AdminHttpClient();
    private final UserHttpClient user = new UserHttpClient();

    @Before
    public void resetDatabase() {
        admin.truncateDatabase();
    }

    @Given("^в базе данных есть некоторый список викторин$")
    public void there_is_quizzes() {
        admin.createTestQuizzes();
    }

    @When("^пользователь посылает запрос GET /api/quizzes$")
    public void user_reqs_quizzes() {
        user.getQuizzes();
    }

    @When("^пользователь посылает запрос GET /quizzes/page/ (\\d+)$")
    public void user_reqs_quiz_page(int page) {
        user.getQuizPage(page);
    }

    @When("^пользователь посылает запрос POST /api/quiz созадния викторины (.+) (.+) (.+)$")
    public void user_reqs_quiz_page2(String title, String question, String answer) {
        user.postQuiz(title, question, answer);
    }

    @Then("^получает код состояния (\\d+)$")
    public void user_gets_status_code(int expectedStatusCode) {
        int actualStatusCode = user.latestResponse().getStatusCode().value();
        assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Then("^получает тот некоторый список викторин$")
    public void user_gets_quizzes() {
        ArrayList<LinkedHashMap> quizList = (ArrayList<LinkedHashMap>) user.latestResponse().getBody();
        LinkedHashMap quiz1 = quizList.get(0);
        LinkedHashMap quiz2 = quizList.get(1);

        assertEquals("Английский", quiz1.get("title").toString());
        assertEquals("hello", quiz1.get("question").toString());
        assertEquals("привет", quiz1.get("answer").toString());

        assertEquals("Английский", quiz2.get("title").toString());
        assertEquals("price", quiz2.get("question").toString());
        assertEquals("цена", quiz2.get("answer").toString());
    }

    @Then("^получает некоторую первую карточку$")
    public void user_gets_quiz_page() {
        LinkedHashMap quiz = (LinkedHashMap) user.latestResponse().getBody();

        assertEquals("Английский", quiz.get("title").toString());
        assertEquals("hello", quiz.get("question").toString());
        assertEquals("привет", quiz.get("answer").toString());
    }

    @Then("^викторина (.+) (.+) (.+) есть в системе$")
    public void user_gets_quiz(String title, String question, String answer) {
        LinkedHashMap quiz = (LinkedHashMap) user.latestResponse().getBody();

        assertEquals(title, quiz.get("title").toString());
        assertEquals(question, quiz.get("question").toString());
        assertEquals(answer, quiz.get("answer").toString());
    }
}
