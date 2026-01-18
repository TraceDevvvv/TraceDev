package com.banner.repository;

import com.banner.model.Banner;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Banner persistence operations.
 */
public interface BannerRepository {
    Banner save(Banner banner);
    int countByPointOfRestId(String pointOfRestId);
    Optional<Banner> findById(String id);
    List<Banner> findAllByPointOfRestId(String pointOfRestId);
}