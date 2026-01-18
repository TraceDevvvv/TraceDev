package com.example.absencejustification.dataaccess;

import com.example.absencejustification.application.Justification;

/**
 * Interface for managing Justification persistence.
 * This adheres to the Repository Pattern, abstracting the data storage mechanism.
 */
public interface IJustificationRepository {
    /**
     * Saves a Justification entity to the persistent storage.
     * @param justification The Justification object to save.
     * @throws RuntimeException if a persistence error occurs (e.g., database connection issues).
     */
    void save(Justification justification);
}