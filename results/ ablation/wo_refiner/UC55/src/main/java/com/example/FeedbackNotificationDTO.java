package com.example;

/**
 * Data Transfer Object for the notification response.
 * Stereotype: DTO
 */
public class FeedbackNotificationDTO {
    private String message;
    private String operationStatus;

    public FeedbackNotificationDTO(String message, String operationStatus) {
        this.message = message;
        this.operationStatus = operationStatus;
    }

    public String getMessage() {
        return message;
    }

    public String getOperationStatus() {
        return operationStatus;
    }
}