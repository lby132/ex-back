package com.example.back.exback.api.exception;

import lombok.Getter;

@Getter
public class NotFound extends LBYException{

    private static final String MESSAGE = "존재 하지 않습니다.";

    public NotFound() {
        super(MESSAGE);
    }

}
