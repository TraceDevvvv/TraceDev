package com.example.data;

import com.example.model.PointOfRestEntity;

/**
 * Interface defining the contract for data sources that can fetch PointOfRestEntity.
 */
public interface IDataSource {
    /**
     * Fetches a PointOfRestEntity from the data source by its ID.
     * @param id the unique identifier of the point of rest
     * @return the PointOfRestEntity, or null if not found
     * @throws DataAccessException if connection or data retrieval fails
     */
    PointOfRestEntity fetchPointOfRest(String id) throws DataAccessException;
}