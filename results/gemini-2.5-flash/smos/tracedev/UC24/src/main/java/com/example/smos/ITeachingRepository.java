package com.example.smos;

/**
 * Interface for the Teaching Repository.
 * Defines the contract for data access operations related to Teaching entities.
 */
public interface ITeachingRepository {
    /**
     * Finds a Teaching entity by its unique identifier.
     * @param teachingId The ID of the teaching to find.
     * @return The Teaching entity if found, otherwise null.
     * @throws ConnectionException if there's an issue connecting to the data source.
     */
    Teaching findById(String teachingId);
}