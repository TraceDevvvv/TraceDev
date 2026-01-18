package com.example.registration;

import java.util.List;

/**
 * Service layer for handling registration-related business logic.
 * It orchestrates operations involving the repository and external gateways.
 */
public class RegistrationService {
    // Dependency on the registration repository
    private IRegistrationRepository registrationRepository;
    // Dependency on the SMOS gateway
    private ISMOSGateway smosGateway;

    /**
     * Constructor for RegistrationService, injecting necessary dependencies.
     *
     * @param repo The repository for managing registration requests.
     * @param gateway The gateway for interacting with the SMOS system.
     */
    public RegistrationService(IRegistrationRepository repo, ISMOSGateway gateway) {
        this.registrationRepository = repo;
        this.smosGateway = gateway;
    }

    /**
     * Refuses a specific registration request. This involves deleting the request
     * from the repository and interrupting the SMOS connection.
     *
     * @param requestId The ID of the registration request to refuse.
     * @return A list of all pending registrations after the refusal.
     */
    public List<RegistrationRequest> refuseRegistration(String requestId) {
        System.out.println("[Service] Orchestrating refusal for request ID: " + requestId);

        // 1. Delete the registration from the repository
        // Service -> Repo : deleteRegistration(requestId)
        registrationRepository.deleteRegistration(requestId);
        // Repo --> Service : registrationDeleted()

        // 2. Interrupt SMOS connection
        // Service -> Gateway : interruptSMOSConnection()
        smosGateway.interruptSMOSConnection();
        // Gateway --> Service : connectionInterrupted()
        System.out.println("[Service] Connection to SMOS server IS interrupted.");

        // 3. Retrieve updated list of pending registrations
        // Service -> Repo : getAllPendingRegistrations()
        List<RegistrationRequest> updatedList = registrationRepository.getAllPendingRegistrations();
        // Repo --> Service : pendingRegistrationsList
        System.out.println("[Service] Ensures consistency of system state after rejection.");
        return updatedList;
    }

    /**
     * Retrieves all pending registration requests from the repository.
     *
     * @return A list of pending RegistrationRequest objects.
     */
    public List<RegistrationRequest> getPendingRegistrations() {
        System.out.println("[Service] Requesting all pending registrations from repository.");
        // Service -> Repo : getAllPendingRegistrations()
        return registrationRepository.getAllPendingRegistrations();
    }
}