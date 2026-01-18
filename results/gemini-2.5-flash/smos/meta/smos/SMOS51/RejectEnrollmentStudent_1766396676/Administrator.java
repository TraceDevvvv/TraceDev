package com.example.smos.controller;

import com.example.smos.model.RegistrationRequest;
import com.example.smos.model.RegistrationRequest.RequestStatus;
import com.example.smos.server.SMOSServer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The Administrator class handles administrative actions related to student registration requests.
 * This includes viewing pending requests and rejecting specific requests.
 * It interacts with a simulated SMOSServer to manage the lifecycle of registration.
 */
public class Administrator {

    // In a real application, this would be a service or repository layer
    // managing persistence. For this use case, we simulate it with an in-memory list.
    private final List<RegistrationRequest> allRegistrationRequests;
    private final SMOSServer smosServer;

    /**
     * Constructs an Administrator instance.
     *
     * @param smosServer The SMOSServer instance to interact with for server-side operations.
     * @throws IllegalArgumentException if smosServer is null.
     */
    public Administrator(SMOSServer smosServer) {
        if (smosServer == null) {
            throw new IllegalArgumentException("SMOSServer cannot be null.");
        }
        this.allRegistrationRequests = new ArrayList<>();
        this.smosServer = smosServer;
    }

    /**
     * Adds a registration request to the administrator's managed list.
     * This method is for simulation purposes to populate requests.
     * In a real system, requests would be submitted through a different channel.
     *
     * @param request The RegistrationRequest to add.
     * @throws IllegalArgumentException if the request is null or already exists.
     */
    public void addRegistrationRequest(RegistrationRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Registration request cannot be null.");
        }
        if (allRegistrationRequests.contains(request)) {
            throw new IllegalArgumentException("Registration request with ID " + request.getRequestId() + " already exists.");
        }
        this.allRegistrationRequests.add(request);
        System.out.println("[Administrator] Added new registration request: " + request.getRequestId());
    }

    /**
     * Allows the administrator to view all registration requests that are currently PENDING.
     * This fulfills the precondition "The user has already done the case of use 'View Registration Requests'".
     *
     * @return An unmodifiable list of pending registration requests.
     */
    public List<RegistrationRequest> viewPendingRegistrationRequests() {
        // Filter requests that are still in PENDING status
        List<RegistrationRequest> pendingRequests = allRegistrationRequests.stream()
                .filter(request -> request.getStatus() == RequestStatus.PENDING)
                .collect(Collectors.toList());
        System.out.println("[Administrator] Displaying " + pendingRequests.size() + " pending registration requests.");
        return Collections.unmodifiableList(pendingRequests); // Return an unmodifiable list to prevent external modification
    }

    /**
     * Rejects a specific student registration request.
     * This is the core action of the "RejectEnrollmentStudent" use case.
     *
     * @param requestId The unique identifier of the registration request to reject.
     * @return true if the request was successfully rejected, false if not found or already processed.
     * @throws IllegalArgumentException if requestId is null or empty.
     */
    public boolean rejectRegistrationRequest(String requestId) {
        if (requestId == null || requestId.trim().isEmpty()) {
            throw new IllegalArgumentException("Request ID cannot be null or empty.");
        }

        System.out.println("\n[Administrator] Attempting to reject registration request with ID: " + requestId);

        // Find the request by its ID
        Optional<RegistrationRequest> requestToRejectOptional = allRegistrationRequests.stream()
                .filter(request -> request.getRequestId().equals(requestId))
                .findFirst();

        if (requestToRejectOptional.isEmpty()) {
            System.out.println("[Administrator] Error: Registration request with ID " + requestId + " not found.");
            return false; // Request not found
        }

        RegistrationRequest requestToReject = requestToRejectOptional.get();

        // Check if the request is already rejected or approved
        if (requestToReject.getStatus() != RequestStatus.PENDING) {
            System.out.println("[Administrator] Warning: Request " + requestId + " is already " + requestToReject.getStatus() + ". Cannot reject.");
            return false;
        }

        // 1. Eliminates the registration request to the system.
        // This means changing its status and effectively removing it from the 'pending' view.
        requestToReject.setStatus(RequestStatus.REJECTED);
        System.out.println("[Administrator] Registration request " + requestId + " status changed to REJECTED.");

        // Simulate the system displaying the list of registrations yet to be activated
        // This is implicitly handled by calling viewPendingRegistrationRequests() after rejection.
        // The rejected request will no longer appear in the pending list.

        // Postcondition: The user interrupts the connection operation to the interrupted SMOS server
        // This is simulated by notifying the SMOSServer about the rejection.
        smosServer.interruptConnectionForRejectedStudent(requestToReject.getStudent().getStudentId());
        System.out.println("[Administrator] Notified SMOSServer about rejection for student " + requestToReject.getStudent().getStudentId() + ".");

        System.out.println("[Administrator] Successfully rejected registration request " + requestId + ".");
        return true;
    }

    /**
     * Retrieves a specific registration request by its ID, regardless of its status.
     * This is a helper method for testing and internal lookup.
     *
     * @param requestId The ID of the request to find.
     * @return An Optional containing the RegistrationRequest if found, or empty otherwise.
     */
    public Optional<RegistrationRequest> getRegistrationRequestById(String requestId) {
        return allRegistrationRequests.stream()
                .filter(request -> request.getRequestId().equals(requestId))
                .findFirst();
    }
}