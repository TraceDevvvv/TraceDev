package com.example;

import java.time.LocalDateTime;

/**
 * Command object for deleting a teaching.
 * Contains teaching ID, administrator ID, and timestamp.
 */
public class DeleteTeachingCommand {
    private String teachingId;
    private String administratorId;
    private LocalDateTime timestamp;

    public DeleteTeachingCommand(String teachingId, String administratorId) {
        this.teachingId = teachingId;
        this.administratorId = administratorId;
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Executes the deletion via TeachingService.
     * This method is called by the controller.
     */
    public void execute() {
        // The actual deletion is delegated to TeachingService.
        // This method is kept simple; the service is injected in the controller.
        // In this implementation, execution logic is in the controller.
    }

    public String getTeachingId() {
        return teachingId;
    }

    public String getAdministratorId() {
        return administratorId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}