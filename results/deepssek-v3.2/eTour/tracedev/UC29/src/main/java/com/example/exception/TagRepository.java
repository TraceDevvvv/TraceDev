package com.example.exception;

/**
 * Repository interface for tag operations.
 */
public interface TagRepository {
    /**
     * Checks if the given search tags exist.
     * @param searchTags The search tags.
     * @return true if exists, false otherwise.
     */
    boolean exists(String searchTags);

    /**
     * Finds the previous state associated with the search tags.
     * @param searchTags The search tags.
     * @return The previous State.
     */
    State findPreviousState(String searchTags);
}