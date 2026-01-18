package com.example.agencyapp.infrastructure;

import com.example.agencyapp.domain.ETOURConnectionException;
import com.example.agencyapp.domain.RefreshmentPoint; // Dependency as repository 'operates_on' RefreshmentPoint

/**
 * Interface for Refreshment Point data access operations.
 * It defines the contract for how refreshment points are persisted.
 */
public interface IRefreshmentPointRepository {

    /**
     * Attempts to delete a refreshment point by its ID.
     *
     * @param id The unique identifier of the refreshment point to delete.
     * @return true if the refreshment point was successfully deleted, false otherwise.
     * @throws ETOURConnectionException if there's an issue connecting to the underlying ETOUR system
     *                                  or a related external service during the delete operation.
     *                                  This indicates the repository might throw this exception (REQ-012).
     */
    boolean delete(String id) throws ETOURConnectionException;
}