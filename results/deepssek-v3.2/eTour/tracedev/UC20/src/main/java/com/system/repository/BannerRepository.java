package com.system.repository;

import com.system.Banner;
import java.util.List;

/**
 * Repository interface for Banner persistence operations.
 */
public interface BannerRepository {
    Banner save(Banner banner);
    List<Banner> findByPoint(String pointId);
    int countByPoint(String pointId);
    boolean existsById(String id);
}