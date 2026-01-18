package com.example.repository;

import com.example.model.Teaching;

import java.util.List;

/**
 * Interface for repository operations related to Teaching entities.
 * Defines methods for retrieving Teaching objects.
 */
public interface ITeachingRepository {
    /**
     * Finds a Teaching by its unique identifier.
     *
     * @param teachingId The ID of the teaching to find.
     * @return The Teaching object if found, otherwise null.
     */
    Teaching findById(String teachingId);

    /**
     * Retrieves all Teaching entities available in the system.
     *
     * @return A list of all Teaching objects.
     */
    List<Teaching> findAll();
}