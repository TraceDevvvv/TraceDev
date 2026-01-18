package com.example.registration;

import java.util.List;

/**
 * Main class to demonstrate the functionality of the registration system.
 * It sets up the dependencies and simulates user interactions defined in the sequence diagram.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("--- Starting Registration System Demonstration ---");

        // 1. Initialize core components
        RegistrationRepository registrationRepository = new RegistrationRepository();
        SMOSGateway smosGateway = new SMOSGateway();
        RegistrationService registrationService = new RegistrationService(registrationRepository, smosGateway);
        RegistrationUI registrationUI = new RegistrationUI(registrationService);

        // 2. Populate repository with some initial pending requests
        System.out.println("\n--- Initializing Repository with sample data ---");
        registrationRepository.addRegistration(new RegistrationRequest("REQ001", "Alice Smith", "alice@example.com", "PENDING"));
        registrationRepository.addRegistration(new RegistrationRequest("REQ002", "Bob Johnson", "bob@example.com", "PENDING"));
        registrationRepository.addRegistration(new RegistrationRequest("REQ003", "Charlie Brown", "charlie@example.com", "PENDING"));
        registrationRepository.addRegistration(new RegistrationRequest("REQ004", "Diana Prince", "diana@example.com", "APPROVED")); // Not pending

        // --- Simulate Sequence Diagram Flow ---

        // Scenario 1: Admin views pending registrations
        System.out.println("\n========== SCENARIO 1: View Pending Registrations ==========");
        registrationUI.viewPendingRegistrations();
        // Admin: Registration requests ARE displayed.

        // Scenario 2: Admin selects a request for rejection and the system processes it
        System.out.println("\n========== SCENARIO 2: Refuse a Registration ==========");
        String requestIdToReject = "REQ002"; // Admin chooses to reject Bob Johnson's request
        registrationUI.selectRequestForRejection(requestIdToReject);
        // Admin: The targeted registration request IS refused.

        // Verify that the rejected request is no longer pending
        System.out.println("\n========== VERIFICATION: Re-view Pending Registrations ==========");
        registrationUI.viewPendingRegistrations();
        System.out.println("\n--- Demonstration Complete ---");
    }
}