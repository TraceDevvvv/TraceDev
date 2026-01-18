package com.example.banner.infrastructure;

import com.example.banner.domain.Banner;
import java.util.List;

/**
 * Interface for data access operations related to {@link Banner} entities.
 * Defines methods for saving, finding, and querying banners.
 */
public interface IBannerRepository {
    /**
     * Saves a banner to the persistent storage.
     * If the banner already exists (based on its ID), it should be updated.
     * If it's a new banner, it should be inserted.
     *
     * @param banner The banner to save.
     * @return The saved banner, possibly with updated fields like ID or timestamps.
     * @throws ETOURConnectionException if there's a simulated connection issue during saving.
     */
    Banner save(Banner banner) throws ETOURConnectionException; // SD: m49

    /**
     * Finds a banner by its unique identifier.
     *
     * @param id The ID of the banner to find.
     * @return The found banner, or null if no banner with the given ID exists.
     */
    Banner findById(String id);

    /**
     * Finds all banners associated with a specific point of rest.
     *
     * @param pointOfRestId The ID of the point of rest.
     * @return A list of banners associated with the given point of rest ID.
     */
    List<Banner> findByPointOfRestId(String pointOfRestId);
}