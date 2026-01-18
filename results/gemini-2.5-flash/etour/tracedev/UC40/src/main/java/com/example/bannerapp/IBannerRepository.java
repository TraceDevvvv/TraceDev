package com.example.bannerapp;

import java.util.List;

/**
 * Interface defining operations for managing Banner data persistence.
 */
public interface IBannerRepository {
    /**
     * Finds a list of banners associated with a specific Point of Rest.
     * @param pointOfRestId The ID of the Point of Rest.
     * @return A list of banners for the given Point of Rest.
     * @throws NetworkConnectionException If a network or database connection error occurs.
     */
    List<Banner> findBannersByPointOfRest(String pointOfRestId) throws NetworkConnectionException;

    /**
     * Deletes a banner by its unique identifier.
     * @param bannerId The ID of the banner to delete.
     * @return true if the banner was successfully deleted, false otherwise.
     * @throws NetworkConnectionException If a network or database connection error occurs.
     */
    boolean delete(String bannerId) throws NetworkConnectionException;

    /**
     * Finds a banner by its unique identifier.
     * @param bannerId The ID of the banner to find.
     * @return The Banner object if found, null otherwise.
     * @throws NetworkConnectionException If a network or database connection error occurs.
     */
    Banner findById(String bannerId) throws NetworkConnectionException;
}