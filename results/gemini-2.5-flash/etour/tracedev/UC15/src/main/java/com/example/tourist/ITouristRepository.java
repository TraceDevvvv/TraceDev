package com.example.tourist;

/**
 * Interface for managing Tourist persistence.
 * Marked as a Repository, defining the contract for data access operations.
 */
public interface ITouristRepository {
    /**
     * Finds a Tourist by its ID.
     *
     * @param id The ID of the tourist to find.
     * @return The Tourist entity if found, null otherwise.
     */
    Tourist findById(String id);

    /**
     * Saves a Tourist entity. This can be an insert or an update.
     *
     * @param tourist The Tourist entity to save.
     * @return The saved Tourist entity, potentially with updated fields (e.g., generated ID).
     */
    Tourist save(Tourist tourist);
}