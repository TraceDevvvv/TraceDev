package com.example.school;

import java.util.List;

/**
 * Generic repository interface for basic CRUD operations.
 * @param <T> The type of the entity.
 * @param <ID> The type of the entity's ID.
 */
public interface IRepository<T, ID> {
    /**
     * Finds an entity by its unique ID.
     * @param id The ID of the entity to find.
     * @return The found entity, or null if not found.
     * @throws SMOSConnectionException if there's a connection issue.
     */
    T findById(ID id) throws SMOSConnectionException;

    /**
     * Finds all entities of type T.
     * @return A list of all entities.
     * @throws SMOSConnectionException if there's a connection issue.
     */
    List<T> findAll() throws SMOSConnectionException;

    /**
     * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the
     * entity instance completely.
     * @param entity The entity to save.
     * @return The saved entity.
     * @throws SMOSConnectionException if there's a connection issue.
     */
    T save(T entity) throws SMOSConnectionException;

    /**
     * Deletes a given entity.
     * @param entity The entity to delete.
     * @throws SMOSConnectionException if there's a connection issue.
     */
    void delete(T entity) throws SMOSConnectionException;
}