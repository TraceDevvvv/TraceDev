package com.example.justification.repository;

import com.example.justification.domain.Justification;

/**
 * Interface for the Justification Repository.
 * Defines the contract for data access operations related to Justification entities.
 * Corresponds to the IJustificationRepository interface in the Class Diagram.
 */
public interface IJustificationRepository {

    /**
     * Retrieves a Justification by its unique identifier.
     *
     * @param id The ID of the Justification to retrieve.
     * @return The Justification object if found, or null if not found.
     */
    Justification getById(String id);

    /**
     * Removes a Justification from the persistent storage.
     * This typically means a physical deletion from the database.
     *
     * @param justification The Justification object to remove.
     */
    void remove(Justification justification);
}