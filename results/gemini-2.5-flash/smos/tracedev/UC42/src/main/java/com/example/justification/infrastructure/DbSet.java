package com.example.justification.infrastructure;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * A generic in-memory implementation of a DbSet, simulating a collection of entities.
 * This class provides basic CRUD operations for entities of type T,
 * identified by a unique key (String).
 * In a real application, this would interact with a database table.
 *
 * @param <T> The type of entity stored in this DbSet.
 */
public class DbSet<T> {
    private final Map<String, T> entities;
    private final Function<T, String> keyExtractor; // Function to extract the ID from an entity

    /**
     * Constructs a new DbSet with a key extractor.
     *
     * @param keyExtractor A function that extracts the unique ID (String) from an entity of type T.
     */
    public DbSet(Function<T, String> keyExtractor) {
        this.entities = new ConcurrentHashMap<>();
        this.keyExtractor = keyExtractor;
    }

    /**
     * Adds an entity to the set. If an entity with the same ID already exists, it will be overwritten.
     *
     * @param entity The entity to add.
     */
    public void add(T entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null.");
        }
        entities.put(keyExtractor.apply(entity), entity);
        System.out.println("DbSet: Added/Updated entity with ID: " + keyExtractor.apply(entity));
    }

    /**
     * Retrieves an entity by its ID.
     *
     * @param id The ID of the entity to retrieve.
     * @return The entity if found, null otherwise.
     */
    public T get(String id) {
        System.out.println("DbSet: Attempting to get entity with ID: " + id);
        return entities.get(id);
    }

    /**
     * Removes an entity by its ID.
     *
     * @param id The ID of the entity to remove.
     * @return The removed entity if found, null otherwise.
     */
    public T remove(String id) {
        System.out.println("DbSet: Attempting to remove entity with ID: " + id);
        // m15: Complete and irreversible removal from storage.
        return entities.remove(id);
    }

    /**
     * Retrieves all entities in the set.
     *
     * @return A collection of all entities.
     */
    public Collection<T> getAll() {
        return entities.values();
    }

    /**
     * Checks if an entity with the given ID exists in the set.
     *
     * @param id The ID to check.
     * @return true if an entity with the ID exists, false otherwise.
     */
    public boolean contains(String id) {
        return entities.containsKey(id);
    }

    /**
     * Clears all entities from the set.
     */
    public void clear() {
        entities.clear();
    }
}