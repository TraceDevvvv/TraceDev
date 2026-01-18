package com.example.banner.infrastructure;

import com.example.banner.domain.PointOfRest;
import java.util.List;

/**
 * Interface for data access operations related to {@link PointOfRest} entities.
 * Defines methods for finding and updating points of rest.
 */
public interface IPointOfRestRepository {
    /**
     * Finds a point of rest by its unique identifier.
     *
     * @param id The ID of the point of rest to find.
     * @return The found point of rest, or null if no point of rest with the given ID exists.
     */
    PointOfRest findById(String id); // SD: m34

    /**
     * Finds all available points of rest.
     *
     * @return A list of all points of rest.
     */
    List<PointOfRest> findAllPointsOfRest(); // SD: m6

    /**
     * Updates an existing point of rest in the persistent storage.
     *
     * @param pointOfRest The point of rest to update.
     * @return The updated point of rest.
     * @throws ETOURConnectionException if there's a simulated connection issue during updating.
     */
    PointOfRest update(PointOfRest pointOfRest) throws ETOURConnectionException; // SD: m54
}