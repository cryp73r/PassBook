package com.cryp73r.passbook.exception;

public class InternalServerException extends RuntimeException {
    public InternalServerException(String errorMessage) {
        super(errorMessage);
    }
}
