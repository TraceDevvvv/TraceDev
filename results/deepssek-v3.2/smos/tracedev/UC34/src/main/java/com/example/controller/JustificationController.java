package com.example.controller;

import com.example.dto.JustificationViewDTO;
import com.example.service.AuthenticationService;
import com.example.service.JustificationService;
import com.example.service.RegistrationService;

/**
 * Controller handling justification view requests.
 */
public class JustificationController {
    private JustificationService justificationService;
    private RegistrationService registrationService;
    private AuthenticationService authenticationService;
    public String R4 = "<<requirement R4>>"; // Attribute from class diagram

    /**
     * Constructor for JustificationController.
     * @param justificationService the service for justification data
     * @param registrationService the service for registration checks
     * @param authenticationService the service for authentication checks
     */
    public JustificationController(JustificationService justificationService,
                                   RegistrationService registrationService,
                                   AuthenticationService authenticationService) {
        this.justificationService = justificationService;
        this.registrationService = registrationService;
        this.authenticationService = authenticationService;
    }

    /**
     * Handles a request to view justifications for a student.
     * Performs precondition checks (authentication and registration) before proceeding.
     * @param studentId the student identifier
     * @return a JustificationViewDTO if successful, null if preconditions fail
     */
    public void handleJustificationViewRequest(String studentId) {
        // Precondition 1: Check administrator authentication (R3)
        if (!authenticationService.validateAdmin("admin")) { // userId assumed from context
            System.out.println("Error: Administrator login required");
            return;
        }

        // Precondition 2: Check registration completion (R4)
        if (!registrationService.isRegistrationCompleted(studentId)) {
            System.out.println("Error: Registration not completed");
            return;
        }

        // Preconditions satisfied: proceed to retrieve data
        JustificationViewDTO result = justificationService.getAbsencesForJustificationView(studentId);
        // In a real scenario, result would be passed to UI
    }
}