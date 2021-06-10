package com.chitts.dao.exception;

import lombok.Getter;

@Getter
public class EmployeeDaoException extends Exception {

    public EmployeeDaoException(final String message, final Throwable ex) {
        super(message, ex);
    }

}