package com.security.controller;

import com.security.constants.SecurityConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException e) {
        return new ResponseEntity<>(SecurityConstants.FAILED + SecurityConstants.COLON_SEPARATOR + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
