package com.example.dto;

/**
 * Generic result DTO containing success flag and message.
 */
public class ResultDto {
    private final boolean success;
    private final String message;

    public ResultDto(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}