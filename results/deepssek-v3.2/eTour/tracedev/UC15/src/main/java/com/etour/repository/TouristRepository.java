package com.etour.repository;

import com.etour.domain.Tourist;

/**
 * Repository interface for Tourist entities.
 * Defines data access operations.
 */
public interface TouristRepository {
    Tourist findById(String id);
    void save(Tourist tourist);
}