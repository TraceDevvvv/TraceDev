package com.example.repository;

import com.example.banner.Banner;
import java.util.List;

/**
 * Interface for managing Banner persistence.
 */
public interface BannerRepository {
    
    /**
     * Finds a banner by its ID.
     * @param bannerId the banner ID
     * @return the Banner object, or null if not found
     */
    Banner findBannerById(int bannerId);
    
    /**
     * Finds all banners for a given refreshment point.
     * @param pointId the refreshment point ID
     * @return list of banners for that point
     */
    List<Banner> findBannersByRefreshmentPoint(int pointId);
    
    /**
     * Saves (inserts or updates) a banner.
     * @param banner the banner to save
     */
    void save(Banner banner);
}