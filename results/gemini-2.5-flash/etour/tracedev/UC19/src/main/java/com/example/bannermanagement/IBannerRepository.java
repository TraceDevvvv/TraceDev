package com.example.bannermanagement;

import java.util.List;

/**
 * Interface for data access operations related to Banner entities.
 * Includes methods to find banners by refreshment point ID and delete banners.
 * All methods may throw ETOURConnectionException as per REQ-003.
 */
public interface IBannerRepository {
    /**
     * Retrieves all Banner entities associated with a specific refreshment point.
     * @param refreshmentPointId The ID of the refreshment point.
     * @return A list of Banner objects associated with the given refreshment point.
     * @throws ETOURConnectionException if there's an issue connecting to the data source.
     */
    List<Banner> findByRefreshmentPointId(String refreshmentPointId) throws ETOURConnectionException;

    /**
     * Deletes a Banner entity by its unique identifier.
     * This operation is marked as <<Irreversible>> as per quality requirement R015.
     * @param bannerId The unique ID of the banner to delete.
     * @throws ETOURConnectionException if there's an issue connecting to the data source.
     */
    void delete(String bannerId) throws ETOURConnectionException;
}