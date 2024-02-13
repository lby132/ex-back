package com.example.back.exback.api.exception;

import org.springframework.http.HttpStatus;

public class InvalidRequest extends ApiException {

    public InvalidRequest(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
