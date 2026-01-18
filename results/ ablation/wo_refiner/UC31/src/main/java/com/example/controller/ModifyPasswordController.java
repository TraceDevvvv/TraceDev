
package com.example.controller;

import com.example.dto.ModifyPasswordRequest;
import com.example.dto.ModifyPasswordResponse;
import com.example.model.AgencyOperator;
import com.example.repository.AgencyRepository;
import com.example.service.PasswordService;
import com.example.component.SessionManager;
import com.example.component.CircuitBreaker;
import com.example.exception.DatabaseConnectionException;
import java.util.Optional;
import java.util.concurrent.Callable;

/**
 * Controller for password modification operations.
 * Traceability: Controller stereotype from UML.
 * Precondition: R4 (agency operator must be logged in)
 */
public class ModifyPasswordController {
    private PasswordService passwordService;
    private AgencyRepository agencyRepository;
    private SessionManager sessionManager;
    private CircuitBreaker circuitBreaker;
    
    public ModifyPasswordController(PasswordService passwordService, 
                                  AgencyRepository agencyRepository,
                                  SessionManager sessionManager,
                                  CircuitBreaker circuitBreaker) {
        this.passwordService = passwordService;
        this.agencyRepository = agencyRepository;
        this.sessionManager = sessionManager;
        this.circuitBreaker = circuitBreaker;
    }
    
    /**
     * Main method for password modification.
     * Implements the sequence diagram flow.
     * @param request the password modification request
     * @return response indicating success or failure
     */
    public ModifyPasswordResponse modifyPassword(ModifyPasswordRequest request) {
        try {
            // Step 1: Validate session (R4)
            if (!sessionManager.verifySession(request.getAgencyId())) {
                return new ModifyPasswordResponse(false, "Session invalid or expired. Please log in again.");
            }
            
            // Step 2: Validate password confirmation (R9)
            if (!request.getNewPassword().equals(request.getConfirmPassword())) {
                return new ModifyPasswordResponse(false, "New password and confirmation don't match");
            }
            
            // Step 3: Encode new password
            String newEncryptedPassword = passwordService.encode(request.getNewPassword());
            
            // Step 4: Find agency with circuit breaker (R13)
            Optional<AgencyOperator> agencyOptional;
            try {
                agencyOptional = circuitBreaker.executeWithRetry((Callable<Optional<AgencyOperator>>)
                    () -> agencyRepository.findById(request.getAgencyId())
                );
            } catch (DatabaseConnectionException e) {
                throw e; // Re-throw to be caught by outer handler
            } catch (Exception e) {
                return new ModifyPasswordResponse(false, "Unable to retrieve agency information: " + e.getMessage());
            }
            
            if (!agencyOptional.isPresent()) {
                return new ModifyPasswordResponse(false, "Agency not found");
            }
            
            AgencyOperator agency = agencyOptional.get();
            
            // Step 5: Validate current password
            boolean isValid = passwordService.validatePassword(agency, request.getCurrentPassword());
            if (!isValid) {
                return new ModifyPasswordResponse(false, "Current password is incorrect");
            }
            
            // Step 6: Change password with circuit breaker
            try {
                circuitBreaker.executeWithRetry((Callable<Void>)
                    () -> {
                        agency.changePassword(newEncryptedPassword);
                        agencyRepository.save(agency);
                        return null;
                    }
                );
            } catch (DatabaseConnectionException e) {
                throw e; // Re-throw to be caught by outer handler
            } catch (Exception e) {
                return new ModifyPasswordResponse(false, "Failed to save new password: " + e.getMessage());
            }
            
            // Step 7: Return success
            return new ModifyPasswordResponse(true, "Password updated successfully");
            
        } catch (DatabaseConnectionException e) {
            // Handle connection interruption scenario (R12)
            return new ModifyPasswordResponse(false, "Server unavailable. Please try again later.");
        }
    }

    /**
     * Build Success Response - corresponds to message m23 in sequence diagram.
     * This is a self-message on MPC.
     */
    public ModifyPasswordResponse buildSuccessResponse() {
        return new ModifyPasswordResponse(true, "Password updated successfully");
    }

    /**
     * Build Failure Response - corresponds to messages m25 and m27 in sequence diagram.
     * This is a self-message on MPC.
     */
    public ModifyPasswordResponse buildFailureResponse(String message) {
        return new ModifyPasswordResponse(false, message);
    }
}
