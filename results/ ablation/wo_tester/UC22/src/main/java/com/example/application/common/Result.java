package com.example.application.common;

/**
 * Represents the result of an operation.
 */
public class Result {
    private final boolean success;
    private final String message;
    private final Exception error;

    private Result(boolean success, String message, Exception error) {
        this.success = success;
        this.message = message;
        this.error = error;
    }

    /**
     * Creates a successful result.
     *
     * @param message Success message
     * @return Result instance
     */
    public static Result success(String message) {
        return new Result(true, message, null);
    }

    /**
     * Creates a failed result.
     *
     * @param message Error message
     * @param error   Exception (optional)
     * @return Result instance
     */
    public static Result failure(String message, Exception error) {
        return new Result(false, message, error);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Exception getError() {
        return error;
    }
}