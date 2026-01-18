package com.example.repository;

import com.example.domain.RefreshmentPoint;
import com.example.domain.SearchCriteria;
import com.example.exception.DataAccessException;

import java.util.List;

/**
 * Repository interface for refreshment points.
 */
public interface IRefreshmentPointRepository {
    /**
     * Find all refreshment points.
     * @return list of all refreshment points
     */
    List<RefreshmentPoint> findAll();

    /**
     * Find refreshment points matching given criteria.
     * @param criteria search criteria
     * @return list of matching refreshment points
     * @throws DataAccessException if data access fails (e.g., connection error)
     */
    List<RefreshmentPoint> findByCriteria(SearchCriteria criteria) throws DataAccessException;
}