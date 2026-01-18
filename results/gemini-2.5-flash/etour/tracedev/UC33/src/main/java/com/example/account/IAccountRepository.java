package com.example.account;

/**
 * Interface for the Account Repository.
 * Defines the contract for data access operations related to the Account entity.
 */
public interface IAccountRepository {
    /**
     * Saves a new account or updates an existing one in the data store.
     *
     * @param account The Account object to save.
     * @return The saved Account object, potentially with an updated ID or other generated fields.
     */
    Account save(Account account);

    /**
     * Finds an account by its username.
     *
     * @param username The username to search for.
     * @return The Account object if found, or null otherwise.
     */
    Account findByUsername(String username);
}