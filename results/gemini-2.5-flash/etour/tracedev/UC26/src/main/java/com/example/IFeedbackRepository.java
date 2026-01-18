package com.example;

import java.util.List;

/**
 * Interface for managing Feedback persistence.
 * Defines standard CRUD operations for Feedback entities.
 */
public interface IFeedbackRepository {
    /**
     * Finds a Feedback entity by its unique ID.
     * @param feedbackId The ID of the feedback to find.
     * @return The Feedback entity if found, null otherwise. Returns null also on simulated DB errors.
     */
    Feedback findById(String feedbackId);

    /**
     * Saves a Feedback entity. This can be for creating a new feedback or updating an existing one.
     * @param feedback The Feedback entity to save.
     * @return true if the save operation was successful, false otherwise. Returns false also on simulated DB errors.
     */
    boolean save(Feedback feedback);

    /**
     * Finds all Feedback entities associated with a specific site.
     * @param siteId The ID of the site.
     * @return A list of Feedback entities for the given site. Returns an empty list on simulated DB errors.
     */
    List<Feedback> findBySiteId(String siteId);
}