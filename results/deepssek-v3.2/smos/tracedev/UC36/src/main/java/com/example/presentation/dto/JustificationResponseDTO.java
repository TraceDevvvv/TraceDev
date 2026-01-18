package com.example.presentation.dto;

import java.util.UUID;

/**
 * Response DTO for justification insertion.
 */
public class JustificationResponseDTO {
    private boolean success;
    private UUID justificationId;
    private String message;

    public JustificationResponseDTO(boolean success, UUID justificationId, String message) {
        this.success = success;
        this.justificationId = justificationId;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public UUID getJustificationId() {
        return justificationId;
    }

    public String getMessage() {
        return message;
    }
}