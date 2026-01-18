package com.example.dto;

/**
 * DTO class representing a convention response data transfer object.
 * Corresponds to the ConventionResponseDTO class in the UML diagram.
 */
public class ConventionResponseDTO {
    private boolean success;
    private String message;
    private String requestId;

    /**
     * Default constructor.
     */
    public ConventionResponseDTO() {
    }

    /**
     * Gets the success status.
     * @return true if operation succeeded, false otherwise.
     */
    public boolean getSuccess() {
        return success;
    }

    /**
     * Sets the success status.
     * @param success The success status.
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * Gets the message.
     * @return Response message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message.
     * @param message The response message.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the request ID.
     * @return The request identifier.
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Sets the request ID.
     * @param requestId The request identifier.
     */
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}