package com.example.command;

/**
 * Result of a command execution.
 */
public class CommandResult {
    private boolean success;
    private String message;
    private String bannerId;

    public CommandResult(boolean success, String message, String bannerId) {
        this.success = success;
        this.message = message;
        this.bannerId = bannerId;
    }

    public boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getBannerId() {
        return bannerId;
    }
}