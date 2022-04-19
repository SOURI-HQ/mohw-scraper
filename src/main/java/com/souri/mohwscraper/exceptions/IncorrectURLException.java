package com.souri.mohwscraper.exceptions;

public class IncorrectURLException extends RuntimeException {
    public IncorrectURLException(String message) {
        super(message);
    }
    public IncorrectURLException(String message, Throwable cause) {
        super(message, cause);
    }
}
