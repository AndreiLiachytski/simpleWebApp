package com.chitts.exceptions;

public class AppException extends Exception {

    public AppException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AppException(final String message) {
        super(message);
    }
}