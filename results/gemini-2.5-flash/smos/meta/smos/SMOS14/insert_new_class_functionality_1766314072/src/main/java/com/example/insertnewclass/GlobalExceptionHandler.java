package com.example.insertnewclass;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for the application.
 * This class intercepts exceptions thrown by controllers and provides a centralized
 * way to handle them, returning consistent error responses to the client.
 * It implements the "Errodati" use case by providing detailed error messages.
 */
@ControllerAdvice // Marks this class as a global exception handler.
public class GlobalExceptionHandler {

    /**
     * Handles {@link InvalidClassDataException}.
     * This exception is typically thrown by the service layer when business logic validation fails.
     * It maps to an HTTP 400 Bad Request status.
     *
     * @param ex The InvalidClassDataException that was thrown.
     * @return A ResponseEntity containing a map with error details and an HTTP 400 status.
     */
    @ExceptionHandler(InvalidClassDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // Sets the HTTP status code for this exception.
    public ResponseEntity<Map<String, String>> handleInvalidClassDataException(InvalidClassDataException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Invalid Class Data");
        errorResponse.put("message", ex.getMessage()); // Provides the specific validation error message.
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles {@link MethodArgumentNotValidException}.
     * This exception is thrown when an argument annotated with {@code @Valid} fails validation.
     * It typically occurs in the controller layer due to JSR-303 (Jakarta Validation) annotations.
     * It maps to an HTTP 400 Bad Request status and provides details for each field error.
     *
     * @param ex The MethodArgumentNotValidException that was thrown.
     * @return A ResponseEntity containing a map with field-specific error details and an HTTP 400 status.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        // Collect all field errors and their default messages.
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        // Add a general error message for context.
        errors.put("error", "Validation Failed");
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles all other unexpected exceptions.
     * This is a catch-all handler for any exceptions not specifically handled by other methods.
     * It maps to an HTTP 500 Internal Server Error status.
     *
     * @param ex The general Exception that was thrown.
     * @return A ResponseEntity containing a map with a generic error message and an HTTP 500 status.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Map<String, String>> handleException(Exception ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Internal Server Error");
        // For security reasons, avoid exposing internal exception messages directly in production.
        // A more generic message is often preferred, and the actual exception can be logged.
        errorResponse.put("message", "An unexpected error occurred. Please try again later.");
        // Log the exception for debugging purposes.
        // Logger.error("An unexpected error occurred: {}", ex.getMessage(), ex);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}