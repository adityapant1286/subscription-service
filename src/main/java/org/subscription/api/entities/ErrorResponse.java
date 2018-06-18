package org.subscription.api.entities;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class ErrorResponse {

    private HttpStatus status;
    private String code;
    private List<String> errors;

    public ErrorResponse(HttpStatus status, String code, List<String> errors) {
        this.status = status;
        this.code = code;
        this.errors = errors;
    }

    public ErrorResponse(HttpStatus status, String code, String error) {
        this.status = status;
        this.code = code;
        this.errors = Arrays.asList(error);
    }

    public boolean hasErrors() { return !errors.isEmpty(); }

    public HttpStatus getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public List<String> getErrors() {
        return errors;
    }
}
