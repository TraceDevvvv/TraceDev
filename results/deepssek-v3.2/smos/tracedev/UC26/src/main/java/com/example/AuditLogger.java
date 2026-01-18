package com.example;

/**
 * Interface for audit logging.
 * Added to satisfy quality requirement: "Administrator actions shall be logged for audit."
 */
public interface AuditLogger {
    void logAction(DeleteTeachingCommand command);
}