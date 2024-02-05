package com.example.back.exback.api;

import com.example.back.exback.api.ApiResponse;
import com.example.back.exback.api.exception.ApiException;
import com.example.back.exback.api.exception.PostNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ApiResponse bindException(BindException e) {
        return ApiResponse.of(
                HttpStatus.BAD_REQUEST,
                e.getBindingResult().getAllErrors().get(0).getDefaultMessage(),
                null
        );
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse> apiException(ApiException e) {
        ApiResponse body = ApiResponse.apiErrorResponse(
                e.getStatus(),
                e.getMessage());

        return ResponseEntity.status(e.getStatus()).body(body);
    }

}
