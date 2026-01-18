package com.example.statistics;

/**
 * Custom exception for application/domain layer business logic errors.
 * Used to signal issues that are relevant to the application's business rules,
 * often wrapping lower-level exceptions like DataAccessException.
 */
public class BusinessException extends Exception {
    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}