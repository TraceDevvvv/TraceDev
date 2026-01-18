package com.example;

/**
 * Simple implementation of IDatabase for demonstration.
 * Simulates database operations without an actual database.
 */
public class DatabaseImpl implements IDatabase {
    /**
     * Simulates executing a query.
     * @param query The SQL query string.
     * @return null (since we don't have a real ResultSet).
     */
    @Override
    public Object executeQuery(String query) {
        System.out.println("Executing query: " + query);
        return null;
    }

    /**
     * Simulates executing an update.
     * @param query The SQL update string.
     * @return 1 to simulate one row affected (for successful operations).
     */
    @Override
    public int executeUpdate(String query) {
        System.out.println("Executing update: " + query);
        return 1; // Assume success.
    }
}