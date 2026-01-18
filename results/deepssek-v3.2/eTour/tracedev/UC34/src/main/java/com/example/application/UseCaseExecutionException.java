package com.example.application;

/**
 * Exception thrown when use case execution fails.
 */
public class UseCaseExecutionException extends Exception {
    private int errorCode;

    public UseCaseExecutionException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}