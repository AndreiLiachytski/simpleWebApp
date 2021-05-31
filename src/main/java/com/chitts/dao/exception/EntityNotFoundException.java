package com.chitts.dao.exception;

public class EntityNotFoundException extends DaoException {

    public EntityNotFoundException(long id) {
        super("Entity with id = " + id + " does not exist. Check your request parameters");
    }

}