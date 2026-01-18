package com.example.infrastructure;

import com.example.domain.ISpecification;
import com.example.domain.RefreshmentPoint;
import java.util.List;

/**
 * Repository interface for retrieving refreshment points using specifications.
 */
public interface IRefreshmentPointRepository {
    /**
     * Finds all refreshment points satisfying the given specification.
     * @param specification the search criteria
     * @return list of matching refreshment points
     */
    List<RefreshmentPoint> findAll(ISpecification<RefreshmentPoint> specification);

    /**
     * Refreshes the connection to the external server (Requirement 11).
     */
    void refreshConnection();
}