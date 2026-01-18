package com.example.justification;

/**
 * Repository interface for Justification persistence operations.
 */
public interface JustificationRepository {
    /**
     * Finds a justification by its ID.
     * @param justificationId the ID of the justification
     * @return Justification entity, or null if not found
     */
    Justification findById(int justificationId);

    /**
     * Deletes a justification.
     * @param justification the justification to delete
     */
    void delete(Justification justification);

    /**
     * Cancels an ongoing transaction (rollback).
     * Added for operation interruption flow.
     * @param justificationId the ID of the justification involved
     */
    void cancelTransaction(int justificationId);
}