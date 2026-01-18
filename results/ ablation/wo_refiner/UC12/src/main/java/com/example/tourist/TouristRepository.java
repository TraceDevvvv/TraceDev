package com.example.tourist;

/**
 * Repository interface for Tourist entity.
 * Provides methods to find and update tourist data.
 */
public interface TouristRepository {
    /**
     * Finds a tourist by ID.
     * @param id the tourist ID
     * @return the Tourist entity
     */
    Tourist findById(String id);

    /**
     * Updates tourist data (renamed from uploadData per REQ-007).
     * @param tourist the tourist entity to update
     * @return true if successful, false otherwise
     */
    boolean updateTouristData(Tourist tourist);

    /**
     * Uploads tourist data (as defined in class diagram).
     * @param tourist the tourist entity to upload
     * @return true if successful, false otherwise
     */
    boolean uploadData(Tourist tourist);
}