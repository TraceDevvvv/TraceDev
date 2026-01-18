package com.etour.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Standard API response wrapper for tourist-related operations.
 * Includes status, message, timestamp, and optional data payload.
 * Used for all tourist API responses for consistency.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TouristResponse {
    
    /** Response status (success, error, warning) */
    private String status;
    
    /** Response message for the user */
    private String message;
    
    /** HTTP status code */
    private int httpStatus;
    
    /** Timestamp of the response */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    
    /** The actual data payload (e.g., TouristDTO) */
    private Object data;
    
    /** Error details if status is error */
    private Object errorDetails;
    
    /** Request ID for tracing */
    private String requestId;
    
    /**
     * Creates a successful response with data.
     * 
     * @param message Success message
     * @param data The data payload
     * @return TouristResponse with success status
     */
    public static TouristResponse success(String message, Object data) {
        return TouristResponse.builder()
                .status("success")
                .message(message)
                .httpStatus(200)
                .timestamp(LocalDateTime.now())
                .data(data)
                .build();
    }
    
    /**
     * Creates a successful response for tourist update.
     * 
     * @param touristDTO Updated tourist data
     * @return TouristResponse with update success message
     */
    public static TouristResponse updateSuccess(TouristDTO touristDTO) {
        return success("Tourist account updated successfully", touristDTO);
    }
    
    /**
     * Creates a successful response for tourist retrieval.
     * 
     * @param touristDTO Retrieved tourist data
     * @return TouristResponse with retrieval success message
     */
    public static TouristResponse retrievalSuccess(TouristDTO touristDTO) {
        return success("Tourist account retrieved successfully", touristDTO);
    }
    
    /**
     * Creates an error response.
     * 
     * @param message Error message
     * @param httpStatus HTTP status code
     * @param errorDetails Detailed error information
     * @return TouristResponse with error status
     */
    public static TouristResponse error(String message, int httpStatus, Object errorDetails) {
        return TouristResponse.builder()
                .status("error")
                .message(message)
                .httpStatus(httpStatus)
                .timestamp(LocalDateTime.now())
                .errorDetails(errorDetails)
                .build();
    }
    
    /**
     * Creates a validation error response.
     * 
     * @param validationErrors Validation error details
     * @return TouristResponse with validation error status
     */
    public static TouristResponse validationError(Object validationErrors) {
        return error("Validation failed", 400, validationErrors);
    }
    
    /**
     * Creates a not found error response.
     * 
     * @param message Specific not found message
     * @return TouristResponse with 404 status
     */
    public static TouristResponse notFound(String message) {
        return error(message, 404, null);
    }
    
    /**
     * Creates a server error response.
     * 
     * @param message Server error message
     * @return TouristResponse with 500 status
     */
    public static TouristResponse serverError(String message) {
        return error(message, 500, null);
    }
    
    /**
     * Creates a confirmation required response.
     * Used when user confirmation is needed before proceeding.
     * 
     * @param message Confirmation message
     * @param confirmationData Data to confirm
     * @return TouristResponse with confirmation status
     */
    public static TouristResponse confirmationRequired(String message, Object confirmationData) {
        return TouristResponse.builder()
                .status("confirmation_required")
                .message(message)
                .httpStatus(200)
                .timestamp(LocalDateTime.now())
                .data(confirmationData)
                .build();
    }
    
    /**
     * Checks if the response indicates success.
     * 
     * @return true if status is "success", false otherwise
     */
    public boolean isSuccess() {
        return "success".equals(status);
    }
    
    /**
     * Checks if the response requires confirmation.
     * 
     * @return true if status is "confirmation_required", false otherwise
     */
    public boolean requiresConfirmation() {
        return "confirmation_required".equals(status);
    }
    
    /**
     * Gets a summary string for logging.
     * 
     * @return Summary string
     */
    public String getSummary() {
        return String.format("TouristResponse[status=%s, httpStatus=%d, message=%s]", 
                status, httpStatus, message);
    }
}