package com.chitts.dao.exception;

import lombok.Getter;

@Getter
public class EmployeeNotFoundException extends Exception {

    private final String message;

    public EmployeeNotFoundException(final long id) {
        this.message = "Employee with id = " + id + " does not exist. Check your request parameters";
    }

}