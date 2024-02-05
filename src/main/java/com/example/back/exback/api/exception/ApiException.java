package com.example.back.exback.api.exception;

import org.springframework.http.HttpStatus;

public abstract class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

//    public abstract int getStatusCode();

    public abstract HttpStatus getStatus();
}
