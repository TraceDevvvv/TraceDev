package com.example.viewrequestssubscription;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing data access operations for {@link RegistrationRequest} objects.
 * This interface abstracts the underlying data storage mechanism, allowing for different
 * implementations (e.g., in-memory, database, file system).
 */
public interface RegistrationRequestRepository {

    /**
     * Retrieves all registration requests that are currently in a PENDING state.
     * These are the requests that are "yet to be activated" as per the use case.
     *
     * @return A list of {@link RegistrationRequest} objects with PENDING status.
     *         Returns an empty list if no pending requests are found.
     */
    List<RegistrationRequest> findAllPendingRequests();

    /**
     * Retrieves all registration requests, regardless of their status.
     *
     * @return A list of all {@link RegistrationRequest} objects.
     *         Returns an empty list if no requests are found.
     */
    List<RegistrationRequest> findAll();

    /**
     * Finds a registration request by its unique ID.
     *
     * @param requestId The unique identifier of the registration request.
     * @return An {@link Optional} containing the {@link RegistrationRequest} if found,
     *         or an empty {@link Optional} if no request with the given ID exists.
     */
    Optional<RegistrationRequest> findById(String requestId);

    /**
     * Saves a new registration request or updates an existing one.
     *
     * @param request The {@link RegistrationRequest} object to save or update.
     * @return The saved or updated {@link RegistrationRequest} object.
     */
    RegistrationRequest save(RegistrationRequest request);

    /**
     * Deletes a registration request by its unique ID.
     *
     * @param requestId The unique identifier of the registration request to delete.
     * @return true if the request was successfully deleted, false otherwise.
     */
    boolean deleteById(String requestId);
}