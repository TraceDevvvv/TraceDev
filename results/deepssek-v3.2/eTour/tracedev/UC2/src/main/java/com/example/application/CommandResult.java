package com.example.application;

import java.util.UUID;

/**
 * Result of a command execution.
 */
public class CommandResult {
    private boolean success;
    private String message;
    private UUID culturalGoodId;
    
    public CommandResult(boolean success, String message, UUID culturalGoodId) {
        this.success = success;
        this.message = message;
        this.culturalGoodId = culturalGoodId;
    }
    
    public CommandResult(boolean success, String message) {
        this(success, message, null);
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public String getMessage() {
        return message;
    }
    
    public UUID getCulturalGoodId() {
        return culturalGoodId;
    }
}