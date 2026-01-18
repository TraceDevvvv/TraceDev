package com.example.registration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Concrete implementation of IRegistrationRepository using an in-memory Map
 * to store and manage RegistrationRequest objects.
 */
public class RegistrationRepository implements IRegistrationRepository {
    // In-memory storage for registration requests, mapped by their ID.
    private Map<String, RegistrationRequest> registrations;

    /**
     * Constructs a new RegistrationRepository and initializes the internal map.
     */
    public RegistrationRepository() {
        this.registrations = new HashMap<>();
    }

    /**
     * Adds a registration request to the repository.
     * This method is not in the interface but useful for populating the repository.
     *
     * @param request The RegistrationRequest object to add.
     */
    public void addRegistration(RegistrationRequest request) {
        registrations.put(request.id, request);
        System.out.println("[Repo] Added registration: " + request.id);
    }

    /**
     * Deletes a registration request from the repository.
     * Implements the deleteRegistration method from IRegistrationRepository.
     *
     * @param requestId The ID of the registration request to delete.
     */
    @Override
    public void deleteRegistration(String requestId) {
        if (registrations.remove(requestId) != null) {
            System.out.println("[Repo] Deleted registration with ID: " + requestId);
        } else {
            System.out.println("[Repo] No registration found with ID: " + requestId + " to delete.");
        }
    }

    /**
     * Retrieves all pending registration requests from the repository.
     * Implements the getAllPendingRegistrations method from IRegistrationRepository.
     * It filters requests based on a "PENDING" status.
     *
     * @return A list of RegistrationRequest objects that are currently pending.
     */
    @Override
    public List<RegistrationRequest> getAllPendingRegistrations() {
        // Assume 'PENDING' is the status for pending requests
        List<RegistrationRequest> pendingList = registrations.values().stream()
                .filter(req -> "PENDING".equalsIgnoreCase(req.status))
                .collect(Collectors.toList());
        System.out.println("[Repo] Retrieved " + pendingList.size() + " pending registrations.");
        return new ArrayList<>(pendingList); // Return a copy to prevent external modification of internal state
    }
}