package com.example.presentation;

/**
 * Data Transfer Object for outgoing Convention response data.
 * Used by the Presentation Layer to send data back to the client.
 */
public class ConventionResponseDTO {
    private String conventionId;
    private String status;
    private String message;

    // Default constructor for (de)serialization frameworks
    public ConventionResponseDTO() {
    }

    /**
     * Constructs a ConventionResponseDTO.
     *
     * @param conventionId The ID of the convention.
     * @param status The status of the operation or convention.
     * @param message A descriptive message about the operation result.
     */
    public ConventionResponseDTO(String conventionId, String status, String message) {
        this.conventionId = conventionId;
        this.status = status;
        this.message = message;
    }

    public String getConventionId() {
        return conventionId;
    }

    public void setConventionId(String conventionId) {
        this.conventionId = conventionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ConventionResponseDTO{" +
               "conventionId='" + conventionId + '\'' +
               ", status='" + status + '\'' +
               ", message='" + message + '\'' +
               '}';
    }
}