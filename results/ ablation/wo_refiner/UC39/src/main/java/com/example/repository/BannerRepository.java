package com.example.repository;

import com.example.model.Banner;
import java.util.List;

/**
 * Repository interface for Banner entities.
 * Added to satisfy requirement REQ-013
 */
public interface BannerRepository {
    /**
     * Finds all banners for a given point of restaurant.
     * @param pointOfRestaurantId the point of restaurant ID
     * @return list of banners
     */
    List<Banner> findAllByPointOfRestaurantId(String pointOfRestaurantId);

    /**
     * Finds a banner by ID.
     * @param id banner ID
     * @return the banner or null if not found
     */
    Banner findById(String id);

    /**
     * Saves a new banner.
     * @param banner the banner to save
     * @return saved banner
     */
    Banner save(Banner banner);

    /**
     * Updates the image URL of a banner.
     * @param bannerId banner ID
     * @param imageUrl new image URL
     * @return updated banner
     */
    Banner updateImage(String bannerId, String imageUrl);

    /**
     * Bookmarks a banner image.
     * Added to satisfy requirement REQ-013
     * @param bannerId banner ID
     * @param imageUrl image URL to bookmark
     */
    void bookmarkBannerImage(String bannerId, String imageUrl);
}