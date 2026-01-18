package com.example.ui;

import com.example.controller.RegistrationController;
import com.example.dto.RegistrationRequest;
import com.example.dto.RegistrationResponse;
import com.example.service.LoggingService;

/**
 * Boundary class representing the registration user interface.
 * <<boundary>> stereotype in class diagram.
 */
public class RegistrationUI {
    
    private RegistrationController registrationController;
    private LoggingService loggingService;
    
    /**
     * Constructor for RegistrationUI.
     * @param registrationController the registration controller
     * @param loggingService the logging service
     */
    public RegistrationUI(RegistrationController registrationController, LoggingService loggingService) {
        this.registrationController = registrationController;
        this.loggingService = loggingService;
    }
    
    /**
     * Displays the registration form (as per class diagram).
     */
    public void displayRegistrationForm() {
        System.out.println("Displaying registration form...");
    }
    
    /**
     * Submits a registration request (as per sequence diagram step 5).
     * @param request the registration request
     * @return the registration response
     */
    public RegistrationResponse submitRegistration(RegistrationRequest request) {
        return registrationController.handleRegistration(request);
    }
    
    /**
     * Shows success confirmation (as per class diagram).
     * @param response the successful registration response
     */
    public void showSuccessConfirmation(RegistrationResponse response) {
        System.out.println("Registration successful!");
        System.out.println("User ID: " + response.getUserId());
        System.out.println("Message: " + response.getMessage());
    }
    
    /**
     * Shows an error message (as per class diagram).
     * @param message the error message
     */
    public void showErrorMessage(String message) {
        System.out.println("Error: " + message);
    }
    
    /**
     * Shows connection error (as per sequence diagram step 44).
     * @param message the connection error message
     */
    public void showConnectionError(String message) {
        System.out.println("Connection Error: " + message);
    }
    
    /**
     * Handles cancel registration (as per sequence diagram step 35-36).
     */
    public void handleCancelRegistration() {
        System.out.println("Registration cancelled");
    }
    
    /**
     * Enables registration feature (as per sequence diagram step 1).
     */
    public void enableRegistrationFeature() {
        System.out.println("Registration feature enabled");
    }
    
    /**
     * Requests registration form (as per sequence diagram step 2).
     */
    public void requestRegistrationForm() {
        System.out.println("Requesting registration form...");
        displayRegistrationForm();
    }
    
    /**
     * Receives filled form and submits (as per sequence diagram steps 4 and 37).
     * @param username the username
     * @param email the email
     * @param password the password
     * @return the registration response
     */
    public RegistrationResponse receiveFormAndSubmit(String username, String email, String password) {
        RegistrationRequest request = new RegistrationRequest(username, email, password);
        return submitRegistration(request);
    }
}