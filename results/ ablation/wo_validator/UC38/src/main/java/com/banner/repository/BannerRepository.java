package com.banner.repository;

import com.banner.model.Banner;
import java.util.List;

/**
 * Repository interface for Banner entities.
 */
public interface BannerRepository {
    void save(Banner banner);
    List<Banner> findByPointOfRestaurantId(String pointOfRestaurantId);
    int countByPointOfRestaurantId(String pointOfRestaurantId);
}