package com.example.repository;

import com.example.model.PointOfRest;

import java.util.List;
import java.util.Map;

/**
 * Repository interface for PointOfRest data access.
 */
public interface PointOfRestRepository {
    List<PointOfRest> findAll();
    List<PointOfRest> findByCriteria(Map<String, Object> criteria);
}