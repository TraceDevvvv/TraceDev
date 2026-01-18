package com.example.repository;

import com.example.dto.SearchTouristDTO;
import com.example.domain.TouristAccount;
import com.example.exception.ConnectionException;
import java.util.List;

/**
 * Repository interface for TouristAccount data access.
 */
public interface TouristAccountRepository {
    /**
     * Finds all tourist accounts matching the given criteria.
     * @param criteria Search criteria DTO.
     * @return List of TouristAccount entities.
     * @throws ConnectionException If connection to the external server fails.
     */
    List<TouristAccount> findAllByCriteria(SearchTouristDTO criteria) throws ConnectionException;

    /**
     * Finds all tourist accounts with retry logic on connection failure.
     * @param criteria Search criteria DTO.
     * @param maxRetries Maximum number of retry attempts.
     * @return List of TouristAccount entities.
     * @throws ConnectionException If connection fails after all retries.
     */
    List<TouristAccount> findAllByCriteriaWithRetry(SearchTouristDTO criteria, int maxRetries) throws ConnectionException;
}