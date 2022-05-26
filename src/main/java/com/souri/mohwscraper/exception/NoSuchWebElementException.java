package com.souri.mohwscraper.exception;

public class NoSuchWebElementException extends RuntimeException {
    public NoSuchWebElementException(String message) {
        super(message);
    }
    public NoSuchWebElementException(String message, Throwable cause) {
        super(message, cause);
    }
}
