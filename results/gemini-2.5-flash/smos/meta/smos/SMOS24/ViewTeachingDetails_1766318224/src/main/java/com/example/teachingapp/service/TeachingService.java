package com.example.teachingapp.service;

import com.example.teachingapp.data.TeachingDAO;
import com.example.teachingapp.model.Teaching;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for handling business logic related to Teaching entities.
 * This class acts as an intermediary between the presentation layer (e.g., UI)
 * and the data access layer (TeachingDAO). It orchestrates operations
 * and can include business rules or validations if needed.
 */
public class TeachingService {

    private final TeachingDAO teachingDAO;

    /**
     * Constructs a new TeachingService with the given TeachingDAO.
     * Dependency injection is used here to provide the data access implementation.
     *
     * @param teachingDAO The data access object for Teaching entities.
     */
    public TeachingService(TeachingDAO teachingDAO) {
        // Validate that the DAO is not null to prevent NullPointerExceptions.
        if (teachingDAO == null) {
            throw new IllegalArgumentException("TeachingDAO cannot be null.");
        }
        this.teachingDAO = teachingDAO;
    }

    /**
     * Retrieves a list of all available teachings.
     * This method supports the precondition where the system displays a list
     * of teachings from which an administrator can select one.
     *
     * @return A list of Teaching objects. Returns an empty list if no teachings are found.
     */
    public List<Teaching> getAllTeachings() {
        // Delegate the call to the DAO layer.
        return teachingDAO.getAllTeachings();
    }

    /**
     * Retrieves the detailed information of a single teaching by its ID.
     * This is the core business logic for the "ViewTeachingDetails" use case.
     * It handles the scenario where a teaching might not be found.
     *
     * @param teachingId The unique identifier of the teaching to retrieve.
     * @return An Optional containing the Teaching object if found, or an empty Optional if not found.
     */
    public Optional<Teaching> getTeachingDetails(String teachingId) {
        // Basic validation for the input ID.
        if (teachingId == null || teachingId.trim().isEmpty()) {
            // Log this or throw a more specific exception if this were a real application.
            System.err.println("Attempted to retrieve teaching with null or empty ID.");
            return Optional.empty();
        }
        // Delegate the data retrieval to the DAO layer.
        return teachingDAO.findTeachingById(teachingId);
    }
}