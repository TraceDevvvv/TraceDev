package com.example.refreshmentpoint;

/**
 * Interface for Refreshment Point data access operations.
 * Defines the contract for interacting with refreshment point persistence.
 */
public interface IRefreshmentPointRepository {
    /**
     * Finds a RefreshmentPoint by its ID.
     *
     * @param id The ID of the refreshment point.
     * @return The RefreshmentPoint found, or null if not found.
     * @throws ETOURConnectionException If there is a connection issue with the ETOUR server.
     */
    RefreshmentPoint findById(String id) throws ETOURConnectionException;

    /**
     * Saves a new RefreshmentPoint.
     *
     * @param point The RefreshmentPoint to save.
     * @throws ETOURConnectionException If there is a connection issue with the ETOUR server.
     */
    void save(RefreshmentPoint point) throws ETOURConnectionException;

    /**
     * Updates an existing RefreshmentPoint.
     *
     * @param point The RefreshmentPoint with updated details.
     * @throws ETOURConnectionException If there is a connection issue with the ETOUR server.
     */
    void update(RefreshmentPoint point) throws ETOURConnectionException;
}