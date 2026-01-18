package com.example;

import java.util.List;

/**
 * Repository interface for fetching PointOfRest objects.
 */
public interface PointOfRestRepository {
    /**
     * Finds all PointOfRest objects that satisfy the specification.
     * @param specification the specification to filter by
     * @return list of matching PointOfRest objects
     */
    List<PointOfRest> findAll(PointOfRestSpecification specification);

    /**
     * Fetches PointOfRest objects from the external server.
     * @param specification the specification to filter by
     * @return list of matching PointOfRest objects
     */
    List<PointOfRest> fetchFromServer(PointOfRestSpecification specification);
}