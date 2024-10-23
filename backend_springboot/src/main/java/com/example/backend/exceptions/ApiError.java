package com.example.backend.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiError {

    private HttpStatus statusCode;
    private String error;
    private String message;
    private LocalDateTime timestamp;

    public ApiError(Builder builder){
        this.statusCode = builder.statusCode;
        this.error = builder.error;
        this.message = builder.message;
        this.timestamp = LocalDateTime.now();
    }

    public static class Builder {
        private HttpStatus statusCode;
        private String error;
        private String message;

        public Builder statusCode(HttpStatus statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public Builder error(String error) {
            this.error = error;
            return this;
        }

        public Builder message(String message){
            this.message = message;
            return this;
        }

        public ApiError build() {
            return new ApiError(this);
        }
    }
}
