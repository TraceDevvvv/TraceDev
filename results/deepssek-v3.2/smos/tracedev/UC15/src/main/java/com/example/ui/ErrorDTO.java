package com.example.ui;

/**
 * Data Transfer Object for error details.
 * Used to transfer error information between layers.
 */
public class ErrorDTO {
    public String message;
    public int code;

    /**
     * Constructor for ErrorDTO.
     * @param message The error message.
     * @param code The error code.
     */
    public ErrorDTO(String message, int code) {
        this.message = message;
        this.code = code;
    }
}