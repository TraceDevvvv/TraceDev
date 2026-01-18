package com.example;

/**
 * Simple audit logger that prints to console.
 */
public class ConsoleAuditLogger implements AuditLogger {
    @Override
    public void logAction(DeleteTeachingCommand command) {
        System.out.println("[AUDIT] Action: DeleteTeachingCommand"
                + ", Teaching ID: " + command.getTeachingId()
                + ", Administrator ID: " + command.getAdministratorId()
                + ", Timestamp: " + command.getTimestamp());
    }
}