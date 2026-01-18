package com.example.repository;

import com.example.exception.ConnectionFailedException;
import com.example.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Concrete implementation of UserRepository, optimized for retrieving
 * user data from an 'Archive System' (R5, R9).
 * This class simulates communication with an external system.
 */
public class ArchiveUserRepository implements UserRepository {

    // A flag to simulate connection failures for the sequence diagram's alternative flow.
    private boolean simulateConnectionFailure = false;

    /**
     * Sets whether the repository should simulate a connection failure.
     * @param simulateConnectionFailure True to simulate failure, false otherwise.
     */
    public void setSimulateConnectionFailure(boolean simulateConnectionFailure) {
        this.simulateConnectionFailure = simulateConnectionFailure;
    }

    /**
     * Retrieves all User objects from the simulated external archive.
     * Optimized for large dataset retrieval (R9).
     * @return A list of all User objects.
     * @throws ConnectionFailedException if a simulated connection failure occurs.
     */
    @Override
    public List<User> findAllUsers() {
        System.out.println("[ArchiveUserRepository] Attempting to find all users from archive...");
        // This internally handles communication with the external Archive System (R5).
        // Optimized for large dataset retrieval (R9).
        // Calls the method that simulates interaction with the actual archive.
        return retrieveAllUsersData();
    }

    /**
     * Simulates the communication with an external "Archive System" and retrieves data.
     * This method could involve network calls, database queries, etc., in a real application.
     * Corresponds to the 'retrieveAllUsersData()' message to the Archive in the sequence diagram.
     *
     * @return A list of simulated User objects.
     * @throws ConnectionFailedException if `simulateConnectionFailure` is true.
     */
    private List<User> retrieveAllUsersData() {
        System.out.println("[ArchiveUserRepository] Communicating with 'Archive System' (simulated external system) for retrieveAllUsersData()...");

        if (simulateConnectionFailure) {
            System.err.println("[ArchiveUserRepository] Simulating connection interruption from 'archive'.");
            throw new ConnectionFailedException("Connection to SMOS server interrupted.");
        }

        // Simulate a delay for external system communication
        try {
            Thread.sleep(500); // Simulate network latency or data processing
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Return dummy data representing users from an archive.
        List<User> users = Arrays.asList(
                new User("1", "admin", "admin@example.com", true),
                new User("2", "john.doe", "john.doe@example.com", true),
                new User("3", "jane.smith", "jane.smith@example.com", false),
                new User("4", "guest", "guest@example.com", true)
        );
        System.out.println("[ArchiveUserRepository] Data retrieved from 'Archive System'.");
        return users;
    }
}