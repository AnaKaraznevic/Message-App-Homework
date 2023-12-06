package com.example.messageapp.exception;

public class FailedToCreateUserException extends RuntimeException {
    public FailedToCreateUserException(String message) {
        super(message);
    }
}
