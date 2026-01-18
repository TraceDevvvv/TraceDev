package com.example.infrastructure;

import com.example.domain.RegistrationRequest;
import java.util.List;

/**
 * Interface for data access operations related to RegistrationRequests.
 * Defines the contract for finding, updating, and retrieving all pending RegistrationRequest entities.
 */
public interface IRegistrationRequestRepository {
    /**
     * Finds a RegistrationRequest entity by its unique request ID.
     * @param requestId The ID of the request to find.
     * @return The RegistrationRequest object if found, null otherwise.
     */
    RegistrationRequest findById(String requestId);

    /**
     * Updates an existing RegistrationRequest entity in the repository.
     * Throws SMOSConnectionException to simulate a failure during persistence.
     * @param request The RegistrationRequest object to update.
     * @throws SMOSConnectionException if there's a simulated connection issue during the update.
     */
    void update(RegistrationRequest request) throws SMOSConnectionException;

    /**
     * Finds all pending RegistrationRequest entities.
     * @return A list of RegistrationRequest objects with PENDING status.
     */
    List<RegistrationRequest> findAllPending();

    /**
     * Saves a new RegistrationRequest entity to the repository.
     * This method is not explicitly in the diagram but is necessary for initial data.
     * @param request The RegistrationRequest object to save.
     */
    void save(RegistrationRequest request);
}