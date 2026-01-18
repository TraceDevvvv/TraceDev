package com.example;

/**
 * Interface for database operations.
 * Provides methods for executing queries and updates.
 */
public interface IDatabase {
    /**
     * Executes a query (e.g., SELECT).
     * @param query The SQL query string.
     * @return A simulated ResultSet (for this example, returns null).
     */
    Object executeQuery(String query);

    /**
     * Executes an update (e.g., INSERT, UPDATE, DELETE).
     * @param query The SQL update string.
     * @return The number of rows affected.
     */
    int executeUpdate(String query);
}