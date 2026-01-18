package com.example.login;

import java.util.HashMap;
import java.util.Map;

/**
 * Infrastructure/Data Access Layer (Repository Pattern): Concrete implementation of UserRepository
 * that simulates fetching data from a database, potentially through an external system like SMOS.
 */
public class DatabaseUserRepository implements UserRepository {

    // Simulating a database with hardcoded users
    private final Map<String, User> userDatabase = new HashMap<>();
    private final ExternalSMOSServer smosServer;

    /**
     * Constructs a new DatabaseUserRepository.
     * @param smosServer The external SMOS server to query user data.
     */
    public DatabaseUserRepository(ExternalSMOSServer smosServer) {
        this.smosServer = smosServer;
        // Pre-populate with some mock user data
        // Password "password123" hashes to String.valueOf("password123".hashCode())
        userDatabase.put("testuser", new User("user-001", "testuser", String.valueOf("password123".hashCode()), false));
        userDatabase.put("admin", new User("user-002", "admin", String.valueOf("securepass".hashCode()), false));
        // User with short username/password will not be valid by CredentialValidator
        userDatabase.put("short", new User("user-003", "short", String.valueOf("pass".hashCode()), false));
    }

    /**
     * Finds a user by their username.
     * // Modified to satisfy requirement R13, R14
     * @param username The username to search for.
     * @return The User object if found, otherwise null.
     * @throws ConnectionException If there's an issue connecting to the data source (simulated).
     */
    @Override
    public User findByUsername(String username) throws ConnectionException {
        System.out.println("DatabaseUserRepository: Searching for user by username: " + username);
        // Simulate potential connection issue during database access.
        // For demonstration, let's say 'broken_db' username triggers a connection error.
        if ("broken_db".equals(username)) {
            throw new ConnectionException("Simulated connection error to database.");
        }
        return userDatabase.get(username);
    }

    /**
     * Finds a user by their username and hashed password.
     * // Modified to satisfy requirement R13, R14
     * @param username The username to search for.
     * @param passwordHash The hashed password to match.
     * @return The User object if found and credentials match, otherwise null.
     * @throws ConnectionException If there's an issue connecting to the data source (simulated).
     */
    @Override
    public User findByUsernameAndPasswordHash(String username, String passwordHash) throws ConnectionException {
        System.out.println("DatabaseUserRepository: Searching for user by username and password hash.");

        // Simulate a connection interruption as per the sequence diagram
        // If a specific username is used, trigger the connection error scenario.
        if ("network_down".equals(username)) {
            System.err.println("DatabaseUserRepository: Simulating ConnectionException for user 'network_down'.");
            throw new ConnectionException("Simulated network interruption to SMOS server."); // R13, R14
        }

        // Query ExternalSMOSServer as per R13
        System.out.println("DatabaseUserRepository: Querying ExternalSMOSServer for user data...");
        UserData userData = smosServer.queryUserData(username, passwordHash);

        if (userData == null) {
            System.out.println("DatabaseUserRepository: User not found in SMOS or credentials mismatch.");
            return null; // User not found via SMOS
        }

        // Check if the user exists in our local simulated database
        User storedUser = userDatabase.get(userData.getUsername());

        // If user exists and password hash matches, return the user.
        // In a real scenario, compare provided passwordHash with stored passwordHash directly.
        // Here, we trust SMOS for authentication, but check consistency with local user if needed.
        if (storedUser != null && storedUser.getUsername().equals(userData.getUsername())) {
             // For this simulation, the SMOS server is assumed to handle the password hash comparison.
             // If SMOS returns UserData, it implies successful verification.
             return storedUser; // Return the user from our local cache/db if found
        }
        System.out.println("DatabaseUserRepository: User found in SMOS but not in local DB or data mismatch: " + userData.getUsername());
        return null; // Credentials didn't match or user not found.
    }
}