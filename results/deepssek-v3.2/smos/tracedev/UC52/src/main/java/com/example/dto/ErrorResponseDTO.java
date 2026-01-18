package com.example.dto;

/**
 * Data Transfer Object for error responses.
 * Wraps exception details for presentation layer.
 */
public class ErrorResponseDTO {
    private String errorCode;
    private String message;
    private String details;

    // Constructors
    public ErrorResponseDTO() {
    }

    public ErrorResponseDTO(String errorCode, String message, String details) {
        this.errorCode = errorCode;
        this.message = message;
        this.details = details;
    }

    /**
     * Creates a formatted error string for display.
     * @return formatted error message.
     */
    public String getFormattedError() {
        return "Error " + errorCode + ": " + message + " | Details: " + details;
    }

    // Getters and Setters
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}