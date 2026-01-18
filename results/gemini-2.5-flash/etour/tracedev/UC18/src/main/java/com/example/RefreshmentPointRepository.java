package com.example;

/**
 * Repository interface for managing RefreshmentPoint entities.
 * Annotated with <<Repository>> stereotype.
 */
public interface RefreshmentPointRepository {

    /**
     * Finds a RefreshmentPoint by its ID.
     * Modified to satisfy requirement R13: now throws ETOURServiceException.
     *
     * @param rpId The ID of the RefreshmentPoint.
     * @return The RefreshmentPoint entity.
     * @throws ETOURServiceException if there is a service-related error during data retrieval.
     */
    RefreshmentPoint findById(String rpId) throws ETOURServiceException;
}