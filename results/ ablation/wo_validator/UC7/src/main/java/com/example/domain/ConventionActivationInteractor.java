package com.example.domain;

import com.example.ports.ConventionRepository;
import com.example.ports.RefreshmentPointRequestGateway;
import com.example.ports.NotificationPort;
import com.example.ports.ServerConnectionPort;
import java.util.Date;
import java.util.Optional;

/**
 * Core business logic interactor for activating a convention.
 * Implements the use case as per the sequence diagram.
 */
public class ConventionActivationInteractor {

    private final ConventionRepository conventionRepository;
    private final RefreshmentPointRequestGateway refreshmentPointRequestGateway;
    private final NotificationPort notificationPort;
    private final ServerConnectionPort serverConnectionPort;

    public ConventionActivationInteractor(ConventionRepository conventionRepository,
                                          RefreshmentPointRequestGateway refreshmentPointRequestGateway,
                                          NotificationPort notificationPort,
                                          ServerConnectionPort serverConnectionPort) {
        this.conventionRepository = conventionRepository;
        this.refreshmentPointRequestGateway = refreshmentPointRequestGateway;
        this.notificationPort = notificationPort;
        this.serverConnectionPort = serverConnectionPort;
    }

    /**
     * Executes the activation command. This method orchestrates the activation flow
     * as described in the sequence diagram.
     * @param activationCommand the command containing convention and operator IDs
     * @return the result of the activation attempt
     */
    public ConventionActivationResult execute(ConventionActivationCommand activationCommand) {
        String conventionId = activationCommand.getConventionId();
        // Step 1 & 2: Load data from point of rest and find convention
        ConventionDataDTO dataDTO = refreshmentPointRequestGateway.loadRequestData(conventionId);
        Optional<Convention> optionalConvention = conventionRepository.findById(conventionId);

        Convention convention;
        if (optionalConvention.isPresent()) {
            convention = optionalConvention.get();
            convention.loadData(dataDTO);
        } else {
            // If convention does not exist, create a new one (as implied in sequence diagram)
            convention = new Convention(conventionId);
            convention.loadData(dataDTO);
        }

        // Step 3: Return result with data for form display (interactor returns early for confirmation)
        // In the sequence diagram, the interactor returns a result to controller which then displays form.
        // We assume the first call to execute is for loading data and displaying form.
        // A second call with a "confirmed" command will perform the actual activation.
        // We differentiate by checking if the command contains a confirmation flag (assumed).
        // For simplicity, we assume the command has a boolean flag; if not present, we treat as first call.
        // We'll add a method to check if command is confirmed (assumption).
        if (!activationCommand.isConfirmed()) {
            return new ConventionActivationResult(false, "Data loaded for confirmation", new Date(), dataDTO);
        }

        // Step 8: Process request (core business logic) - activation confirmed
        convention.activate();
        Convention savedConvention = conventionRepository.save(convention);

        // Exit Conditions: notify and interrupt connection
        notificationPort.notifyActivation(savedConvention);
        serverConnectionPort.interruptConnection();

        return new ConventionActivationResult(true, "Convention activated successfully", new Date(), null);
    }
}