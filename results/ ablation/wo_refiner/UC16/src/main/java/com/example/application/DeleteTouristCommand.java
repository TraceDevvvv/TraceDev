package com.example.application;

import java.time.LocalDateTime;

/**
 * Application Layer - Encapsulates delete request (Command Pattern).
 */
public class DeleteTouristCommand {
    public int touristId;
    public int operatorId;
    public LocalDateTime timestamp;

    public DeleteTouristCommand(int touristId, int operatorId) {
        this.touristId = touristId;
        this.operatorId = operatorId;
        this.timestamp = LocalDateTime.now();
    }

    public int getTouristId() {
        return touristId;
    }

    public void setTouristId(int touristId) {
        this.touristId = touristId;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Executes the command (delegated to handler).
     * This method is kept for pattern completeness; actual execution is in handler.
     */
    public com.example.dto.CommandResult execute() {
        // This would normally invoke the handler; here we return a placeholder.
        // In a full implementation, a command bus or direct handler invocation would be used.
        return new com.example.dto.CommandResult(false, "Command not directly executable", touristId);
    }

    /**
     * Undo operation (stub for command pattern).
     */
    public void undo() {
        // In a real implementation, this would revert the delete.
        System.out.println("Undo delete tourist command (stub)");
    }
}