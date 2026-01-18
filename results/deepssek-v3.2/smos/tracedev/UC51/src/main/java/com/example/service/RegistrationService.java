package com.example.service;

import com.example.command.RejectRegistrationCommand;
import com.example.exception.ValidationException;
import com.example.handler.RegistrationCommandHandler;
import com.example.model.RegistrationRequest;
import com.example.repository.RegistrationRepository;
import java.util.List;

/**
 * Service for registration operations.
 * Exit Condition: Administrator interrupts the connection operation to the interrupted SMOS server.
 */
public class RegistrationService {
    private RegistrationCommandHandler commandHandler;
    private RegistrationRepository registrationRepository;

    public RegistrationService(RegistrationCommandHandler commandHandler, RegistrationRepository registrationRepository) {
        this.commandHandler = commandHandler;
        this.registrationRepository = registrationRepository;
    }

    /**
     * Rejects a registration request.
     * @param requestId the ID of the request to reject
     * @param adminId the ID of the administrator performing the action
     * @throws ValidationException if validation fails
     */
    public void rejectRegistration(String requestId, String adminId) throws ValidationException {
        RejectRegistrationCommand command = new RejectRegistrationCommand(requestId, adminId);
        commandHandler.handle(command);
    }

    /**
     * Retrieves all pending registration requests.
     * @return list of pending registration requests
     */
    public List<RegistrationRequest> getPendingRegistrations() {
        return registrationRepository.findAllPending();
    }

    /**
     * Handles connection interruption.
     * This method simulates handling an interruption in the connection to the SMOS server.
     */
    public void handleConnectionInterrupt() {
        // Simulate handling connection interrupt, e.g., logging, cleanup, etc.
        System.out.println("Connection to SMOS server interrupted. Handling cleanup...");
    }
}