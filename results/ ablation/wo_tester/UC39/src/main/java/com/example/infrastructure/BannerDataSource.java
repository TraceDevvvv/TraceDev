package com.example.infrastructure;

import com.example.domain.Banner;
import java.util.List;

/**
 * Data source interface for Banner persistence.
 */
public interface BannerDataSource {
    Banner findByIdAndPointOfRestaurantId(String bannerId, String pointOfRestaurantId);
    List<Banner> findByPointOfRestaurantId(String pointOfRestaurantId); // R-2
    void update(Banner banner) throws ConnectionInterruptedException;
}