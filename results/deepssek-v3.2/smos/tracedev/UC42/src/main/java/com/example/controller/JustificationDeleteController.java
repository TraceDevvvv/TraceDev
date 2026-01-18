package com.example.controller;

import com.example.justification.JustificationDTO;
import com.example.justification.JustificationService;
import com.example.audit.AuditLogger;
import com.example.session.SessionManager;
import com.example.session.UserSession;

/**
 * Controller for handling justification deletion requests.
 */
public class JustificationDeleteController {
    private JustificationService justificationService;
    private AuditLogger auditLogger;
    private SessionManager sessionManager;

    // Constructor for dependency injection
    public JustificationDeleteController(JustificationService justificationService,
                                         AuditLogger auditLogger,
                                         SessionManager sessionManager) {
        this.justificationService = justificationService;
        this.auditLogger = auditLogger;
        this.sessionManager = sessionManager;
    }

    /**
     * Processes a delete request.
     * Implements the main success flow and error handling from sequence diagram.
     * @param justificationId the ID of the justification to delete
     * @return true if deletion succeeded, false otherwise
     */
    public boolean processDeleteRequest(int justificationId) {
        // Entry condition: Check if administrator is logged in
        if (!sessionManager.isLoggedIn()) {
            // According to sequence diagram, throw AuthenticationException.
            // For simplicity, we throw a runtime exception.
            throw new RuntimeException("Administrator not logged in");
        }

        // Retrieve current user (admin)
        UserSession currentUser = sessionManager.getCurrentUser();
        if (currentUser == null) {
            throw new RuntimeException("No user session");
        }
        int adminId = currentUser.getUserId();

        // Optional: Verify justification exists before deletion (ViewJustificationDetails)
        JustificationDTO details = justificationService.getJustificationDetails(justificationId);
        if (details == null) {
            // Justification not found - throw exception as per sequence diagram
            throw new IllegalArgumentException("Justification not found");
        }

        // Pre-deletion validation (corresponds to note m6)
        // This is a placeholder; actual validation could be done in Service.
        System.out.println("[Controller] Pre-deletion validation for justification ID: " + justificationId);

        // Proceed with deletion
        boolean success = justificationService.deleteJustification(justificationId);
        if (success) {
            // Log the deletion in controller (post-deletion logging)
            logDeletion(justificationId, adminId);
            // Post-deletion logging in Controller (note m20)
            System.out.println("[Controller] Post-deletion logging completed.");
        }
        return success;
    }

    /**
     * Logs a deletion event (controller-level logging).
     * @param justificationId ID of the justification deleted
     * @param adminId ID of the administrator who performed the deletion
     */
    public void logDeletion(int justificationId, int adminId) {
        // This could log to a different audit trail (e.g., internal logs).
        // For simplicity, we just print.
        System.out.println(String.format("[Controller] Deletion logged: justificationId=%d, adminId=%d",
                justificationId, adminId));
    }

    /**
     * Cancels an ongoing deletion operation.
     * Added for operation interruption flow.
     * @param justificationId the ID of the justification being deleted
     * @return true if cancellation succeeded, false otherwise
     */
    public boolean cancelDeletion(int justificationId) {
        // Delegate to service layer
        return justificationService.cancelOperation(justificationId);
    }

    /**
     * Returns an error message to UI.
     * Corresponds to sequence diagram message m45.
     * @param justificationId the ID of the justification
     * @param errorMessage the error message
     * @return the error message string
     */
    public String returnErrorMessage(int justificationId, String errorMessage) {
        // In a real scenario, this might return a structured error object.
        // For simplicity, return the error message.
        return errorMessage;
    }
}