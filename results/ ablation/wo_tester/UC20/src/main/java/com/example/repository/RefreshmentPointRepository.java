package com.example.repository;

import com.example.entity.RefreshmentPoint;
import java.util.List;
import java.util.Optional;

/**
 * Interface for refreshment point repository.
 */
public interface RefreshmentPointRepository {
    /**
     * Finds all refreshment points.
     */
    List<RefreshmentPoint> findAll();
    
    /**
     * Finds a refreshment point by ID.
     */
    Optional<RefreshmentPoint> findById(String pointId);
}