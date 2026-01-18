package com.example.repository;

import com.example.domain.RefreshmentPoint;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for RefreshmentPoint persistence operations.
 */
public interface IRefreshmentPointRepository {
    List<RefreshmentPoint> findAllActive();
    Optional<RefreshmentPoint> findById(String id);
    RefreshmentPoint save(RefreshmentPoint point);
}