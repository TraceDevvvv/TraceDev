package com.example.touristapp.infrastructure;

import com.example.touristapp.domain.TouristAccount;
import com.example.touristapp.domain.AccountStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Adapter implementation for ITouristAccountRepository.
 * This class simulates interaction with an external system (ETOUR Server/Database)
 * for persisting and retrieving TouristAccount data.
 * It uses an in-memory map for demonstration purposes.
 */
public class TouristAccountRepositoryAdapter implements ITouristAccountRepository {
    // This map simulates a database table. In a real application, this would connect to ETOURServer.
    private final Map<String, TouristAccount> dataStore = new HashMap<>();

    // Flag to simulate connection failure to ETOUR Server for testing error scenarios.
    private boolean simulateConnectionFailure = false;

    /**
     * Simulates connecting to the ETOUR Server.
     * This method is conceptual, as ETOURServer is an external system.
     */
    public void connectToETOURServer() {
        System.out.println("[ETOUR Server] Simulating connection to ETOUR Server.");
        // In a real application, this would establish a connection.
    }

    /**
     * Enables or disables simulation of ETOUR server connection failures.
     * @param fail true to simulate failure, false otherwise.
     */
    public void setSimulateConnectionFailure(boolean fail) {
        this.simulateConnectionFailure = fail;
        System.out.println("[ETOUR Server] Connection failure simulation set to: " + fail);
    }

    private void checkConnection() throws RepositoryException {
        if (simulateConnectionFailure) {
            System.err.println("[ETOUR Server] Connection failure simulated.");
            throw new RepositoryException("Failed to connect to ETOUR server: simulated connection interruption.");
        }
    }

    @Override
    public TouristAccount findById(String accountId) throws RepositoryException {
        System.out.println("[TouristRepoAdapter] findById called for " + accountId);
        checkConnection(); // Simulate ETOUR server connection check
        // Simulate DB call
        try {
            Thread.sleep(100); // Simulate network latency
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("[DB] SELECT * FROM TouristAccount WHERE id = " + accountId);
        return dataStore.get(accountId);
    }

    @Override
    public void save(TouristAccount account) throws RepositoryException {
        System.out.println("[TouristRepoAdapter] save called for " + account.getId());
        checkConnection(); // Simulate ETOUR server connection check
        // Simulate DB call
        try {
            Thread.sleep(100); // Simulate network latency
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("[DB] UPDATE TouristAccount SET status = " + account.getStatus() + " WHERE id = " + account.getId());
        dataStore.put(account.getId(), account); // In-memory update
    }

    /**
     * Helper method to pre-populate the repository with some test data.
     * @param account The TouristAccount to add.
     */
    public void addAccountForTesting(TouristAccount account) {
        dataStore.put(account.getId(), account);
        System.out.println("[TouristRepoAdapter] Added test account: " + account);
    }
}