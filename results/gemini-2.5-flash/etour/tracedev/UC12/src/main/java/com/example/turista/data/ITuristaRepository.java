package com.example.turista.data;

import com.example.turista.domain.Turista;

/**
 * Interface for Turista data access operations.
 * Defines the contract for how Turista entities are retrieved from persistence.
 */
public interface ITuristaRepository {
    /**
     * Finds a Turista by its unique identifier.
     * @param turistaId The ID of the turista.
     * @return The Turista object if found.
     * @throws TuristaDataAccessException if data cannot be retrieved (e.g., connection error, data not found).
     * note right of ITuristaRepository::findById: Retrieves Turista data from persistence (R8)
     */
    Turista findById(String turistaId) throws TuristaDataAccessException;
}