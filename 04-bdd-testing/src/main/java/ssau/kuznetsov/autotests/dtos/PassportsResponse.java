package ssau.kuznetsov.autotests.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PassportsResponse {

    private List<PassportResponse> passportResponses;

    public PassportsResponse(List<PassportResponse> passportResponses) {
        this.passportResponses = passportResponses;
    }

    @JsonProperty("Паспорты")
    public List<PassportResponse> getPassportResponses() {
        return passportResponses;
    }
}
