package com.example.app;

import java.util.List;

/**
 * ITeachingRepository interface defines the contract for managing Teaching entities.
 * It specifies methods for saving new teachings and checking for existence by name.
 */
public interface ITeachingRepository {

    /**
     * Saves a Teaching entity to the repository.
     *
     * @param teaching The Teaching object to be saved.
     * @throws PersistenceException if there is an issue saving the teaching.
     */
    void save(Teaching teaching);

    /**
     * Checks if a teaching with the given name already exists in the repository.
     *
     * @param name The name of the teaching to check.
     * @return true if a teaching with the name exists, false otherwise.
     */
    boolean existsByName(String name);
}