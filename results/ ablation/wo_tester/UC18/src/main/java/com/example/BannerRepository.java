package com.example;

/**
 * Repository interface for Banner entities.
 * Defines methods to count, save, and rollback banners.
 */
public interface BannerRepository {
    /**
     * Counts the number of banners for a given refreshment point.
     * @param refreshmentPointId The ID of the refreshment point.
     * @return The count of banners.
     */
    int countByRefreshmentPointId(String refreshmentPointId);

    /**
     * Saves a banner.
     * @param banner The banner to save.
     */
    void save(Banner banner);

    /**
     * Rolls back any changes made, used for state recovery.
     */
    void rollback();
}