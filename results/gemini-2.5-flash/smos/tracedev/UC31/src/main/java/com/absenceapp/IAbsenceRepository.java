package com.absenceapp;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Interface for Absence data access operations.
 * <<Repository>> stereotype indicates it's part of the domain-driven design repository pattern.
 */
public interface IAbsenceRepository {
    /**
     * Finds an absence record by its unique identifier.
     *
     * @param id The ID of the absence to find.
     * @return An Optional containing the Absence if found, otherwise an empty Optional.
     */
    Optional<Absence> findById(String id);

    /**
     * Finds all absence records associated with a specific date.
     *
     * @param date The date to search for.
     * @return A list of Absence records found for the given date.
     */
    List<Absence> findByDate(Date date);

    /**
     * Saves an absence record. This method handles both creation (if ID is new)
     * and update (if ID exists) operations.
     * REQ-003: Persistence operation.
     *
     * @param absence The Absence object to be saved.
     * @return The saved Absence object, potentially with an updated ID if it was new.
     * @throws PersistenceException if the save operation fails.
     */
    Absence save(Absence absence) throws PersistenceException;

    /**
     * Deletes an absence record by its unique identifier.
     * REQ-003: Persistence operation.
     *
     * @param id The ID of the absence to delete.
     * @throws PersistenceException if the delete operation fails.
     */
    void delete(String id) throws PersistenceException;
}