package infrastructure;

import domain.PointOfRestaurant;

/**
 * Interface for repository operations related to PointOfRestaurant entities.
 * Methods are designed to throw NetworkUnavailableException to signal connectivity issues.
 */
public interface IPointOfRestaurantRepository {
    /**
     * Finds a PointOfRestaurant by its unique ID.
     *
     * @param id The ID of the PointOfRestaurant to find.
     * @return The found PointOfRestaurant object, or null if not found.
     * @throws NetworkUnavailableException If the connection to the underlying storage is interrupted.
     */
    PointOfRestaurant findById(String id) throws NetworkUnavailableException;

    /**
     * Updates an existing PointOfRestaurant in the repository.
     *
     * @param pointOfRestaurant The PointOfRestaurant object with updated information.
     * @throws NetworkUnavailableException If the connection to the underlying storage is interrupted.
     */
    void update(PointOfRestaurant pointOfRestaurant) throws NetworkUnavailableException;
}