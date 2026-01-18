package com.example.dto;

/**
 * Data Transfer Object for convention response.
 */
public class ConventionResponseDTO {
    private boolean success;
    private String message;
    private int conventionId;

    /**
     * Constructor for creating a response.
     */
    public ConventionResponseDTO(boolean success, String message, int conventionId) {
        this.success = success;
        this.message = message;
        this.conventionId = conventionId;
    }

    // Getters
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public int getConventionId() {
        return conventionId;
    }
}