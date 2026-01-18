package com.example.audit;

import java.util.Date;

/**
 * Logs audit events to an external system.
 */
public class AuditLogger {
    private String logFile;

    // Constructor
    public AuditLogger(String logFile) {
        this.logFile = logFile;
    }

    /**
     * Log a deletion event.
     * @param justificationId ID of the justification deleted
     * @param adminId ID of the administrator who performed the deletion
     * @param timestamp time of deletion
     */
    public void logDeletion(int justificationId, int adminId, Date timestamp) {
        // In a real system, this would write to a file, send to an external service, etc.
        // For simplicity, we just print to console.
        System.out.println(String.format("[AuditLogger] Deletion logged: justificationId=%d, adminId=%d, timestamp=%s, logFile=%s",
                justificationId, adminId, timestamp, logFile));
    }

    /**
     * Log the deletion before returning (note m16).
     * This method is called by Service before returning success.
     * @param justificationId ID of the justification
     * @param adminId ID of the administrator
     */
    public void logDeletionBeforeReturn(int justificationId, int adminId) {
        System.out.println(String.format("[AuditLogger] Logging deletion before returning: justificationId=%d, adminId=%d",
                justificationId, adminId));
        // Could call logDeletion with current timestamp.
        logDeletion(justificationId, adminId, new Date());
    }
}