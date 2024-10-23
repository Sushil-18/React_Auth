package com.example.backend.exceptions;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequestException(BadRequestException badRequestException){
        ApiError apiError = new ApiError.Builder()
                .statusCode(HttpStatus.BAD_REQUEST)
                .error(badRequestException.getMessage())
                .message(badRequestException.getLocalizedMessage())
                .build();

        return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
    }
}
