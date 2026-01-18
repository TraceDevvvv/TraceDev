package com.example.repository;

import com.example.model.Tourist;
import java.util.Optional;

/**
 * Repository interface for Tourist entities.
 */
public interface TouristRepository {
    Optional<Tourist> findById(String id);
}