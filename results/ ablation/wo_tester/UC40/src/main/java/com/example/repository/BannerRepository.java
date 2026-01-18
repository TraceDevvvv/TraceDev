package com.example.repository;

import com.example.entity.Banner;
import java.util.List;

/**
 * Repository interface for Banner entities.
 * Defines the contract for banner data access operations.
 */
public interface BannerRepository {
    /**
     * Find all banners for a specific point of restaurant.
     * @param pointOfRestId the ID of the point of restaurant
     * @return list of banners for the given point of restaurant
     */
    List<Banner> findAllByPointOfRest(String pointOfRestId);

    /**
     * Find a banner by its ID.
     * @param bannerId the banner ID
     * @return the Banner entity, or null if not found
     */
    Banner findById(String bannerId);

    /**
     * Delete a banner by its ID.
     * @param bannerId the banner ID to delete
     * @return true if deletion was successful, false otherwise
     */
    boolean deleteById(String bannerId);
}