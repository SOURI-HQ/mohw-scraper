package com.souri.mohwscraper.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

class ApiError {
    private HttpStatus status;
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    ApiError() {
        this.timestamp = LocalDateTime.now();
    }

    ApiError(HttpStatus status, String message) {
        this();
        this.status = status;
        this.message = message;
    }

    HttpStatus getStatus() {
        return status;
    }

    void setStatus(HttpStatus status) {
        this.status = status;
    }

    String getMessage() {
        return message;
    }

    void setMessage(String message) {
        this.message = message;
    }

    LocalDateTime getTimestamp() {
        return timestamp;
    }

    void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
