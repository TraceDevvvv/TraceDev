package com.example.command;

/**
 * Result of command validation (REQ-009).
 */
public class CommandValidationResult {
    private boolean valid;
    private String message;

    public CommandValidationResult(boolean valid, String message) {
        this.valid = valid;
        this.message = message;
    }

    public boolean isValid() {
        return valid;
    }

    public String getMessage() {
        return message;
    }
}