package com.example.report;

import java.util.List;

/**
 * ILocationRepository interface
 * Defines the contract for accessing Location data.
 */
public interface ILocationRepository {
    /**
     * Finds all available locations.
     * @return A list of Location objects.
     */
    List<Location> findAll();

    /**
     * Finds a location by its ID.
     * @param id The unique identifier of the location.
     * @return The Location object if found, null otherwise.
     */
    Location findById(String id);
}