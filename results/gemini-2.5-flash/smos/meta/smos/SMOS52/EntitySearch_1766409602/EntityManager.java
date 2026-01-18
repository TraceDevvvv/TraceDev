package EntitySearch_1766409602;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manages a collection of various searchable entities within the system.
 * This class acts as a central repository for all entities that can be searched
 * by the administrator. It provides methods to add entities and retrieve
 * the entire collection of managed entities.
 */
public class EntityManager {
    // A list to store all registered entities.
    // Using ArrayList for efficient addition and iteration.
    private final List<Entity> entities;

    /**
     * Constructs a new EntityManager with an empty list of entities.
     */
    public EntityManager() {
        this.entities = new ArrayList<>();
    }

    /**
     * Adds a single entity to the manager's collection.
     *
     * @param entity The entity to be added. Must not be null.
     * @throws IllegalArgumentException if the provided entity is null.
     */
    public void addEntity(Entity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Cannot add a null entity to the EntityManager.");
        }
        this.entities.add(entity);
        // In a real-world scenario, you might add logging here:
        // System.out.println("Added entity: " + entity.getDisplayName() + " (" + entity.getType() + ")");
    }

    /**
     * Returns an unmodifiable list of all entities currently managed by this manager.
     * This prevents external modification of the internal entity list.
     *
     * @return A List of all Entity objects. Returns an empty list if no entities have been added.
     */
    public List<Entity> getAllEntities() {
        return Collections.unmodifiableList(entities);
    }

    /**
     * Returns the total number of entities currently managed.
     *
     * @return The count of entities.
     */
    public int getEntityCount() {
        return entities.size();
    }
}