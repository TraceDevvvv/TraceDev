package com.example.repository;

import com.example.model.Account;

/**
 * Interface for account repository.
 */
public interface AccountRepository {
    /**
     * Saves an account.
     * @param account the account to save
     * @return the saved account
     */
    Account save(Account account);

    /**
     * Finds an account by ID.
     * @param id the account ID
     * @return the found account, or null if not found
     */
    Account findById(String id);
}