
package com.example.convention.repository;

import com.example.convention.model.RefreshmentPoint;

/**
 * JPA implementation of the RefreshmentPointRepository interface.
 * This class would typically interact with a database through an EntityManager.
 * For this example, it uses an in-memory map to simulate persistence.
 */
public class JpaRefreshmentPointRepository implements RefreshmentPointRepository {
    // In a real application, this would be injected, e.g., @PersistenceContext
    // private EntityManager entityManager; // Declared for class diagram, not used in simulation

    // In a real application, this would be a database. For simulation, use a simple map.
    // Making it static to simulate a shared "database" for the application.
    private static java.util.Map<String, RefreshmentPoint> refreshmentPointsDb = new java.util.HashMap<>();

    // Populate with some dummy data for demonstration
    static {
        refreshmentPointsDb.put("RP001", new RefreshmentPoint("RP001", "Central Rest Stop", true));
        refreshmentPointsDb.put("RP002", new RefreshmentPoint("RP002", "Highway Cafe", false));
        refreshmentPointsDb.put("RP003", new RefreshmentPoint("RP003", "Mountain View Point", true));
        refreshmentPointsDb.put("RP004", new RefreshmentPoint("RP004", "Riverside Park", false)); // Not designated
    }

    /**
     * Finds a RefreshmentPoint by its ID.
     *
     * @param id The ID of the RefreshmentPoint.
     * @return The RefreshmentPoint object, or null if not found.
     */
    @Override
    public RefreshmentPoint findById(String id) {
        System.out.println("RPRepo: findById(" + id + ")");
        // Simulate database retrieval
        return refreshmentPointsDb.get(id);
    }
}
