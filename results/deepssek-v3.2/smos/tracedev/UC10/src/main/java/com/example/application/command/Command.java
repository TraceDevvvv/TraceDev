package com.example.application.command;

import java.time.LocalDateTime;

/**
 * Abstract base class for commands.
 */
public abstract class Command {
    private String userId;
    private String roleId;
    private LocalDateTime timestamp;

    protected Command(String userId, String roleId) {
        this.userId = userId;
        this.roleId = roleId;
        this.timestamp = LocalDateTime.now();
    }

    public String getUserId() {
        return userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}