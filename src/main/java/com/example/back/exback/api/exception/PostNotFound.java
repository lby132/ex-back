package com.example.back.exback.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class PostNotFound extends ApiException {

    private static final String MESSAGE = "존재 하지 않습니다.";

    public PostNotFound() {
        super(MESSAGE);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
