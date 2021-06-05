package com.chitts.dao.exception;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends Exception {

    private final String message;

    public EntityNotFoundException(final long id) {
        this.message = "Entity with id = " + id + " does not exist. Check your request parameter";
    }

}