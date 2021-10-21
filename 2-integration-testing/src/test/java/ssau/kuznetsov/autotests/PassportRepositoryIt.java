package ssau.kuznetsov.autotests;

import org.flywaydb.test.annotation.FlywayTest;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import ssau.kuznetsov.autotests.dto.PassportResponse;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PassportRepositoryIt extends BaseIt {

    private static final String apiPath = "/api/passport/";

    private static final long expectedSerialNumber = 1678756113;
    private static final Date expectedIssueDate = Date.valueOf("2012-05-17");
    private static final long noSuchSerialNumber = -1;

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
}
