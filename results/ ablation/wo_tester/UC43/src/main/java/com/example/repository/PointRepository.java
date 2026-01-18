package com.example.repository;

import com.example.model.PointOfRestaurant;
import java.util.Map;

/**
 * Repository interface for PointOfRestaurant entities.
 * Defines CRUD operations for point data.
 */
public interface PointRepository {
    /**
     * Finds a point by its ID (REQ-FLOW-002).
     * @param id The point ID.
     * @return The PointOfRestaurant, or null if not found.
     */
    PointOfRestaurant findById(String id);

    /**
     * Saves a new point.
     * @param point The point to save.
     */
    void save(PointOfRestaurant point);

    /**
     * Updates an existing point (REQ-FLOW-009).
     * @param id The point ID.
     * @param pointData The new data as a map.
     * @return true if update successful, false otherwise.
     */
    boolean update(String id, Map<String, String> pointData);
}