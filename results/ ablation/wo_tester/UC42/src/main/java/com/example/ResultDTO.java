package com.example;

/**
 * ResultDTO class for operation results.
 * Indicates success or failure along with a message.
 */
public class ResultDTO {
    private boolean success;
    private String message;

    /**
     * Constructor to create a ResultDTO.
     * @param success Whether the operation was successful.
     * @param message The result message.
     */
    public ResultDTO(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    // Getters.
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}