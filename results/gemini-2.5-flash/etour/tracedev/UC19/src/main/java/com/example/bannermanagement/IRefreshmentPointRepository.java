package com.example.bannermanagement;

import java.util.List;

/**
 * Interface for data access operations related to RefreshmentPoint entities.
 * Includes methods to find all refreshment points and find a refreshment point by its ID.
 * All methods may throw ETOURConnectionException as per REQ-003.
 */
public interface IRefreshmentPointRepository {
    /**
     * Retrieves all available RefreshmentPoint entities.
     * @return A list of all RefreshmentPoint objects.
     * @throws ETOURConnectionException if there's an issue connecting to the data source.
     */
    List<RefreshmentPoint> findAll() throws ETOURConnectionException;

    /**
     * Finds a RefreshmentPoint by its unique identifier.
     * @param id The unique ID of the refreshment point to find.
     * @return The RefreshmentPoint object if found, null otherwise.
     * @throws ETOURConnectionException if there's an issue connecting to the data source.
     */
    RefreshmentPoint findById(String id) throws ETOURConnectionException;
}