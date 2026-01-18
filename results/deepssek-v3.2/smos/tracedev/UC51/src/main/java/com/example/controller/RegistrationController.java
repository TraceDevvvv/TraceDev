package com.example.controller;

import com.example.exception.ValidationException;
import com.example.model.RegistrationRequest;
import com.example.service.RegistrationService;
import com.example.view.AdminDashboard;
import java.util.List;

/**
 * Controller for registration-related actions.
 */
public class RegistrationController {
    private RegistrationService registrationService;
    private AdminDashboard adminDashboard;

    public RegistrationController(RegistrationService registrationService, AdminDashboard adminDashboard) {
        this.registrationService = registrationService;
        this.adminDashboard = adminDashboard;
    }

    /**
     * Handles click on reject button.
     * @param requestId the ID of the request to reject
     */
    public void clickRejectButton(String requestId) {
        String adminId = "admin123"; // Assume current administrator ID is known.
        if (!validateRequestId(requestId)) {
            adminDashboard.displayErrorMessage("Invalid Request ID");
            return;
        }
        try {
            registrationService.rejectRegistration(requestId, adminId);
            List<RegistrationRequest> pendingRequests = registrationService.getPendingRegistrations();
            Object viewModel = prepareViewModel(pendingRequests);
            adminDashboard.displayPendingRegistrations(viewModel);
        } catch (ValidationException e) {
            adminDashboard.displayErrorMessage(e.getMessage());
        }
    }

    /**
     * Retrieves pending registrations and displays them.
     */
    public void getPendingRegistrations() {
        List<RegistrationRequest> pendingRequests = registrationService.getPendingRegistrations();
        Object viewModel = prepareViewModel(pendingRequests);
        adminDashboard.displayPendingRegistrations(viewModel);
    }

    /**
     * Validates the request ID.
     * @param requestId the request ID to validate
     * @return true if valid, false otherwise
     */
    boolean validateRequestId(String requestId) {
        return requestId != null && !requestId.trim().isEmpty();
    }

    /**
     * Prepares the view model from a list of registration requests.
     * @param requests the list of registration requests
     * @return the view model object
     */
    Object prepareViewModel(List<RegistrationRequest> requests) {
        // For simplicity, return the list itself as the view model.
        // In a real application, this might involve mapping to a DTO.
        return requests;
    }
}