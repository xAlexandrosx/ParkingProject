package org.example.exceptions;

public class DuplicateRegistrationException extends RuntimeException{
    public DuplicateRegistrationException(String message) {
        super(message);
    }
}
