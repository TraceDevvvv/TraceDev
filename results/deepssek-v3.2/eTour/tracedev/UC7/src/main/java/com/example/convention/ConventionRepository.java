package com.example.convention;

/**
 * Repository interface for Convention persistence operations.
 */
public interface ConventionRepository {
    /**
     * Finds a convention by its associated refreshment point ID.
     * @param pointId the refreshment point ID.
     * @return the found Convention, or null if not found.
     */
    Convention findByRefreshmentPointId(String pointId);

    /**
     * Saves a convention.
     * @param convention the convention to save.
     * @return the saved convention.
     */
    Convention save(Convention convention);
}