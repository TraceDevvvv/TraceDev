package com.example.teachingapp.data;

import com.example.teachingapp.model.Teaching;
import java.util.List;
import java.util.Optional;

/**
 * Interface for data access operations related to Teaching entities.
 * This DAO (Data Access Object) defines the contract for how teaching data
 * can be retrieved from a data source (e.g., a database, a mock service).
 */
public interface TeachingDAO {

    /**
     * Retrieves a list of all available teachings.
     * This method simulates fetching all teachings, which is a precondition
     * for the administrator to select a specific teaching to view its details.
     *
     * @return A list of Teaching objects. Returns an empty list if no teachings are found.
     */
    List<Teaching> getAllTeachings();

    /**
     * Retrieves a single teaching by its unique identifier.
     * This is the core operation for the "ViewTeachingDetails" use case.
     *
     * @param teachingId The unique ID of the teaching to retrieve.
     * @return An Optional containing the Teaching object if found, or an empty Optional if not found.
     */
    Optional<Teaching> findTeachingById(String teachingId);
}