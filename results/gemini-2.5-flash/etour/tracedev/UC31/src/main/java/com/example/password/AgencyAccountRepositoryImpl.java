package com.example.password;

import java.util.HashMap;
import java.util.Map;

/**
 * Concrete implementation of IAgencyAccountRepository.
 * Uses an in-memory HashMap to simulate a database for AgencyAccount objects.
 * Can simulate PersistenceExceptions for specific account IDs.
 */
public class AgencyAccountRepositoryImpl implements IAgencyAccountRepository {
    // In-memory store to simulate a database
    private Map<String, AgencyAccount> accounts = new HashMap<>();

    /**
     * Initializes the repository with some sample data.
     */
    public AgencyAccountRepositoryImpl() {
        // Pre-populate with a test account
        accounts.put("user123", new AgencyAccount("user123", "hashed_OldPassword123"));
        accounts.put("testAccount123", new AgencyAccount("testAccount123", "hashed_InitialPassword99"));
        System.out.println("DEBUG: Repository initialized with test accounts.");
    }

    /**
     * Finds an AgencyAccount by its ID.
     *
     * @param accountId The ID of the account to find.
     * @return The found AgencyAccount, or null if not found.
     * @throws PersistenceException If a connection or database error occurs (simulated).
     */
    @Override
    public AgencyAccount findById(String accountId) throws PersistenceException {
        System.out.println("DEBUG: Repository: Attempting to find account with ID: " + accountId);
        // Simulate a database connection error for specific account IDs
        if (accountId != null && accountId.startsWith("error_")) {
            throw new PersistenceException("Simulated database connection error for account ID: " + accountId);
        }
        return accounts.get(accountId);
    }

    /**
     * Saves an AgencyAccount. If an account with the same ID already exists, it is updated.
     *
     * @param account The AgencyAccount to save.
     * @throws PersistenceException If a connection or database error occurs (simulated).
     */
    @Override
    public void save(AgencyAccount account) throws PersistenceException {
        System.out.println("DEBUG: Repository: Attempting to save account: " + account.getAccountId());
        // Simulate a database connection error for specific account IDs
        if (account != null && account.getAccountId() != null && account.getAccountId().startsWith("error_")) {
            throw new PersistenceException("Simulated database write error for account ID: " + account.getAccountId());
        }
        accounts.put(account.getAccountId(), account);
        System.out.println("DEBUG: Repository: Account '" + account.getAccountId() + "' saved successfully.");
    }

    /**
     * Retrieves the current state of an account from the repository.
     * Used for verification in the Main class.
     * @param accountId The ID of the account.
     * @return The AgencyAccount object.
     */
    public AgencyAccount getAccount(String accountId) {
        return accounts.get(accountId);
    }
}