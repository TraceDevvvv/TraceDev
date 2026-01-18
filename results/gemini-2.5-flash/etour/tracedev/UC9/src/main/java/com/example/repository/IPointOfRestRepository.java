package com.example.repository;

import com.example.exceptions.DataAccessException;
import com.example.model.PointOfRest;
import com.example.model.SearchCriteria;

import java.util.List;

/**
 * Interface for the PointOfRest repository, defining data access operations.
 * Part of the infrastructure layer.
 */
public interface IPointOfRestRepository {
    /**
     * Finds points of rest based on the given search criteria.
     *
     * @param criteria The search criteria.
     * @return A list of PointOfRest entities.
     * @throws DataAccessException if an error occurs during data retrieval.
     */
    List<PointOfRest> findByCriteria(SearchCriteria criteria) throws DataAccessException;
}