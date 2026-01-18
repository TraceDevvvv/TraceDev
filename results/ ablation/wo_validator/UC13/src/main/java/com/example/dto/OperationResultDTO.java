package com.example.dto;

/**
 * Data Transfer Object for operation results.
 */
public class OperationResultDTO {
    private boolean successful;
    private String message;
    private String touristId;
    private boolean newStatus;

    public OperationResultDTO(boolean successful, String message, String touristId, boolean newStatus) {
        this.successful = successful;
        this.message = message;
        this.touristId = touristId;
        this.newStatus = newStatus;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public String getMessage() {
        return message;
    }

    public String getTouristId() {
        return touristId;
    }

    public boolean getNewStatus() {
        return newStatus;
    }
}