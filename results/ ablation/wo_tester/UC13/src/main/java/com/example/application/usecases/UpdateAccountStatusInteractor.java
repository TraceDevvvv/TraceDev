package com.example.application.usecases;

import com.example.application.ports.input.UpdateAccountStatusInputPort;
import com.example.application.ports.output.TouristAccountRepository;
import com.example.application.ports.output.UpdateAccountStatusOutputPort;
import com.example.application.dtos.UpdateAccountStatusRequest;
import com.example.application.dtos.UpdateAccountStatusResponse;
import com.example.domain.TouristAccount;
import com.example.domain.enums.AccountOperation;
import com.example.domain.enums.AccountStatus;
import com.example.infrastructure.monitoring.SystemAvailabilityMonitor;

/**
 * Use case interactor implementation for updating account status.
 * Contains the core business logic for activation/deactivation operations.
 */
public class UpdateAccountStatusInteractor implements UpdateAccountStatusInputPort {
    private final TouristAccountRepository repository;
    private final UpdateAccountStatusOutputPort presenter;
    private final SystemAvailabilityMonitor monitor;

    public UpdateAccountStatusInteractor(
            TouristAccountRepository repository,
            UpdateAccountStatusOutputPort presenter,
            SystemAvailabilityMonitor monitor) {
        this.repository = repository;
        this.presenter = presenter;
        this.monitor = monitor;
    }

    @Override
    public UpdateAccountStatusResponse execute(UpdateAccountStatusRequest request) {
        // Check system availability as per quality requirement
        if (!monitor.checkAvailability()) {
            return new UpdateAccountStatusResponse(
                    false,
                    "System is currently unavailable. Please try again during business hours.",
                    null
            );
        }

        try {
            // Find the account
            TouristAccount account = repository.findById(request.getAccountId());
            if (account == null) {
                return new UpdateAccountStatusResponse(
                        false,
                        "Account not found with ID: " + request.getAccountId(),
                        null
                );
            }

            // Perform the operation
            AccountStatus previousStatus = account.getStatus();
            AccountOperation operation = request.getOperation();

            if (operation == AccountOperation.ACTIVATE) {
                account.activate();
            } else if (operation == AccountOperation.DEACTIVATE) {
                account.deactivate();
            } else {
                return new UpdateAccountStatusResponse(
                        false,
                        "Invalid operation: " + operation,
                        previousStatus
                );
            }

            // Save the updated account
            repository.save(account);

            // Prepare success response
            UpdateAccountStatusResponse response = new UpdateAccountStatusResponse(
                    true,
                    "Account " + request.getAccountId() + " successfully " +
                            (operation == AccountOperation.ACTIVATE ? "activated" : "deactivated"),
                    account.getStatus()
            );

            // Present the response
            presenter.present(response);
            return response;

        } catch (Exception e) {
            // Log the exception
            monitor.logDowntime("Error in UpdateAccountStatusInteractor: " + e.getMessage());
            
            return new UpdateAccountStatusResponse(
                    false,
                    "An error occurred while processing the request: " + e.getMessage(),
                    null
            );
        }
    }
}