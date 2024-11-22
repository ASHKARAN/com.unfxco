package com.unfxco.usermanagement.dto.response.login;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponse {


    private final Boolean success = false;
    private String error;
    public ErrorResponse(String error) {
        this.error = error;
    }
    public ErrorResponse print() {
       return this;
    }

    public String toJson() {
        return "json";
    }
}
