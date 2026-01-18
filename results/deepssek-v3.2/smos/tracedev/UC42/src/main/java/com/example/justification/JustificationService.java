package com.example.justification;

/**
 * Service interface for Justification operations.
 */
public interface JustificationService {
    /**
     * Deletes a justification by its ID.
     * @param justificationId the ID of the justification to delete
     * @return true if deletion succeeded, false otherwise
     */
    boolean deleteJustification(int justificationId);

    /**
     * Retrieves justification details.
     * @param justificationId the ID of the justification
     * @return JustificationDTO containing details
     */
    JustificationDTO getJustificationDetails(int justificationId);

    /**
     * Cancels an ongoing deletion operation.
     * Added for operation interruption flow.
     * @param justificationId the ID of the justification being deleted
     * @return true if cancellation succeeded, false otherwise
     */
    boolean cancelOperation(int justificationId);
}