package com.example.viewrequestssubscription;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Service layer for handling business logic related to {@link RegistrationRequest} objects.
 * This class acts as an intermediary between the presentation layer (e.g., a controller or UI)
 * and the data access layer ({@link RegistrationRequestRepository}).
 * It encapsulates operations like retrieving pending registration requests.
 */
public class RegistrationRequestService {

    private static final Logger LOGGER = Logger.getLogger(RegistrationRequestService.class.getName());

    private final RegistrationRequestRepository registrationRequestRepository;

    /**
     * Constructs a new RegistrationRequestService with the given repository.
     * The repository dependency is injected, promoting loose coupling and testability.
     *
     * @param registrationRequestRepository The repository to use for data access operations.
     * @throws NullPointerException if the provided repository is null.
     */
    public RegistrationRequestService(RegistrationRequestRepository registrationRequestRepository) {
        this.registrationRequestRepository = Objects.requireNonNull(registrationRequestRepository,
                "RegistrationRequestRepository cannot be null");
    }

    /**
     * Retrieves a list of all registration requests that are yet to be activated (i.e., in PENDING status).
     * This method fulfills the core requirement of the "ViewRequestsSubscription" use case.
     *
     * @return A list of {@link RegistrationRequest} objects with PENDING status.
     *         Returns an empty list if no pending requests are found or if an error occurs.
     */
    public List<RegistrationRequest> getPendingRegistrationRequests() {
        LOGGER.log(Level.INFO, "Attempting to retrieve all pending registration requests.");
        try {
            List<RegistrationRequest> pendingRequests = registrationRequestRepository.findAllPendingRequests();
            if (pendingRequests.isEmpty()) {
                LOGGER.log(Level.INFO, "No pending registration requests found.");
            } else {
                LOGGER.log(Level.INFO, "Successfully retrieved {0} pending registration requests.", pendingRequests.size());
            }
            return pendingRequests;
        } catch (Exception e) {
            // Log the exception for debugging and operational monitoring.
            // In a real application, this might involve more sophisticated error handling,
            // such as rethrowing a custom business exception or notifying an alerting system.
            LOGGER.log(Level.SEVERE, "Error retrieving pending registration requests: " + e.getMessage(), e);
            // Return an empty list to ensure the application doesn't crash and the UI can display gracefully.
            return Collections.emptyList();
        }
    }

    /**
     * Retrieves all registration requests, regardless of their status.
     * This might be useful for other administrative views or internal processes.
     *
     * @return A list of all {@link RegistrationRequest} objects.
     *         Returns an empty list if no requests are found or if an error occurs.
     */
    public List<RegistrationRequest> getAllRegistrationRequests() {
        LOGGER.log(Level.INFO, "Attempting to retrieve all registration requests.");
        try {
            List<RegistrationRequest> allRequests = registrationRequestRepository.findAll();
            if (allRequests.isEmpty()) {
                LOGGER.log(Level.INFO, "No registration requests found in total.");
            } else {
                LOGGER.log(Level.INFO, "Successfully retrieved {0} total registration requests.", allRequests.size());
            }
            return allRequests;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error retrieving all registration requests: " + e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    // Additional service methods could be added here for other operations,
    // such as approving, rejecting, or finding a request by ID.
    // For example:
    /*
    public Optional<RegistrationRequest> getRequestById(String requestId) {
        LOGGER.log(Level.INFO, "Attempting to retrieve registration request with ID: {0}", requestId);
        try {
            return registrationRequestRepository.findById(requestId);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error retrieving request by ID " + requestId + ": " + e.getMessage(), e);
            return Optional.empty();
        }
    }

    public RegistrationRequest approveRequest(String requestId) {
        LOGGER.log(Level.INFO, "Attempting to approve registration request with ID: {0}", requestId);
        return registrationRequestRepository.findById(requestId)
                .map(request -> {
                    request.setStatus(RegistrationRequest.RequestStatus.APPROVED);
                    return registrationRequestRepository.save(request);
                })
                .orElseThrow(() -> new IllegalArgumentException("Request not found with ID: " + requestId));
    }
    */
}