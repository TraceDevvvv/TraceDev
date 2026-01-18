package com.etour.registration.adapters;

import com.etour.registration.domain.Account;
import com.etour.registration.exception.ConnectionException;
import com.etour.registration.exception.PersistenceException;
import com.etour.registration.ports.AccountRepository;
import java.sql.*;

/**
 * Adapter implementing AccountRepository using a simulated data source.
 */
public class AccountRepositoryImpl implements AccountRepository {
    private Object dataSource; // Simulated data source
    private Connection connection; // For database operations

    public AccountRepositoryImpl(Object dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Account save(Account account) throws PersistenceException {
        // Simulate database insert
        try {
            // Simulate potential connection failure
            if (Math.random() < 0.001) { // very low chance for demo
                throw new ConnectionException("Connection to the server ETOUR IS interrupted.");
            }
            // In reality, would use dataSource to persist
            System.out.println("Saving account for user: " + account.getUsername());
            
            // Simulate INSERT operation for sequence diagram
            simulateInsertOperation(account);
            return account;
        } catch (ConnectionException e) {
            throw e;
        } catch (Exception e) {
            throw new PersistenceException("Failed to save account: " + e.getMessage());
        }
    }

    @Override
    public boolean existsByUsername(String username) throws PersistenceException {
        // Simulate database query
        try {
            // Simulate connection failure
            if (Math.random() < 0.001) {
                throw new ConnectionException("Connection interrupted during username check.");
            }
            // In reality, query the database
            System.out.println("Checking if username exists: " + username);
            
            // Simulate SELECT COUNT operation for sequence diagram
            simulateSelectCountUsername(username);
            return false; // Assume username is available for demo
        } catch (ConnectionException e) {
            throw e;
        } catch (Exception e) {
            throw new PersistenceException("Error checking username: " + e.getMessage());
        }
    }

    @Override
    public boolean existsByEmail(String email) throws PersistenceException {
        // Simulate database query
        try {
            if (Math.random() < 0.001) {
                throw new ConnectionException("Connection interrupted during email check.");
            }
            System.out.println("Checking if email exists: " + email);
            
            // Simulate SELECT COUNT operation for sequence diagram
            simulateSelectCountEmail(email);
            return false; // Assume email is available for demo
        } catch (ConnectionException e) {
            throw e;
        } catch (Exception e) {
            throw new PersistenceException("Error checking email: " + e.getMessage());
        }
    }

    // New methods for sequence diagram traceability
    private void simulateInsertOperation(Account account) {
        System.out.println("Executing: INSERT INTO accounts (id, username, email, password_hash, status) VALUES (" +
                account.getId() + ", " + account.getUsername() + ", " + account.getEmail() + ", " + 
                account.getPasswordHash() + ", " + account.getStatus() + ")");
    }

    private void simulateSelectCountUsername(String username) {
        System.out.println("Executing: SELECT COUNT(*) FROM accounts WHERE username = '" + username + "'");
    }

    private void simulateSelectCountEmail(String email) {
        System.out.println("Executing: SELECT COUNT(*) FROM accounts WHERE email = '" + email + "'");
    }

    public String getGeneratedId() {
        return "generated-id-" + System.currentTimeMillis();
    }

    public void performInsertOperation(Account account) {
        simulateInsertOperation(account);
    }

    public void performSelectCountEmail(String email) {
        simulateSelectCountEmail(email);
    }

    public void performSelectCountUsername(String username) {
        simulateSelectCountUsername(username);
    }
}