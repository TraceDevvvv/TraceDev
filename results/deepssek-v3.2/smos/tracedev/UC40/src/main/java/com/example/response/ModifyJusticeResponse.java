package com.example.response;

/**
 * Response object for the modify justice use case.
 */
public class ModifyJusticeResponse {
    private boolean success;
    private String message;

    /**
     * Constructor.
     */
    public ModifyJusticeResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    /**
     * Returns true if the operation was successful.
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Returns the message (success or error).
     */
    public String getMessage() {
        return message;
    }
}