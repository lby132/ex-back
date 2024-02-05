package com.example.back.exback.api.exception;

import lombok.Getter;

@Getter
public class PostNotFound extends ApiException {

    private static final String MESSAGE = "값이 존재 하지 않습니다.";

    public PostNotFound() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
