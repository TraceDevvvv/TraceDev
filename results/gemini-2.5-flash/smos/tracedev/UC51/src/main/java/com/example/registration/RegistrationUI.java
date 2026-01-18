package com.example.registration;

import java.util.List;

/**
 * Represents the User Interface component for the registration system.
 * It interacts with the RegistrationService to perform operations and display results.
 */
public class RegistrationUI {
    // Dependency on the RegistrationService
    private RegistrationService registrationService;

    /**
     * Constructor for RegistrationUI, injecting the RegistrationService dependency.
     *
     * @param service The RegistrationService instance to interact with.
     */
    public RegistrationUI(RegistrationService service) {
        this.registrationService = service;
    }

    /**
     * Simulates an administrator viewing pending registrations.
     * This method initiates the display of pending requests by calling the service.
     */
    public void viewPendingRegistrations() {
        System.out.println("\n--- UI: Administrator views pending registrations ---");
        // UI -> Service : getPendingRegistrations()
        List<RegistrationRequest> pendingRequests = registrationService.getPendingRegistrations();
        // Service --> UI : pendingRegistrationsList
        displayRegistrationRequests(pendingRequests);
        System.out.println("--- UI: Registration requests ARE displayed. ---");
    }

    /**
     * Displays a list of registration requests to the user (e.g., admin).
     *
     * @param requests The list of RegistrationRequest objects to display.
     */
    public void displayRegistrationRequests(List<RegistrationRequest> requests) {
        System.out.println("\n--- UI: Displaying Registration Requests ---");
        if (requests.isEmpty()) {
            System.out.println("No pending registration requests found.");
        } else {
            for (RegistrationRequest request : requests) {
                System.out.println("  - ID: " + request.id + ", Student: " + request.studentName + ", Email: " + request.email + ", Status: " + request.status);
            }
        }
        System.out.println("------------------------------------------");
    }

    /**
     * Simulates an administrator selecting a request for rejection.
     * This method then calls the service to refuse the registration.
     *
     * @param requestId The ID of the request to be rejected.
     */
    public void selectRequestForRejection(String requestId) {
        System.out.println("\n--- UI: Administrator selects request '" + requestId + "' for rejection ---");
        System.out.println("A specific request IS selected for rejection.");

        // UI -> Service : refuseRegistration(requestId)
        List<RegistrationRequest> updatedRegistrations = registrationService.refuseRegistration(requestId);
        // Service --> UI : updatedRegistrations
        displayUpdatedRegistrations(updatedRegistrations);
    }

    /**
     * Displays the updated list of registration requests after a refusal operation.
     *
     * @param updatedRegistrations The list of registrations to display, reflecting recent changes.
     */
    public void displayUpdatedRegistrations(List<RegistrationRequest> updatedRegistrations) {
        System.out.println("\n--- UI: Displaying Updated Registrations after refusal ---");
        System.out.println("The targeted registration request IS refused.");
        System.out.println("Displaying the list of registrations yet to be activated.");
        if (updatedRegistrations.isEmpty()) {
            System.out.println("No pending registration requests remaining.");
        } else {
            for (RegistrationRequest request : updatedRegistrations) {
                System.out.println("  - ID: " + request.id + ", Student: " + request.studentName + ", Email: " + request.email + ", Status: " + request.status);
            }
        }
        System.out.println("-----------------------------------------------------");
    }
}