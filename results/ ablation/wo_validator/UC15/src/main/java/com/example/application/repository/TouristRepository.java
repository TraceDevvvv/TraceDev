package com.example.application.repository;

import com.example.core.domain.Tourist;

/**
 * Repository interface for Tourist entity.
 * Defines the contract for data access operations.
 */
public interface TouristRepository {
    Tourist findById(Long id);
    Tourist save(Tourist tourist);
}