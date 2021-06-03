package com.chitts.dao.exception;

public class EntityNotFoundException extends EmployeeDaoException {

    private String message;

    public EntityNotFoundException(final long id) {
        this.message = "Entity with id = " + id + " does not exist. Check your request parameters";
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