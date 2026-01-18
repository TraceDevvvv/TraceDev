package com.example.application.service;

import com.example.application.port.in.ConfirmConventionCommand;
import com.example.application.port.out.AgencyNotificationPort;
import com.example.application.port.out.ConventionRequestRepository;
import com.example.domain.ConventionRequest;

/**
 * Service implementing the ConfirmConventionCommand use case.
 * Handles confirmation and notification of a convention request.
 */
public class ConfirmConventionService implements ConfirmConventionCommand {
    private final ConventionRequestRepository repository;
    private final AgencyNotificationPort agencyNotificationPort;

    public ConfirmConventionService(ConventionRequestRepository repository,
                                    AgencyNotificationPort agencyNotificationPort) {
        this.repository = repository;
        this.agencyNotificationPort = agencyNotificationPort;
    }

    /**
     * Confirms a convention request and notifies the agency.
     * @param requestId ID of the request to confirm
     * @throws IllegalArgumentException if request not found or invalid state
     */
    public void confirmConvention(String requestId) {
        ConventionRequest request = repository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("ConventionRequest not found: " + requestId));

        validateForConfirmation(request);

        // Confirm the request
        request.confirm();
        repository.save(request);

        // Notify agency
        boolean notificationSent = agencyNotificationPort.notifyAgency(request);
        if (notificationSent) {
            request.markAsSent();
            repository.save(request);
        } else {
            // In a real scenario, we might want to retry or handle failure differently
            throw new IllegalStateException("Failed to notify agency for request: " + requestId);
        }
    }

    @Override
    public void execute(String requestId) {
        confirmConvention(requestId);
    }

    /**
     * Validates that the request is in a state allowing confirmation.
     * @param request the request to validate
     * @throws IllegalStateException if validation fails
     */
    void validateForConfirmation(ConventionRequest request) {
        if (request.getStatus() != com.example.domain.RequestStatus.PENDING_VALIDATION) {
            throw new IllegalStateException("Cannot confirm request with status: " + request.getStatus());
        }
    }
}