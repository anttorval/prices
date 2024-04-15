package org.example.prices.application.exceptions;


public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }
}