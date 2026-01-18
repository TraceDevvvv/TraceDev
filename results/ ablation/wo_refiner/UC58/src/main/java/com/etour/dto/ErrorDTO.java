package com.etour.dto;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for error information.
 * Used to transfer error details between layers (Requirement R9).
 */
public class ErrorDTO {
    private String code;
    private String message;
    private LocalDateTime timestamp;

    public ErrorDTO(String code, String message, LocalDateTime timestamp) {
        this.code = code;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Static factory method to create an ErrorDTO from an Exception.
     *
     * @param exception the exception
     * @return a new ErrorDTO
     */
    public static ErrorDTO fromException(Exception exception) {
        String code = "ERR-001";
        if (exception.getClass().getSimpleName().contains("Connection")) {
            code = "ERR-CONN";
        } else if (exception.getClass().getSimpleName().contains("DataAccess")) {
            code = "ERR-DATA";
        }
        return new ErrorDTO(code, exception.getMessage(), LocalDateTime.now());
    }
}