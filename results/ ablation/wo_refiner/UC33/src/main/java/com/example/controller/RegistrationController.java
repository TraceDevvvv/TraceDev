package com.example.controller;

import com.example.dto.RegistrationRequest;
import com.example.dto.RegistrationResponse;
import com.example.service.RegistrationService;
import com.example.exception.InvalidDataException;
import com.example.exception.DuplicateEmailException;
import com.example.exception.ConnectionException;

/**
 * Controller class for handling user registration.
 * Included to satisfy requirement REQ-002 and REQ-004.
 * Stereotypes applied: Secure, Transactional.
 */
public class RegistrationController {
    
    private RegistrationService registrationService;
    // Added to satisfy requirement REQ-004: System access feature
    private boolean systemAccessEnabled;
    
    /**
     * Constructor for RegistrationController.
     * @param registrationService the registration service
     */
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
        this.systemAccessEnabled = true; // default enabled
    }
    
    /**
     * Handles user registration request.
     * @param request the registration request data
     * @return registration response with result
     */
    public RegistrationResponse handleRegistration(RegistrationRequest request) {
        // Check if system access is enabled per REQ-004
        if (!systemAccessEnabled) {
            return new RegistrationResponse(false, "System access is disabled", null);
        }
        
        try {
            // Delegate registration to the service layer
            var user = registrationService.registerUser(request);
            // Create success response as per sequence diagram step 20
            return new RegistrationResponse(true, "Account created", user.getUserId());
        } catch (InvalidDataException e) {
            // REQ-009: Activate Errored use case
            return new RegistrationResponse(false, "Invalid data", null);
        } catch (DuplicateEmailException e) {
            // REQ-009: Activate Errored use case
            return new RegistrationResponse(false, "Email already exists", null);
        } catch (ConnectionException e) {
            // REQ-014: Activate Errored use case
            return new RegistrationResponse(false, "Connection lost", null);
        } catch (Exception e) {
            // Generic error handling
            return new RegistrationResponse(false, "Registration failed: " + e.getMessage(), null);
        }
    }
    
    /**
     * Sets system access enabled status.
     * @param systemAccessEnabled true if system access is enabled
     */
    public void setSystemAccessEnabled(boolean systemAccessEnabled) {
        this.systemAccessEnabled = systemAccessEnabled;
    }
    
    /**
     * Gets system access enabled status.
     * @return true if system access is enabled
     */
    public boolean isSystemAccessEnabled() {
        return systemAccessEnabled;
    }
}