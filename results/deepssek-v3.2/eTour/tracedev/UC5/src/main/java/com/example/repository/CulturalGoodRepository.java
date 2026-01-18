package com.example.repository;

import com.example.exception.ConnectionException;
import com.example.model.CulturalGood;

/**
 * Repository interface for cultural good data access.
 * As per class diagram, it declares findById which may throw ConnectionException.
 */
public interface CulturalGoodRepository {
    /**
     * Finds a cultural good by its ID.
     * @param id the cultural good identifier
     * @return the CulturalGood object
     * @throws ConnectionException if a connection error occurs (e.g., database down)
     */
    CulturalGood findById(String id) throws ConnectionException;
}