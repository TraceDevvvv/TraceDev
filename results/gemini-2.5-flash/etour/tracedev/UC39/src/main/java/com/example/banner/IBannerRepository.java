package com.example.banner;

import java.util.List;

/**
 * Interface for data access operations related to Banners.
 * Specifies methods for finding and saving banner entities,
 * with methods now declared to throw NetworkException (R17).
 */
public interface IBannerRepository {

    /**
     * Finds a banner by its unique identifier.
     * @param bannerId The ID of the banner to find.
     * @return The Banner object if found.
     * @throws NetworkException If a network error occurs during data retrieval (R17).
     */
    Banner findById(String bannerId) throws NetworkException;

    /**
     * Finds all banners associated with a specific restaurant.
     * @param restaurantId The ID of the restaurant.
     * @return A list of Banner objects.
     * @throws NetworkException If a network error occurs during data retrieval (R17).
     */
    List<Banner> findAllByRestaurant(String restaurantId) throws NetworkException;

    /**
     * Saves a banner object to the persistent storage.
     * This method can be used for both creating new banners and updating existing ones.
     * @param banner The Banner object to save.
     * @throws NetworkException If a network error occurs during data persistence (R17).
     */
    void save(Banner banner) throws NetworkException;
}