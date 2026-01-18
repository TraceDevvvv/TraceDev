package com.example.repo;

import com.example.model.Teaching;
import com.example.exceptions.RepositoryException;

import java.util.List;

/**
 * Interface for data access operations related to Teaching entities.
 * Defines the contract for persistence mechanisms.
 */
public interface TeachingRepository {
    /**
     * Updates an existing Teaching entity in the repository.
     * @param teaching The Teaching entity with updated details.
     * @throws RepositoryException if an error occurs during the update operation.
     */
    void update(Teaching teaching) throws RepositoryException;

    /**
     * Finds a Teaching entity by its unique identifier.
     * @param id The ID of the teaching to find.
     * @return The found Teaching entity.
     * @throws RepositoryException if the teaching is not found or an error occurs during retrieval.
     */
    Teaching findById(String id) throws RepositoryException;

    /**
     * Retrieves all Teaching entities from the repository.
     * @return A list of all Teaching entities.
     * @throws RepositoryException if an error occurs during retrieval.
     */
    List<Teaching> findAll() throws RepositoryException;
}