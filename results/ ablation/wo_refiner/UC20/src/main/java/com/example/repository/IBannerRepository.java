package com.example.repository;

import com.example.model.Banner;
import java.util.List;

/**
 * Banner repository interface.
 * Availability > 99.9% (REQ-016).
 */
public interface IBannerRepository {
    void save(Banner banner);
    List<Banner> findByRestPointId(String restPointId);
    int countByRestPointId(String restPointId);
    void logAvailability();
}