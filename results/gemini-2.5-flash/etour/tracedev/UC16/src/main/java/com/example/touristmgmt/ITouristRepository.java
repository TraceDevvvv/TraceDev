package com.example.touristmgmt;

/**
 * Interface for managing Tourist data persistence.
 * Defines the contract for operations related to Tourist entities.
 */
public interface ITouristRepository {

    /**
     * Deletes a tourist record from the repository based on their ID.
     *
     * @param touristId The unique identifier of the tourist to delete.
     */
    void delete(String touristId);

    /**
     * Finds a tourist record by their unique ID.
     *
     * @param touristId The unique identifier of the tourist to find.
     * @return The Tourist object if found, otherwise null.
     */
    Tourist findById(String touristId);
}