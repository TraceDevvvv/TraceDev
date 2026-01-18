package com.example.tourist;

/**
 * Interface for data sources that can fetch tourist data.
 */
public interface DataSource {
    /**
     * Fetches tourist data by ID.
     * @param id the tourist ID
     * @return the Tourist entity, or null if not found
     */
    Tourist fetchTouristData(String id);
}