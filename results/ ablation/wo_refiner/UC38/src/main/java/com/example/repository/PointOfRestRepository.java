package com.example.repository;

import com.example.domain.PointOfRestaurant;
import java.util.Optional;

/**
 * Repository interface for PointOfRestaurant persistence.
 */
public interface PointOfRestRepository {
    Optional<PointOfRestaurant> findById(String id);
}