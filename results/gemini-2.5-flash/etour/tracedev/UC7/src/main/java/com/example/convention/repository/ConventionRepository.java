package com.example.convention.repository;

import com.example.convention.model.Convention;

/**
 * Interface for managing Convention persistence.
 * This interface defines the contract for operations related to Convention objects.
 */
public interface ConventionRepository {
    /**
     * Finds a Convention by its unique identifier.
     *
     * @param id The ID of the Convention to find.
     * @return The Convention object if found, null otherwise.
     */
    Convention findById(String id);

    /**
     * Saves a Convention object to the persistent storage.
     * This method handles both creating new Conventions and updating existing ones.
     *
     * @param convention The Convention object to save.
     */
    void save(Convention convention);
}