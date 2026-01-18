package com.tourist.infrastructure.persistence;

import com.tourist.domain.Tourist;
import java.util.ArrayList;
import java.util.List;

/**
 * Simulated database context.
 */
public class DatabaseContext {
    private String connectionString;
    private List<Tourist> tourists;

    /**
     * Constructor.
     * @param connString the database connection string
     */
    public DatabaseContext(String connString) {
        this.connectionString = connString;
        this.tourists = new ArrayList<>();
        // Seed some dummy data for testing.
        tourists.add(new Tourist("1", "John Doe", "john@example.com", "1234567890"));
        tourists.add(new Tourist("2", "Jane Smith", "jane@example.com", "0987654321"));
    }

    /**
     * Saves changes to the database.
     * @return number of rows affected
     */
    public int SaveChanges() {
        // REQ-Q-001: response time constraint.
        long startTime = System.currentTimeMillis();

        // Simulate database save operation.
        // In a real application, this would persist changes.
        try {
            Thread.sleep(100); // Simulate network latency
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        long endTime = System.currentTimeMillis();
        if ((endTime - startTime) > 5000) {
            System.err.println("Warning: DatabaseContext.SaveChanges took more than 5 seconds.");
        }
        return 1; // Assume one row affected.
    }

    /**
     * Gets the list of tourists (for simulation purposes).
     * @return list of tourists
     */
    public List<Tourist> getTourists() {
        return tourists;
    }
}