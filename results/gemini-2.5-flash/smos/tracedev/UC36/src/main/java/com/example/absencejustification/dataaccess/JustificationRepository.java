package com.example.absencejustification.dataaccess;

import com.example.absencejustification.application.Justification;

/**
 * Concrete implementation of IJustificationRepository using a Database.
 * This class handles the actual storage of Justification objects.
 */
public class JustificationRepository implements IJustificationRepository {
    private final Database database;

    /**
     * Constructs a JustificationRepository with a given Database instance.
     * @param database The database instance to interact with.
     */
    public JustificationRepository(Database database) {
        this.database = database;
    }

    /**
     * Saves a Justification entity to the database.
     * @param justification The Justification object to save.
     * @throws RuntimeException if the database connection fails during insertion.
     */
    @Override
    public void save(Justification justification) {
        System.out.println("[JustificationRepository] Attempting to save justification.");
        // Note: Uses Repository Pattern to abstract storage.
        try {
            boolean success = database.insert(justification);
            if (!success) {
                // This scenario might be covered by exception handling in a real app,
                // but for simulation, we can assume insert either throws or returns false.
                System.err.println("[JustificationRepository] Database insert reported failure for justification ID: " + justification.getId());
                throw new RuntimeException("Failed to insert justification into database.");
            }
            System.out.println("[JustificationRepository] Justification saved successfully. ID: " + justification.getId());
        } catch (RuntimeException e) {
            // JustRepo --x JustController : persistenceError("Database connection lost")
            // This propagates the error up, as per sequence diagram's connection failure path.
            System.err.println("[JustificationRepository] Persistence error during save: " + e.getMessage());
            throw new RuntimeException("Persistence error: Database connection lost or failed to save justification.", e);
        }
    }
}