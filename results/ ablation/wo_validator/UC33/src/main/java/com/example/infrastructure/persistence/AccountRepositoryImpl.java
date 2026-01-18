package com.example.infrastructure.persistence;

import com.example.domain.Account;
import com.example.application.interfaces.IAccountRepository;
import com.example.infrastructure.connection.ServerConnection;

/**
 * Concrete implementation of IAccountRepository using a server connection.
 */
public class AccountRepositoryImpl implements IAccountRepository {
    private ServerConnection connection;

    public AccountRepositoryImpl(ServerConnection connection) {
        this.connection = connection;
    }

    @Override
    public String save(Account account) {
        // Assumption: simulate saving to a database via server connection.
        if (!connection.isAlive()) {
            throw new IllegalStateException("Server connection is not alive.");
        }
        // In a real implementation, this would persist the account and return a generated ID.
        // For simplicity, we generate a dummy ID.
        String accountId = "ACC-" + System.currentTimeMillis();
        // Simulate persistence delay.
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return accountId;
    }

    @Override
    public Account findByUsername(String username) {
        // Assumption: simulate querying the database.
        if (!connection.isAlive()) {
            throw new IllegalStateException("Server connection is not alive.");
        }
        // For this example, we assume no duplicates, so return null.
        // In a real application, this would query the data store.
        return null;
    }
}