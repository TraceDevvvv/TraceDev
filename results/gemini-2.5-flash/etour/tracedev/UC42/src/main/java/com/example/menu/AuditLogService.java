package com.example.menu;

/**
 * Service for logging audit events, such as successful deletions and errors.
 */
public class AuditLogService {

    /**
     * Logs a successful deletion event.
     * REQ-R10: The command object is passed to provide context for logging.
     *
     * @param command The DeleteDailyMenuCommand that was successfully executed.
     */
    public void logDeletion(DeleteDailyMenuCommand command) {
        System.out.println("[AuditLogService] LOG: Successfully deleted daily menu for " + command.getDayOfWeek());
    }

    /**
     * REQ-R13: Logs an error event for traceability.
     *
     * @param error The exception that occurred.
     * @param context A string describing the context in which the error occurred.
     */
    public void logError(Exception error, String context) {
        System.err.println("[AuditLogService] ERROR LOG: An error occurred in context '" + context + "': " + error.getMessage());
        // In a real application, this would log to a file, monitoring system, etc.
    }
}