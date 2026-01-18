package com.system.dtos;

import java.time.LocalDateTime;

/**
 * Generic response DTO for API communication.
 */
public class ResponseDTO {
    private boolean success;
    private String message;
    private LocalDateTime timestamp;

    public ResponseDTO(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public static ResponseDTO createSuccessResponse(String message) {
        return new ResponseDTO(true, message);
    }

    public static ResponseDTO createErrorResponse(String message) {
        return new ResponseDTO(false, message);
    }
}