package com.system.repository;

import com.system.RefreshmentPoint;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for RefreshmentPoint persistence operations.
 */
public interface RefreshmentPointRepository {
    Optional<RefreshmentPoint> findById(String id);
    RefreshmentPoint save(RefreshmentPoint point);
    List<RefreshmentPoint> findAll();
}