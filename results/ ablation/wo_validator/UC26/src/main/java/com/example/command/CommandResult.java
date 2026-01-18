package com.example.command;

import java.util.List;

/**
 * Result of a command execution.
 */
public class CommandResult {
    private boolean success;
    private String message;
    private List<String> errors;

    public CommandResult(boolean success, String message, List<String> errors) {
        this.success = success;
        this.message = message;
        this.errors = errors;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        return "CommandResult{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", errors=" + errors +
                '}';
    }
}