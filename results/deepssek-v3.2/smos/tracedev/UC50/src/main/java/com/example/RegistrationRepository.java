package com.example;

import java.util.List;

/**
 * Repository interface for Registration entities.
 */
public interface RegistrationRepository {
    /**
     * Finds a Registration by its request ID.
     * @param requestId the request ID
     * @return the Registration, or null if not found
     */
    Registration findById(String requestId);

    /**
     * Deletes a Registration by its request ID.
     * @param requestId the request ID
     */
    void deleteById(String requestId);

    /**
     * Finds all pending registrations.
     * @return a list of pending registrations
     */
    List<Registration> findAllPending();
}