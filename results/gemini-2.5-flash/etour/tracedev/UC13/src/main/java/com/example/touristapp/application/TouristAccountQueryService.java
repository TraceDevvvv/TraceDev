package com.example.touristapp.application;

import com.example.touristapp.domain.TouristAccount;
import com.example.touristapp.infrastructure.ITouristAccountRepository;
import com.example.touristapp.infrastructure.RepositoryException;

/**
 * Service responsible for handling queries related to TouristAccount data.
 * This adheres to the CQRS principle, separating read concerns from write concerns.
 */
public class TouristAccountQueryService {
    private final ITouristAccountRepository touristAccountRepository;

    /**
     * Constructs a TouristAccountQueryService.
     * @param touristAccountRepository The repository for accessing tourist account data.
     */
    public TouristAccountQueryService(ITouristAccountRepository touristAccountRepository) {
        if (touristAccountRepository == null) {
            throw new IllegalArgumentException("TouristAccountRepository cannot be null.");
        }
        this.touristAccountRepository = touristAccountRepository;
    }

    /**
     * Retrieves the details of a specific tourist account.
     * @param accountId The ID of the account to retrieve.
     * @return The TouristAccount object if found.
     * @throws RepositoryException if there's an issue with data retrieval (e.g., ETOUR server connection).
     * @throws IllegalArgumentException if the account is not found.
     */
    public TouristAccount getAccountDetails(String accountId) throws RepositoryException {
        System.out.println("[QueryService] Fetching account details for ID: " + accountId);
        TouristAccount account = touristAccountRepository.findById(accountId);
        if (account == null) {
            throw new IllegalArgumentException("Tourist account with ID " + accountId + " not found.");
        }
        return account;
    }
}