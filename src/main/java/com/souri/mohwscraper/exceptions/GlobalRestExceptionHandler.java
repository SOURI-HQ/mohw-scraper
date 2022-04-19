package com.souri.mohwscraper.exceptions;

import org.openqa.selenium.TimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {IncorrectURLException.class})
    protected ResponseEntity<Object> handleIncorrectURLException(IncorrectURLException e) {
        return buildErrorResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, e.getMessage()));
    }

    @ExceptionHandler(value = {NoSuchWebElementException.class, NullPointerException.class})
    protected ResponseEntity<Object> handleNoSuchWebElementException(NoSuchWebElementException e) {
        return buildErrorResponseEntity(new ApiError(HttpStatus.SERVICE_UNAVAILABLE, e.getMessage()));
    }

    @ExceptionHandler(value = {TimeoutException.class})
    protected ResponseEntity<Object> handleTimeoutException(TimeoutException e) {
        return buildErrorResponseEntity(new ApiError(HttpStatus.SERVICE_UNAVAILABLE, e.getMessage()));
    }

    private ResponseEntity<Object> buildErrorResponseEntity(ApiError apiError) {
        return new ResponseEntity<Object>(apiError, apiError.getStatus());
    }
}
