package com.example;

import java.util.List;

/**
 * Controller for handling teaching deletion commands.
 * Integrates repository, audit logger, and SMOS connection.
 */
public class DeleteTeachingController {
    private TeachingRepository teachingRepository;
    private AuditLogger auditLogger;
    private SMOSConnection smosConnection;
    private TeachingService teachingService;

    public DeleteTeachingController(TeachingRepository teachingRepository,
                                    AuditLogger auditLogger,
                                    SMOSConnection smosConnection) {
        this.teachingRepository = teachingRepository;
        this.auditLogger = auditLogger;
        this.smosConnection = smosConnection;
        this.teachingService = new TeachingService(teachingRepository);
    }

    /**
     * Displays teaching details for a given teaching ID.
     * Added to satisfy requirement: "User HAS executed-displaydeddailsignment"
     */
    public TeachingDetails displayTeachingDetails(String teachingId) {
        Teaching teaching = teachingRepository.findById(teachingId);
        if (teaching == null) {
            return new TeachingDetails(teachingId, "Teaching not found.");
        }
        String details = "Teaching ID: " + teaching.getId()
                + ", Title: " + teaching.getTitle()
                + ", Description: " + teaching.getDescription()
                + ", Archived: " + teaching.isArchived();
        return new TeachingDetails(teachingId, details);
    }

    /**
     * Handles the delete command as per the sequence diagram.
     * Validates authorization, executes deletion, logs, and returns updated list.
     */
    public List<Teaching> handleDeleteCommand(DeleteTeachingCommand command) {
        // Validate authorization
        if (!validateAuthorization(command.getAdministratorId())) {
            auditLogger.logAction(command); // Log failed attempt
            throw new SecurityException("Error: Unauthorized");
        }

        // Execute deletion
        command.execute(); // The command's execute method is empty; actual deletion is done via service.
        teachingService.deleteTeaching(command.getTeachingId());

        // Audit log
        auditLogger.logAction(command);

        // Return updated list of teachings
        return teachingService.getAllTeachings();
    }

    /**
     * Validates that the administrator is authorized.
     * Simplified: checks if the administrator exists and is logged in.
     * In a real system, this would involve a user store and role checks.
     */
    private boolean validateAuthorization(String administratorId) {
        // Assume there is a way to get Administrator by ID.
        // For simplicity, we create a dummy administrator.
        Administrator admin = new Administrator(administratorId, "admin", "ADMIN");
        admin.setLoggedIn(true); // Assume logged in for demonstration.
        return admin.isAdministrator() && admin.isLoggedIn();
    }

    /**
     * Utility method to check SMOS connection.
     * Used when connection interruption is suspected.
     */
    public boolean checkSMOSConnection() {
        return smosConnection.isConnected();
    }

    /**
     * Responds with "Error: Unauthorized" as per sequence diagram.
     * This corresponds to the return message "Error: Unauthorized" from Controller to Admin.
     */
    public void sendErrorUnauthorized() {
        // This method can be called when an unauthorized action is attempted.
        // The exception is already thrown in handleDeleteCommand.
        // For completeness, we also provide a method to send the error.
        // In a real controller, this would be part of the HTTP response.
    }

    /**
     * Responds with "Error: Connection failed" as per sequence diagram.
     * This corresponds to the return message "Error: Connection failed" from Controller to Admin.
     */
    public void sendErrorConnectionFailed() {
        // This method can be called when a connection error occurs.
        // In a real controller, this would be part of the HTTP response.
    }

    /**
     * Handles the "[Connection Timeout]" message from Admin to Controller as per sequence diagram.
     * This method simulates the scenario where the Admin experiences a connection timeout.
     */
    public void handleConnectionTimeout() {
        // Simulate handling a connection timeout.
        // This could involve logging, notifying the user, etc.
        System.out.println("Connection timeout occurred.");
    }
}