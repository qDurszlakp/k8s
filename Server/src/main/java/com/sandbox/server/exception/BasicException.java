package com.sandbox.server.exception;

public class BasicException extends RuntimeException {

    public BasicException(String message) {
        super(message);
    }

    public BasicException(String message, Throwable cause) {
        super(message, cause);
    }

    public BasicException() {}
}