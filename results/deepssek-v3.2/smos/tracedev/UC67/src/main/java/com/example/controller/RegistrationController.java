package com.example.controller;

import com.example.dto.RegistrationFormDTO;
import com.example.exception.ConnectionInterruptedException;
import com.example.service.IRegistrationService;

/**
 * Controller for handling registration requests
 */
public class RegistrationController {
    private IRegistrationService registrationService;

    public RegistrationController(IRegistrationService service) {
        this.registrationService = service;
    }

    /**
     * Returns a new RegistrationFormDTO for displaying the form
     * As per class diagram and sequence diagram
     * @return new RegistrationFormDTO instance
     */
    public RegistrationFormDTO showRegistrationForm() {
        // Create and return a new form DTO
        return new RegistrationFormDTO();
    }

    /**
     * Handles form submission from visitor
     * Implements sequence diagram flow
     * @param formData the submitted form data
     * @return true if registration successful, false otherwise
     */
    public boolean submitRegistration(RegistrationFormDTO formData) {
        try {
            // Check for connection interruption (simulated)
            // This is for traceability requirement
            if (isConnectionInterrupted()) {
                throw new ConnectionInterruptedException("Connection interrupted");
            }
            
            // Create DTO from data (as shown in sequence diagram)
            RegistrationFormDTO dto = new RegistrationFormDTO(
                formData.getName(),
                formData.getSurname(),
                formData.getMobilePhone(),
                formData.getEmail(),
                formData.getUsername(),
                formData.getPassword(),
                formData.getConfirmationPassword()
            );
            
            // Call service to register user
            boolean result = registrationService.registerUser(dto);
            
            // Return result to visitor
            return result;
            
        } catch (ConnectionInterruptedException e) {
            // Handle connection interruption as per sequence diagram
            System.out.println("Connection interrupted. Please try again.");
            throw e; // Re-throw to be handled by calling code
        }
    }
    
    /**
     * Simulates connection interruption check
     * For demonstration purposes only
     * @return true if connection is interrupted
     */
    private boolean isConnectionInterrupted() {
        // In real implementation, this would check actual connection status
        // For now, returning false to simulate normal flow
        return false;
    }
}