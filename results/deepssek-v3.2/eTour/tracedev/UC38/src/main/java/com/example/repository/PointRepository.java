package com.example.repository;

import com.example.entity.PointOfRestaurant;

/**
 * Repository interface for PointOfRestaurant entity operations.
 */
public interface PointRepository {
    PointOfRestaurant findByPointId(String pointId);
    boolean update(PointOfRestaurant point);
}