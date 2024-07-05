package com.sankadilshan.myday.expense.exception;

public class AuthorizationFailedException extends RuntimeException {

    private static final String message = "User Authentication Failed, Provide valid authentication token.";
    public AuthorizationFailedException() {
        super(message);
    }
}
