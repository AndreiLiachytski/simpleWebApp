package com.chitts.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseForExceptionHandler {

    private final String message;

}