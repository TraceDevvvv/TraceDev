package com.example.handler;

import com.example.command.RejectRegistrationCommand;
import com.example.exception.ValidationException;
import com.example.model.RegistrationRequest;
import com.example.model.RequestStatus;
import com.example.repository.RegistrationRepository;

/**
 * Handler for rejecting registration requests.
 * Implements reliable execution with validation.
 */
public class RejectRegistrationHandler implements RegistrationCommandHandler {
    private RegistrationRepository registrationRepository;

    public RejectRegistrationHandler(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    /**
     * Handles the reject registration command.
     * @param command the command to handle
     */
    @Override
    public void handle(RejectRegistrationCommand command) {
        try {
            validate(command);
        } catch (ValidationException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
        RegistrationRequest request = registrationRepository.findById(command.getRequestId());
        if (request == null) {
            throw new IllegalArgumentException("Registration request not found for ID: " + command.getRequestId());
        }
        request.setStatus(RequestStatus.REJECTED);
        registrationRepository.save(request);
        // Note: Sequence diagram shows deleteById, but class diagram does not specify deletion.
        // We follow sequence diagram: delete the request after rejection.
        registrationRepository.deleteById(command.getRequestId());
    }

    /**
     * Validates the command.
     * @param command the command to validate
     * @throws ValidationException if validation fails
     */
    private void validate(RejectRegistrationCommand command) throws ValidationException {
        if (command.getRequestId() == null || command.getRequestId().trim().isEmpty()) {
            throw new ValidationException("Request ID cannot be empty");
        }
        if (command.getAdministratorId() == null || command.getAdministratorId().trim().isEmpty()) {
            throw new ValidationException("Administrator ID cannot be empty");
        }
        // Additional validation logic can be added here.
    }
}