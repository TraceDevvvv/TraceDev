package com.example.password;

/**
 * Interface for repository operations on AgencyAccount entities.
 */
public interface IAgencyAccountRepository {
    /**
     * Finds an AgencyAccount by its ID.
     *
     * @param accountId The ID of the account to find.
     * @return The found AgencyAccount, or null if not found.
     * @throws PersistenceException If a connection or database error occurs.
     */
    AgencyAccount findById(String accountId) throws PersistenceException;

    /**
     * Saves an AgencyAccount. If an account with the same ID already exists, it should be updated.
     *
     * @param account The AgencyAccount to save.
     * @throws PersistenceException If a connection or database error occurs.
     */
    void save(AgencyAccount account) throws PersistenceException;
}