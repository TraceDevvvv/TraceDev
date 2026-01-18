package com.example;

import java.util.List;

/**
 * Repository interface for Banner persistence operations.
 */
public interface BannerRepository {
    Banner findById(String id);
    Banner save(Banner banner);
    List<Banner> findByRefreshmentPointId(String pointId);
    List<RefreshmentPoint> findAllRefreshmentPoints(); // Added to support getting all refreshment points
}