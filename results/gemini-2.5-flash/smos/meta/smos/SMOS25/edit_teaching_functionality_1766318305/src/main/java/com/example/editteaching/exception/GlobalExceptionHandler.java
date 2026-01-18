package com.example.editteaching.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for the application.
 * This class uses Spring's {@link ControllerAdvice} to provide centralized exception handling
 * across all {@link org.springframework.web.bind.annotation.RestController} classes.
 * It catches specific exceptions and returns appropriate HTTP responses.
 * It strictly adheres to the data structures and interfaces defined in the system design.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles {@link TeachingNotFoundException}.
     * This exception is typically thrown when a request is made for a teaching ID that does not exist.
     * It maps to an HTTP 404 Not Found status.
     *
     * @param ex The TeachingNotFoundException instance.
     * @return A {@link ResponseEntity} with an error message and HTTP 404 status.
     */
    @ExceptionHandler(TeachingNotFoundException.class)
    public ResponseEntity<String> handleTeachingNotFoundException(TeachingNotFoundException ex) {
        // Log the exception for debugging purposes
        System.err.println("TeachingNotFoundException: " + ex.getMessage());
        // Return a 404 Not Found status with the exception message
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles {@link InvalidTeachingDataException}.
     * This exception is thrown when the business logic detects invalid data,
     * for example, if start date is after end date, or other custom validations fail.
     * It maps to an HTTP 400 Bad Request status. This corresponds to the "Errodati" use case.
     *
     * @param ex The InvalidTeachingDataException instance.
     * @return A {@link ResponseEntity} with an error message and HTTP 400 status.
     */
    @ExceptionHandler(InvalidTeachingDataException.class)
    public ResponseEntity<String> handleInvalidTeachingDataException(InvalidTeachingDataException ex) {
        // Log the exception for debugging purposes
        System.err.println("InvalidTeachingDataException: " + ex.getMessage());
        // Return a 400 Bad Request status with the exception message
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles {@link MethodArgumentNotValidException}.
     * This exception is thrown when validation on an argument annotated with @Valid fails.
     * It collects all validation errors and returns them in a structured map.
     * It maps to an HTTP 400 Bad Request status.
     *
     * @param ex The MethodArgumentNotValidException instance.
     * @return A {@link ResponseEntity} with a map of field errors and HTTP 400 status.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        // Log the validation errors
        System.err.println("Validation Errors: " + errors);
        // Return a 400 Bad Request status with the validation errors
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles all other generic {@link Exception} types.
     * This acts as a fallback for any unhandled exceptions, ensuring a consistent error response.
     * It maps to an HTTP 500 Internal Server Error status. This can cover cases like
     * "Connection to the SMOS server interrupted" if it manifests as a generic database exception.
     *
     * @param ex The generic Exception instance.
     * @param request The current web request.
     * @return A {@link ResponseEntity} with a generic error message and HTTP 500 status.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex, WebRequest request) {
        // Log the full stack trace for critical errors
        ex.printStackTrace();
        // Return a 500 Internal Server Error status with a generic message
        return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}