package com.cryp73r.passbook.exception;

public class BadCredentialsException extends RuntimeException {

    public BadCredentialsException(String errorMessage) {
        super(errorMessage);
    }
}
