package com.example.domain;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Banner persistence operations.
 */
public interface BannerRepository {
    /**
     * Finds all banners associated with a specific point of rest.
     */
    List<Banner> findByPointOfRest(String pointOfRestId);

    /**
     * Finds a banner by its ID.
     */
    Optional<Banner> findById(String bannerId);

    /**
     * Deletes a banner permanently (hard delete).
     */
    void delete(Banner banner);

    /**
     * Saves or updates a banner.
     */
    Banner save(Banner banner);
}