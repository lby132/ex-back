package com.example.back.exback.api.exception;

public class LBYException extends RuntimeException {

    public LBYException() {
        super();
    }

    public LBYException(String message) {
        super(message);
    }

    public LBYException(String message, Throwable cause) {
        super(message, cause);
    }

    public LBYException(Throwable cause) {
        super(cause);
    }
}
