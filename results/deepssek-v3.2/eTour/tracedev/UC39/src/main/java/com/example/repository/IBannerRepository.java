package com.example.repository;

import com.example.domain.Banner;
import java.util.List;

/**
 * Repository interface for Banner persistence operations.
 */
public interface IBannerRepository {
    List<Banner> findByPointOfRestaurant(String pointOfRestaurantId);
    Banner save(Banner banner);
}