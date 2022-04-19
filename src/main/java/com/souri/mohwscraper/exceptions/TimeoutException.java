package com.souri.mohwscraper.exceptions;

public class TimeoutException extends RuntimeException {
    public TimeoutException(String message) {
        super(message);
    }
    public TimeoutException(String message, Throwable cause) {
        super(message, cause);
    }
}
