package com.example.justification.application.port.out;

import com.example.justification.domain.model.Justification;

/**
 * Interface for the outbound port of the justification repository.
 * Defines the contract for persistence operations related to {@link Justification} entities.
 */
public interface IJustificationRepository {

    /**
     * Finds a justification by its unique identifier.
     *
     * @param id The unique identifier of the justification.
     * @return The {@link Justification} entity if found, otherwise null.
     */
    Justification findById(String id);

    /**
     * Saves or updates a justification in the repository.
     *
     * @param justification The {@link Justification} entity to save.
     */
    void save(Justification justification);

    /**
     * Deletes a justification by its unique identifier.
     *
     * @param id The unique identifier of the justification to delete.
     */
    void delete(String id);
}