package com.example.account;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Simulates a low-level database adapter.
 * In a real application, this would interact with a JDBC driver, ORM, etc.
 * For this example, it uses an in-memory Map to store account data.
 */
public class DatabaseAdapter {
    // In-memory "database"
    private Map<String, Map<String, String>> accountsById = new HashMap<>();
    private Map<String, String> usernamesToIds = new HashMap<>(); // For quick lookup by username

    private boolean simulateError = false; // Flag to simulate database errors (REQ-016)

    /**
     * Inserts new account data into the "database".
     *
     * @param accountData A map containing account details. Expected to have "id", "username", "email", "passwordHash".
     * @return The ID of the inserted account.
     * @throws DatabaseException if a simulated error is enabled or on duplicate username.
     */
    public String insertAccount(Map<String, String> accountData) {
        if (simulateError) {
            throw new DatabaseException("Simulated database connection error during insert.");
        }

        String id = accountData.get("id");
        String username = accountData.get("username");

        if (id == null || id.isEmpty()) {
            id = UUID.randomUUID().toString(); // Generate ID if not provided
            accountData.put("id", id);
        }

        if (usernamesToIds.containsKey(username)) {
            throw new DatabaseException("Account with username '" + username + "' already exists in DB.");
        }

        accountsById.put(id, new HashMap<>(accountData)); // Store a copy
        usernamesToIds.put(username, id);
        System.out.println("Database: Inserted account with ID: " + id + ", Username: " + username);
        return id;
    }

    /**
     * Finds account data by username.
     *
     * @param username The username to search for.
     * @return A map containing the account details if found, or null.
     * @throws DatabaseException if a simulated error is enabled.
     */
    public Map<String, String> findAccountByUsername(String username) {
        if (simulateError) {
            throw new DatabaseException("Simulated database connection error during find by username.");
        }

        String id = usernamesToIds.get(username);
        if (id != null) {
            return new HashMap<>(accountsById.get(id)); // Return a copy
        }
        return null;
    }

    /**
     * Sets a flag to simulate a database error for testing purposes. (REQ-016)
     *
     * @param simulateError If true, database operations will throw DatabaseException.
     */
    public void setSimulateError(boolean simulateError) {
        this.simulateError = simulateError;
        System.out.println("DatabaseAdapter: Simulate error set to " + simulateError);
    }

    /**
     * Clears all data from the simulated database.
     */
    public void clearData() {
        accountsById.clear();
        usernamesToIds.clear();
        System.out.println("DatabaseAdapter: All data cleared.");
    }
}