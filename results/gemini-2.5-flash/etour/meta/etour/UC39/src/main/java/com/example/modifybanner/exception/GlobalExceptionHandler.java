package com.example.modifybanner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Global exception handler for the application.
 * This class provides centralized exception handling across all controllers,
 * ensuring consistent error responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles InvalidImageException, returning a 400 Bad Request status.
     *
     * @param ex The InvalidImageException that was thrown.
     * @param request The current web request.
     * @return A ResponseEntity containing error details.
     */
    @ExceptionHandler(InvalidImageException.class)
    public ResponseEntity<Object> handleInvalidImageException(InvalidImageException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Bad Request");
        body.put("message", ex.getMessage());
        body.put("path", request.getDescription(false));
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles BannerNotFoundException, returning a 404 Not Found status.
     *
     * @param ex The BannerNotFoundException that was thrown.
     * @param request The current web request.
     * @return A ResponseEntity containing error details.
     */
    @ExceptionHandler(BannerNotFoundException.class)
    public ResponseEntity<Object> handleBannerNotFoundException(BannerNotFoundException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", "Not Found");
        body.put("message", ex.getMessage());
        body.put("path", request.getDescription(false));
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles generic RuntimeException, returning a 500 Internal Server Error status.
     * This is a fallback for any unhandled runtime exceptions.
     *
     * @param ex The RuntimeException that was thrown.
     * @param request The current web request.
     * @return A ResponseEntity containing error details.
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleAllUncaughtException(RuntimeException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", "Internal Server Error");
        body.put("message", "An unexpected error occurred: " + ex.getMessage());
        body.put("path", request.getDescription(false));
        // Log the exception for debugging purposes
        logger.error("An unexpected error occurred: ", ex);
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}