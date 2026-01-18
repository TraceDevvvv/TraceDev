package com.example.external;

import com.example.domain.SearchCriteria;

import java.sql.ResultSet;

/**
 * Mock external server representing the eTour system.
 * Simulates database query execution.
 */
public class ETourServer {
    /**
     * Executes a query based on search criteria.
     * Simulates a database call that may take up to 15 seconds (REQ-PERF-001).
     * @param criteria search criteria
     * @return a ResultSet (mocked)
     */
    public ResultSet executeQuery(SearchCriteria criteria) {
        // Simulate query execution with a small delay
        try {
            // In real scenario, this would be a JDBC call with timeout set to 15 seconds.
            Thread.sleep(100); // 100 ms for simulation
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // Return a mock ResultSet (null for simplicity; in real code we would return a real ResultSet)
        return null;
    }
}