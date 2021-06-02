package com.chitts.exception;

public class AppException extends Exception {

    private String message;

    public AppException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AppException() {
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

}