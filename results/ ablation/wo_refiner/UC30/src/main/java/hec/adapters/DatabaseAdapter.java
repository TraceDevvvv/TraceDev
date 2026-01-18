package hec.adapters;

import hec.core.Tag;
import hec.ports.TagRepository;
import java.util.Optional;

/**
 * Database adapter implementing TagRepository.
 * Integrates with a ConnectionManager for database operations.
 * Includes a cache-check method as per requirement.
 */
public class DatabaseAdapter implements TagRepository {
    private final ConnectionManager connectionManager;

    /**
     * Constructor that accepts a ConnectionManager.
     *
     * @param connectionManager the connection manager
     */
    public DatabaseAdapter(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public Tag save(Tag tag) {
        // Simulate database save operation with connection check.
        if (!connectionManager.checkConnection()) {
            throw new hec.core.ConnectionException("Database connection lost", 1001);
        }
        // In a real implementation, actual database insert would happen here.
        // For simulation, we assume success.
        // Update cache as per sequence diagram
        updateCache(tag);
        return tag;
    }

    @Override
    public boolean existsByName(String name) {
        // Simulate database exists check.
        if (!connectionManager.checkConnection()) {
            throw new hec.core.ConnectionException("Database connection lost", 1002);
        }
        // Check cache first as per sequence diagram
        if (checkCache()) {
            // In a real implementation, we would check cache for name.
            // For simulation, we'll just return false.
            return false;
        }
        // For simulation, always return false (no duplicate).
        return false;
    }

    @Override
    public Optional<Tag> findById(String id) {
        // Simulate database find by ID.
        if (!connectionManager.checkConnection()) {
            throw new hec.core.ConnectionException("Database connection lost", 1003);
        }
        // Check cache first as per sequence diagram
        if (checkCache()) {
            // In a real implementation, we would check cache.
            // For simulation, return empty.
            return Optional.empty();
        }
        // For simulation, return empty.
        return Optional.empty();
    }

    /**
     * Checks cache status.
     *
     * @return true if cache is active (simulated)
     */
    public boolean checkCache() {
        // Simulated cache check as per sequence diagram message "check cache first"
        return true;
    }

    /**
     * Updates cache as per sequence diagram message "update cache".
     *
     * @param tag the tag to cache
     */
    public void updateCache(Tag tag) {
        // Simulate cache update
        System.out.println("Cache updated for tag: " + tag.getId());
    }
}