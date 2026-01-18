package com.example.infrastructure;

import com.example.domain.Banner;

/**
 * Interface for Data Access operations related to Banner entities.
 * Part of the Infrastructure Layer.
 */
public interface IBannerRepository {
    /**
     * Finds a Banner by its unique identifier.
     * @param id The ID of the banner.
     * @return The Banner if found, null otherwise.
     */
    Banner findById(String id);

    /**
     * Saves or updates a Banner entity.
     * @param banner The Banner object to save.
     */
    void save(Banner banner);
}