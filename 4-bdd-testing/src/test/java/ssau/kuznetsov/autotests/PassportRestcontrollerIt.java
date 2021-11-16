package ssau.kuznetsov.autotests;

import org.flywaydb.test.annotation.FlywayTest;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import ssau.kuznetsov.autotests.configs.PostgresConfig;
import ssau.kuznetsov.autotests.dto.PassportResponse;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class PassportRestcontrollerIt extends PostgresConfig {

    private static final String apiUrl = "/api/passport/";
    private static final long expectedSerialNumber = 1678756113;
    private static final String expectedFullName = "Морозов Артём Тимофеевич";
    private static final String expectedSurname = "Морозов";
    private static final String expectedName = "Артём";
    private static final Date expectedBirthDate = Date.valueOf("1957-10-08");
    private static final Date expectedIssueDate = Date.valueOf("2012-05-17");
    private static final long noSuchSerialNumber = -1;
    private static final String noSuchSurname = "-1";
    private static final String noSuchName = "-1";
    private static final Date noSuchBirthDate = new Date(0L);

    @Test
    @FlywayTest
    public void get_correct_passport_by_serial_number() {
        // arrange
        String testUrl = apiUrl + expectedSerialNumber;
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
        String testUrl = apiUrl + noSuchSerialNumber;
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
        String testUrl = apiUrl + expectedSurname + "/" + expectedName + "/" + expectedBirthDate.toString();
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
        String testUrl = apiUrl + noSuchSurname + "/" + expectedName + "/" + expectedBirthDate.toString();
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
        String testUrl = apiUrl + "surname/" + expectedSurname;
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

    @Test
    @FlywayTest
    public void no_content_when_get_passport_by_no_such_surname() {
        // arrange
        String testUrl = apiUrl + "surname/" + noSuchSurname;
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
    public void get_correct_passport_by_name() {
        // arrange
        String testUrl = apiUrl + "name/" + expectedName;
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

    @Test
    @FlywayTest
    public void no_content_when_get_passport_by_no_such_name() {
        // arrange
        String testUrl = apiUrl + "surname/" + noSuchName;
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
    public void get_correct_passport_by_birthdate() {
        // arrange
        String testUrl = apiUrl + "birth-date/" + expectedBirthDate.toString();
        final HttpEntity<String> request = new HttpEntity<>(null, new HttpHeaders());

        // act
        final ResponseEntity<List<PassportResponse>> response = restTemplate.exchange(
                testUrl, HttpMethod.GET, request, new ParameterizedTypeReference<>() {
                });
        Optional<PassportResponse> result = response.getBody().stream()
                .filter(x -> x.getBirthDate().toString().equals(expectedBirthDate.toString())).findFirst();

        // assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(result.isPresent());
    }

    @Test
    @FlywayTest
    public void no_content_when_get_passport_by_no_such_birthdate() {
        // arrange
        String testUrl = apiUrl + "birth-date/" + noSuchBirthDate;
        final HttpEntity<String> request = new HttpEntity<>(null, new HttpHeaders());

        // act
        final ResponseEntity<List<PassportResponse>> response = restTemplate.exchange(
                testUrl, HttpMethod.GET, request, new ParameterizedTypeReference<>() {
                });

        // assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        //assertNull(response.getBody());
    }

    @Test
    @FlywayTest
    public void get_correct_passport_by_surname_name() {
        // arrange
        String testUrl = apiUrl + "surname/" + expectedSurname + "/name/" + expectedName;
        final HttpEntity<String> request = new HttpEntity<>(null, new HttpHeaders());

        // act
        final ResponseEntity<List<PassportResponse>> response = restTemplate.exchange(
                testUrl, HttpMethod.GET, request, new ParameterizedTypeReference<>() {
                });
        Optional<PassportResponse> result = response.getBody().stream()
                .filter(x -> {
                    String[] word = x.getFullName().split(" ");
                    return word[0].equals(expectedSurname)
                            && word[1].equals(expectedName);
                })
                .findFirst();

        // assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(result.isPresent());
    }

    @Test
    @FlywayTest
    public void no_content_when_get_passport_by_no_such_surname_name() {
        // arrange
        String testUrl = apiUrl + "surname/" + noSuchSurname + "/name/" + expectedName;
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
    public void get_correct_passport_by_surname_birthdate() {
        // arrange
        String testUrl = apiUrl + "surname/" + expectedSurname + "/birth-date/" + expectedBirthDate.toString();
        final HttpEntity<String> request = new HttpEntity<>(null, new HttpHeaders());

        // act
        final ResponseEntity<List<PassportResponse>> response = restTemplate.exchange(
                testUrl, HttpMethod.GET, request, new ParameterizedTypeReference<>() {
                });
        Optional<PassportResponse> result = response.getBody().stream()
                .filter(x -> {
                    String[] word = x.getFullName().split(" ");
                    return word[0].equals(expectedSurname)
                            && x.getBirthDate().toString()
                            .equals(expectedBirthDate.toString());
                })
                .findFirst();

        // assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(result.isPresent());
    }

    @Test
    @FlywayTest
    public void no_content_when_get_passport_by_no_such_surname_birthdate() {
        // arrange
        String testUrl = apiUrl + "surname/" + noSuchSurname + "/birth-date/" + expectedBirthDate.toString();
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
    public void get_correct_passport_by_name_birthdate() {
        // arrange
        String testUrl = apiUrl + "name/" + expectedName + "/birth-date/" + expectedBirthDate.toString();
        final HttpEntity<String> request = new HttpEntity<>(null, new HttpHeaders());

        // act
        final ResponseEntity<List<PassportResponse>> response = restTemplate.exchange(
                testUrl, HttpMethod.GET, request, new ParameterizedTypeReference<>() {
                });
        Optional<PassportResponse> result = response.getBody().stream()
                .filter(x -> {
                    String[] word = x.getFullName().split(" ");
                    return word[1].equals(expectedName)
                            && x.getBirthDate().toString()
                            .equals(expectedBirthDate.toString());
                })
                .findFirst();

        // assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(result.isPresent());
    }

    @Test
    @FlywayTest
    public void no_content_when_get_passport_by_no_such_name_birthdate() {
        // arrange
        String testUrl = apiUrl + "name/" + noSuchName + "/birth-date/" + expectedBirthDate.toString();
        final HttpEntity<String> request = new HttpEntity<>(null, new HttpHeaders());

        // act
        final ResponseEntity<List<PassportResponse>> response = restTemplate.exchange(
                testUrl, HttpMethod.GET, request, new ParameterizedTypeReference<>() {
                });

        // assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }
}
