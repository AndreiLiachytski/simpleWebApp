package com.chitts.controller.handler;

import com.chitts.dao.exception.EmployeeDaoException;
import com.chitts.dao.exception.EmployeeNotFoundException;
import com.chitts.service.dto.error.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class CustomGlobalExceptionHandler {

    private static final String ERROR_MESSAGE = "Sorry, server has some problems. Try later.";

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleException(final Exception ex) {
        log.error("This message should be logged and not returned as response.)", ex);
        return new ErrorDto(ERROR_MESSAGE);
    }

    @ResponseBody
    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleEmployeeNotFound(final EmployeeNotFoundException ex) {
        log.error(ex.getMessage(), ex);
        return new ErrorDto(ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(EmployeeDaoException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleEmployeeDao(final EmployeeDaoException ex) {
        log.error("Failed to process DAO operation.", ex);
        return new ErrorDto(ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleHttpMessageNotReadable(final HttpMessageNotReadableException ex) {
        log.error("Validation Exception", ex);
        return new ErrorDto(ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleMethodArgumentNotValid(final MethodArgumentNotValidException ex) {
        log.error("Validation Exception", ex);
        return new ErrorDto(ex.getMessage());
    }

}