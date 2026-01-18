package com.example;

/**
 * Response object for inserting a teaching.
 */
public class InsertTeachingResponse extends Response {
    private boolean success;
    private Teaching teaching;
    private String errorMessage;

    /**
     * Constructor for InsertTeachingResponse.
     * @param success true if operation succeeded
     * @param teaching the saved teaching (if success)
     * @param errorMessage error message (if failure)
     */
    public InsertTeachingResponse(boolean success, Teaching teaching, String errorMessage) {
        this.success = success;
        this.teaching = teaching;
        this.errorMessage = errorMessage;
    }

    /**
     * Checks if the operation was successful.
     * @return true if successful
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Gets the saved teaching.
     * @return the teaching, or null if failure
     */
    public Teaching getTeaching() {
        return teaching;
    }

    /**
     * Gets the error message.
     * @return the error message, or null if success
     */
    public String getErrorMessage() {
        return errorMessage;
    }
}