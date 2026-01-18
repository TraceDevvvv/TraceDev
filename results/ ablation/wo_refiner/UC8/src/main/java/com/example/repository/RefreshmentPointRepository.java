
package com.example.repository;

import com.example.model.RefreshmentPoint;

import java.util.Optional;

/**
 * Repository interface for RefreshmentPoint.
 */
public interface RefreshmentPointRepository {
    Optional<RefreshmentPoint> findById(String id);
    RefreshmentPoint save(RefreshmentPoint point);
    RefreshmentPoint update(RefreshmentPoint point);
}
