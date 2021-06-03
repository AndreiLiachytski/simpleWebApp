package com.chitts.dao.exception;

import com.chitts.exception.AppException;

public class EmployeeDaoException extends AppException {

    private String message;

    public EmployeeDaoException() {
    }

    public EmployeeDaoException(final String message, final Throwable ex) {
        super(message, ex);
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(final String message) {
        this.message = message;
    }

}