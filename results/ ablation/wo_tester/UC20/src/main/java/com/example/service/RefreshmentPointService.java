package com.example.service;

import com.example.entity.RefreshmentPoint;
import java.util.List;

/**
 * Interface for refreshment point operations.
 */
public interface RefreshmentPointService {
    /**
     * Searches for all refreshment points.
     */
    List<RefreshmentPoint> searchRefreshmentPoints();
    
    /**
     * Gets a refreshment point by ID.
     */
    RefreshmentPoint getRefreshmentPointById(String pointId);
    
    /**
     * Checks if a point has reached its banner limit.
     */
    boolean hasReachedBannerLimit(String pointId);
    
    /**
     * Increments the banner count for a point.
     */
    void incrementBannerCount(String pointId);
    
    /**
     * Resets selection for a point.
     */
    void resetSelection(String pointId);
}