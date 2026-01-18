package com.example.refreshmentpoint.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        // This handler catches the RuntimeException thrown for the 15-second timeout
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.REQUEST_TIMEOUT);
    }

    // You can add more specific exception handlers here
}
