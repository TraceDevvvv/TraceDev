package com.etour.database;

import java.sql.ResultSet;

/**
 * Represents the ETOUR Database participant from the sequence diagram.
 * This class handles database interactions and may throw connection exceptions.
 */
public class Database {
    /**
     * Simulates a database query execution.
     * In the sequence diagram, this returns a ResultSet to the Repository.
     * 
     * @param query the SQL query
     * @return the ResultSet
     * @throws ConnectionException if the database connection fails
     */
    public ResultSet executeQuery(String query) throws ConnectionException {
        // Simulating database execution.
        // In a real scenario, this would connect to the database and execute the query.
        // For the traceability, this method corresponds to the message "--> Repository : ResultSet"
        // and the alternate flow "--x Repository : ConnectionException".
        try {
            // Simulate database operation
            // For simplicity, we assume the query is executed and returns null (since we cannot create a real ResultSet).
            // In actual code, this would be a real database call.
            if (query == null || query.trim().isEmpty()) {
                throw new ConnectionException("Invalid query");
            }
            // Simulate server interruption (note m16 in sequence diagram)
            if (shouldSimulateInterruption()) {
                throw new ConnectionException("ETOUR server interruption");
            }
            // Return null as a placeholder; real implementation would return a live ResultSet.
            return null;
        } catch (Exception e) {
            throw new ConnectionException("Database connection failed", e);
        }
    }

    /**
     * Simulates a condition where the server is interrupted.
     * This corresponds to the note "ETOUR server interruption" in the sequence diagram.
     */
    private boolean shouldSimulateInterruption() {
        // For demonstration, never simulate interruption by default.
        return false;
    }
}

/**
 * Exception representing a database connection failure.
 * This corresponds to the message "--x Repository : ConnectionException" in the sequence diagram.
 */
class ConnectionException extends Exception {
    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}