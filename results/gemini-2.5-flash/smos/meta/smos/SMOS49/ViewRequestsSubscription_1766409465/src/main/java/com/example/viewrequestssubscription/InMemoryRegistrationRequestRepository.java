package com.example.viewrequestssubscription;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * An in-memory implementation of the {@link RegistrationRequestRepository} interface.
 * This class stores {@link RegistrationRequest} objects in a {@link ConcurrentHashMap}
 * for quick retrieval and manipulation without a persistent database.
 * It's suitable for testing or simple applications where data persistence is not required
 * across application restarts.
 */
public class InMemoryRegistrationRequestRepository implements RegistrationRequestRepository {

    // Using ConcurrentHashMap to store requests, mapping requestId to RegistrationRequest.
    // This provides thread-safe operations for concurrent access.
    private final Map<String, RegistrationRequest> requests = new ConcurrentHashMap<>();

    /**
     * Retrieves all registration requests that are currently in a PENDING state.
     * This method filters the in-memory collection to find requests with
     * {@link RegistrationRequest.RequestStatus#PENDING}.
     *
     * @return A list of {@link RegistrationRequest} objects with PENDING status.
     *         Returns an empty list if no pending requests are found.
     */
    @Override
    public List<RegistrationRequest> findAllPendingRequests() {
        // Stream through all stored requests, filter by PENDING status, and collect into a list.
        return requests.values().stream()
                .filter(request -> request.getStatus() == RegistrationRequest.RequestStatus.PENDING)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all registration requests stored in this repository, regardless of their status.
     *
     * @return A list of all {@link RegistrationRequest} objects.
     *         Returns an empty list if no requests are found.
     */
    @Override
    public List<RegistrationRequest> findAll() {
        // Return all values from the map as a new ArrayList to prevent external modification
        // of the internal map's collection.
        return List.copyOf(requests.values());
    }

    /**
     * Finds a registration request by its unique ID.
     *
     * @param requestId The unique identifier of the registration request.
     * @return An {@link Optional} containing the {@link RegistrationRequest} if found,
     *         or an empty {@link Optional} if no request with the given ID exists.
     */
    @Override
    public Optional<RegistrationRequest> findById(String requestId) {
        // Use Optional.ofNullable to handle cases where the key might not exist in the map.
        return Optional.ofNullable(requests.get(requestId));
    }

    /**
     * Saves a new registration request or updates an existing one.
     * If a request with the same ID already exists, it will be updated.
     * Otherwise, a new request will be added.
     *
     * @param request The {@link RegistrationRequest} object to save or update.
     * @return The saved or updated {@link RegistrationRequest} object.
     * @throws IllegalArgumentException if the provided request or its ID is null.
     */
    @Override
    public RegistrationRequest save(RegistrationRequest request) {
        if (request == null || request.getRequestId() == null) {
            throw new IllegalArgumentException("RegistrationRequest and its ID cannot be null.");
        }
        // Put the request into the map, effectively saving or updating it.
        requests.put(request.getRequestId(), request);
        return request;
    }

    /**
     * Deletes a registration request by its unique ID.
     *
     * @param requestId The unique identifier of the registration request to delete.
     * @return true if the request was successfully deleted, false otherwise (e.g., if not found).
     */
    @Override
    public boolean deleteById(String requestId) {
        if (requestId == null) {
            return false; // Cannot delete with a null ID.
        }
        // remove() returns the previous value associated with key, or null if there was no mapping.
        // We check if the returned value is not null to determine if an item was actually removed.
        return requests.remove(requestId) != null;
    }
}