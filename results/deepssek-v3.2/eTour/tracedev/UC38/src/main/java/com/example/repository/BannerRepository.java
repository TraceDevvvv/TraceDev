package com.example.repository;

import com.example.entity.Banner;

/**
 * Repository interface for Banner entity operations.
 */
public interface BannerRepository {
    boolean save(Banner banner) throws DataAccessException;
    int countByPointId(String pointId);
    Banner findById(String id);
}