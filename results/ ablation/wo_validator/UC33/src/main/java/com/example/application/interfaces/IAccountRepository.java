package com.example.application.interfaces;

import com.example.domain.Account;

/**
 * Abstract repository interface for account persistence.
 */
public interface IAccountRepository {
    /**
     * Saves an account and returns the generated ID.
     * @param account The account to save.
     * @return The unique identifier for the saved account.
     */
    String save(Account account);

    /**
     * Finds an account by username.
     * @param username The username to search.
     * @return The account if found, null otherwise.
     */
    Account findByUsername(String username);
}