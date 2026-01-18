package infrastructure;

import domain.Banner;

import java.util.List;

/**
 * Interface for repository operations related to Banner entities.
 * Methods are designed to throw NetworkUnavailableException to signal connectivity issues.
 */
public interface IBannerRepository {
    /**
     * Saves a banner to the repository.
     *
     * @param banner The banner to save.
     * @return The saved banner, potentially with updated information (e.g., a generated ID).
     * @throws NetworkUnavailableException If the connection to the underlying storage is interrupted.
     */
    Banner save(Banner banner) throws NetworkUnavailableException;

    /**
     * Finds all banners associated with a specific PointOfRestaurant ID.
     *
     * @param pointOfRestaurantId The ID of the PointOfRestaurant.
     * @return A list of banners belonging to the specified PointOfRestaurant.
     * @throws NetworkUnavailableException If the connection to the underlying storage is interrupted.
     */
    List<Banner> findByPointOfRestaurantId(String pointOfRestaurantId) throws NetworkUnavailableException;

    /**
     * Deletes a banner by its ID.
     *
     * @param bannerId The ID of the banner to delete.
     * @throws NetworkUnavailableException If the connection to the underlying storage is interrupted.
     */
    void delete(String bannerId) throws NetworkUnavailableException;

    /**
     * Finds a banner by its unique ID.
     *
     * @param bannerId The ID of the banner to find.
     * @return The found Banner object, or null if not found.
     * @throws NetworkUnavailableException If the connection to the underlying storage is interrupted.
     */
    Banner findById(String bannerId) throws NetworkUnavailableException;
}