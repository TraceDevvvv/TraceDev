package com.example.registration;

import java.util.List;

/**
 * Interface defining the contract for managing registration requests.
 */
public interface IRegistrationRepository {

    /**
     * Deletes a registration request from the repository based on its ID.
     *
     * @param requestId The unique identifier of the registration request to delete.
     */
    void deleteRegistration(String requestId);

    /**
     * Retrieves a list of all pending registration requests.
     *
     * @return A list of RegistrationRequest objects that are currently pending.
     */
    List<RegistrationRequest> getAllPendingRegistrations();
}