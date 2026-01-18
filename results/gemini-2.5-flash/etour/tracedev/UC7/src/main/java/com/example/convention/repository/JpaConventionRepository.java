
package com.example.convention.repository;

import com.example.convention.model.Convention;

/**
 * JPA implementation of the ConventionRepository interface.
 * This class would typically interact with a database through an EntityManager.
 * For this example, it uses an in-memory map to simulate persistence.
 */
public class JpaConventionRepository implements ConventionRepository {
    // In a real application, this would be injected, e.g., @PersistenceContext
    // private EntityManager entityManager; // Declared for class diagram, not used in simulation

    // In a real application, this would be a database. For simulation, use a simple map.
    // Making it static to simulate a shared "database" for the application.
    private static java.util.Map<String, Convention> conventionsDb = new java.util.HashMap<>();

    // Populate with some dummy data for demonstration
    static {
        conventionsDb.put("CONV001", new Convention("CONV001", "PENDING", "RP001", "Annual Sales Meeting 2024"));
        conventionsDb.put("CONV002", new Convention("CONV002", "PENDING", "RP002", "Team Building Retreat"));
        conventionsDb.put("CONV003", new Convention("CONV003", "ACTIVE", "RP003", "Holiday Gala"));
        // Convention with non-designated RP for activation failure scenario
        conventionsDb.put("CONV004", new Convention("CONV004", "PENDING", "RP004", "Future Planning Workshop"));
    }

    /**
     * Finds a Convention by its ID.
     *
     * @param id The ID of the Convention.
     * @return The Convention object, or null if not found.
     */
    @Override
    public Convention findById(String id) {
        System.out.println("ConvRepo: findById(" + id + ")");
        // Simulate database retrieval
        return conventionsDb.get(id);
    }

    /**
     * Saves a Convention. Updates if exists, otherwise "creates" (adds to map).
     *
     * @param convention The Convention to save.
     */
    @Override
    public void save(Convention convention) {
        System.out.println("ConvRepo: saving Convention " + convention.getId() + " with status " + convention.getStatus());
        // Simulate database save/update
        conventionsDb.put(convention.getId(), convention);
    }
}
