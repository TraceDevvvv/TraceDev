package com.example.touristapp.infrastructure;

import com.example.touristapp.domain.TouristAccount;

/**
 * Port interface for TouristAccount persistence operations.
 * Defines the contract for how TouristAccount data is retrieved and stored.
 */
public interface ITouristAccountRepository {

    /**
     * Finds a TouristAccount by its unique identifier.
     * @param accountId The ID of the account to find.
     * @return The TouristAccount object if found, or null if not found.
     * @throws RepositoryException if there's an issue with data retrieval (e.g., ETOUR server connection).
     */
    TouristAccount findById(String accountId) throws RepositoryException;

    /**
     * Saves (creates or updates) a TouristAccount.
     * @param account The TouristAccount object to save.
     * @throws RepositoryException if there's an issue with data persistence (e.g., ETOUR server connection).
     */
    void save(TouristAccount account) throws RepositoryException;
}