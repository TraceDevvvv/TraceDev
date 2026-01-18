package com.example;

/**
 * Repository interface for managing Convention entities.
 * Annotated with <<Repository>> stereotype.
 */
public interface ConventionRepository {

    /**
     * Finds a Convention by its Refreshment Point ID.
     * Modified to satisfy requirement R13: now throws ETOURServiceException.
     *
     * @param rpId The ID of the Refreshment Point associated with the Convention.
     * @return The Convention entity.
     * @throws ETOURServiceException if there is a service-related error during data retrieval.
     */
    Convention findByRefreshmentPointId(String rpId) throws ETOURServiceException;
}