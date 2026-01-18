package com.example.infrastructure;

import com.example.domain.Tourist;

/**
 * Repository interface for Tourist domain objects.
 */
public interface TouristRepository {
    Tourist findById(String id);
    void save(Tourist tourist);
}