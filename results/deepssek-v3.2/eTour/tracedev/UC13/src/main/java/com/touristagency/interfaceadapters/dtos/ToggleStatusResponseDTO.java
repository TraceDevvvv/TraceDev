package com.touristagency.interfaceadapters.dtos;

/**
 * Data Transfer Object for outgoing toggle status responses.
 */
public class ToggleStatusResponseDTO {
    private final boolean success;
    private final String message;

    public ToggleStatusResponseDTO(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}