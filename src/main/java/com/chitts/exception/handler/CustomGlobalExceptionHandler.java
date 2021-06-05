package com.chitts.exception.handler;

import com.chitts.dao.exception.EmployeeDaoException;
import com.chitts.dao.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@ControllerAdvice
public class CustomGlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseForExceptionHandler> handleException(final EntityNotFoundException e) {
        log.error(e.getMessage(), e);
        final ResponseForExceptionHandler response = new ResponseForExceptionHandler(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ResponseForExceptionHandler> handleException(final ResponseStatusException e) {
        log.error(e.getMessage(), e);
        final ResponseForExceptionHandler response = new ResponseForExceptionHandler(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmployeeDaoException.class)
    public ResponseEntity<ResponseForExceptionHandler> handleException(final EmployeeDaoException e) {
        log.error(e.getMessage(), e);
        final ResponseForExceptionHandler response = new ResponseForExceptionHandler(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}