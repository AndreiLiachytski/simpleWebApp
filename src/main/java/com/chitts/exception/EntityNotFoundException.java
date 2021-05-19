package com.chitts.exception;

public class EntityNotFoundException extends AppException {

    public EntityNotFoundException(long id) {
        super("Entity with id = " + id + " does not exist. Check your request parameters");
    }
}