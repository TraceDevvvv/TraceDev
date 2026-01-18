package com.example.infrastructure;

import com.example.domain.Banner;
import java.util.List;

/**
 * Repository interface for Banner persistence.
 */
public interface BannerRepository {
    Banner findBannerByIdAndPointOfRestaurantId(String bannerId, String pointOfRestaurantId);
    List<Banner> findBannersByPointOfRestaurantId(String pointOfRestaurantId); // R-2
    void save(Banner banner) throws ConnectionInterruptedException;
}