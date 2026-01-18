package com.example.agencyapp.application;

import com.example.agencyapp.domain.ETOURConnectionException;
import com.example.agencyapp.domain.RefreshmentPoint; // Dependency to RefreshmentPoint (REQ-009)
import com.example.agencyapp.infrastructure.IRefreshmentPointRepository;

/**
 * Service class responsible for orchestrating the deletion of Refreshment Points.
 * It acts as an application layer component, coordinating between the UI and the infrastructure layer.
 */
public class RefreshmentPointDeletionService {
    // Relationship: RefreshmentPointDeletionService uses IRefreshmentPointRepository
    private final IRefreshmentPointRepository refreshmentPointRepository;

    /**
     * Constructs a RefreshmentPointDeletionService with a given repository.
     * @param repo The repository implementation to use for refreshment point persistence.
     */
    public RefreshmentPointDeletionService(IRefreshmentPointRepository repo) {
        this.refreshmentPointRepository = repo;
    }

    /**
     * Orchestrates the deletion of a refreshment point by its ID.
     * This method translates potential infrastructure-level exceptions (like ETOURConnectionException)
     * into a boolean result, aligning with the sequence diagram's outcome handling.
     *
     * @param id The unique identifier of the refreshment point to delete.
     * @return true if the deletion was successful, false otherwise (e.g., item not found,
     *         database error, or ETOUR connection issue).
     */
    public boolean deleteRefreshmentPoint(String id) {
        System.out.println(" Service: Received request to delete refreshment point with ID: " + id);
        try {
            // Call the infrastructure layer to perform the actual deletion.
            // The sequence diagram indicates the service returns boolean from repository.
            return refreshmentPointRepository.delete(id);
        } catch (ETOURConnectionException e) {
            // Catch ETOURConnectionException (as indicated by RefreshmentPointDeletionService --|> ETOURConnectionException)
            // and translate it into a 'false' return, matching the sequence diagram's error path
            // "Deletion failed (e.g., ETOUR connection interrupted)" which leads to 'return false'.
            System.err.println(" Service: ETOUR connection error during deletion: " + e.getMessage());
            return false; // Deletion failed due to connection issue
        }
    }
}