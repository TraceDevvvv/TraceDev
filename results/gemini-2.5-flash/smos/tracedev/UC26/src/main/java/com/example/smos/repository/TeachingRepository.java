package com.example.smos.repository;

import com.example.smos.model.Teaching;
import com.example.smos.exception.SMOSConnectionException;

import java.util.List;

/**
 * Interface defining the contract for data access operations related to Teachings.
 * Corresponds to the 'TeachingRepository' interface in the UML Class Diagram.
 * It specifies methods for deleting and finding teachings.
 */
public interface TeachingRepository {

    /**
     * Deletes a teaching entry from the repository based on its ID.
     * @param teachingId The unique identifier of the teaching to delete.
     * @throws SMOSConnectionException if there is a problem connecting to the SMOS server (simulated DB).
     */
    void delete(String teachingId) throws SMOSConnectionException;

    /**
     * Retrieves all teaching entries from the repository.
     * @return A list of all teachings.
     * @throws SMOSConnectionException if there is a problem connecting to the SMOS server (simulated DB).
     */
    List<Teaching> findAll() throws SMOSConnectionException;
}