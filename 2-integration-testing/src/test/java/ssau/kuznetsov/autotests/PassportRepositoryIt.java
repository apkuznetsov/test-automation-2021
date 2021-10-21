package ssau.kuznetsov.autotests;

import org.flywaydb.test.annotation.FlywayTest;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import ssau.kuznetsov.autotests.dto.PassportResponse;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class PassportRepositoryIt extends BaseIt {

    private static final String apiPath = "/api/passport/";

    private static final long expectedSerialNumber = 1678756113;
    private static final String expectedFullName = "Морозов Артём Тимофеевич";
    private static final String expectedSurname = "Морозов";
    private static final String expectedName = "Артём";
    private static final Date expectedBirthDate = Date.valueOf("1957-10-08");
    private static final Date expectedIssueDate = Date.valueOf("2012-05-17");
    private static final long noSuchSerialNumber = -1;
    private static final String noSuchSurname = "Aaaaa";

    @Test
    @FlywayTest
    public void get_correct_passport_by_serial_number() {
        // arrange
        String testUrl = apiPath + expectedSerialNumber;
        final HttpEntity<String> request = new HttpEntity<>(null, new HttpHeaders());

        // act
        final ResponseEntity<PassportResponse> response = restTemplate.exchange(
                testUrl, HttpMethod.GET, request, new ParameterizedTypeReference<>() {
                });
        PassportResponse result = response.getBody();

        // assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedSerialNumber, result.getSerialNumber());
        assertEquals(expectedIssueDate.toString(), result.getIssueDate().toString());
    }

    @Test
    @FlywayTest
    public void no_content_when_get_passport_by_no_such_serial_number() {
        // arrange
        String testUrl = apiPath + noSuchSerialNumber;
        final HttpEntity<String> request = new HttpEntity<>(null, new HttpHeaders());

        // act
        final ResponseEntity<PassportResponse> response = restTemplate.exchange(
                testUrl, HttpMethod.GET, request, new ParameterizedTypeReference<>() {
                });
        PassportResponse result = response.getBody();

        // assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(result);
    }

    @Test
    @FlywayTest
    public void get_correct_passport_by_surname_name_birthdate() {
        // arrange
        String testUrl = apiPath + expectedSurname + "/" + expectedName + "/" + expectedBirthDate.toString();
        final HttpEntity<String> request = new HttpEntity<>(null, new HttpHeaders());

        // act
        final ResponseEntity<List<PassportResponse>> response = restTemplate.exchange(
                testUrl, HttpMethod.GET, request, new ParameterizedTypeReference<>() {
                });
        PassportResponse result = response.getBody().get(0);

        // assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedFullName, result.getFullName());
        assertEquals(expectedIssueDate.toString(), result.getIssueDate().toString());
    }

    @Test
    @FlywayTest
    public void no_content_when_get_passport_by_no_such_surname_name_birthdate() {
        // arrange
        String testUrl = apiPath + noSuchSurname + "/" + expectedName + "/" + expectedBirthDate.toString();
        final HttpEntity<String> request = new HttpEntity<>(null, new HttpHeaders());

        // act
        final ResponseEntity<List<PassportResponse>> response = restTemplate.exchange(
                testUrl, HttpMethod.GET, request, new ParameterizedTypeReference<>() {
                });

        // assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    @FlywayTest
    public void get_correct_passport_by_surname() {
        // arrange
        String testUrl = apiPath + "surname/" + expectedSurname;
        final HttpEntity<String> request = new HttpEntity<>(null, new HttpHeaders());

        // act
        final ResponseEntity<List<PassportResponse>> response = restTemplate.exchange(
                testUrl, HttpMethod.GET, request, new ParameterizedTypeReference<>() {
                });
        Optional<PassportResponse> result = response.getBody().stream()
                .filter(x -> x.getFullName().equals(expectedFullName)).findFirst();

        // assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(result.isPresent());
    }
}
