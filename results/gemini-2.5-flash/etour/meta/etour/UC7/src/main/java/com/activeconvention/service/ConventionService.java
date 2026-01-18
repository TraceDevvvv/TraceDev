package com.activeconvention.service;

import com.activeconvention.data.ConventionRepository;
import com.activeconvention.model.Convention;
import com.activeconvention.model.ConventionRequest;
import com.activeconvention.util.EtourConnectionException;
import com.activeconvention.util.Logger;

import java.util.List;

/**
 * ConventionService class for handling business logic related to conventions.
 * This class acts as the service layer, orchestrating operations between the UI (via Controller)
 * and the data access layers (ConventionRepository, EtourService).
 */
public class ConventionService {

    private ConventionRepository conventionRepository;
    private EtourService etourService;

    /**
     * Constructor for ConventionService.
     *
     * @param conventionRepository The repository for accessing convention data.
     * @param etourService         The service for interacting with the ETOUR server.
     */
    public ConventionService(ConventionRepository conventionRepository, EtourService etourService) {
        this.conventionRepository = conventionRepository;
        this.etourService = etourService;
        Logger.logInfo("ConventionService initialized.");
    }

    /**
     * Retrieves a list of pending convention requests.
     *
     * @return A list of ConventionRequest objects.
     */
    public List<ConventionRequest> getPendingConventionRequests() {
        Logger.logInfo("Fetching pending convention requests.");
        // In a real application, this might involve filtering by status or other criteria.
        return conventionRepository.getPendingConventionRequests();
    }

    /**
     * Retrieves a specific convention request by its ID.
     *
     * @param requestId The ID of the convention request.
     * @return The ConventionRequest object, or null if not found.
     */
    public ConventionRequest getConventionRequest(String requestId) {
        Logger.logInfo("Fetching convention request with ID: " + requestId);
        // This method would typically iterate through pending requests or query the repository directly
        // if requestId was a primary key for ConventionRequest.
        // For simplicity, we'll find it from the list of pending requests.
        return conventionRepository.getPendingConventionRequests().stream()
                .filter(req -> req.getRequestId().equals(requestId))
                .findFirst()
                .orElse(null);
    }

    /**
     * Retrieves detailed information for a specific convention by its ID.
     *
     * @param conventionId The ID of the convention.
     * @return The Convention object, or null if not found.
     */
    public Convention getConventionDetails(String conventionId) {
        Logger.logInfo("Fetching convention details for ID: " + conventionId);
        return conventionRepository.getConventionById(conventionId);
    }

    /**
     * Activates a given convention. This involves validating the data,
     * sending an activation request to the ETOUR server, and then
     * saving the activated convention status.
     *
     * @param convention The Convention object to activate.
     * @return true if activation is successful, false otherwise.
     * @throws EtourConnectionException if there's an issue connecting to the ETOUR server.
     */
    public boolean activateConvention(Convention convention) throws EtourConnectionException {
        Logger.logInfo("Attempting to activate convention: " + convention.getId());

        // Step 3: Check the data of the agreement and decide for activation.
        if (!validateConventionData(convention)) {
            Logger.logError("Convention data validation failed for convention: " + convention.getId(), null);
            return false; // Data is invalid
        }

        // Send activation request to ETOUR server
        boolean etourSuccess = etourService.sendActivationRequest(convention);

        if (etourSuccess) {
            // Update convention status and save
            convention.setStatus("Activated");
            conventionRepository.saveActivatedConvention(convention);
            Logger.logInfo("Convention " + convention.getId() + " successfully activated and saved.");
            return true;
        } else {
            // etourService.sendActivationRequest should throw EtourConnectionException on connection issues
            // If it returns false for other reasons, it's a service-level failure.
            Logger.logError("ETOUR server reported failure for convention: " + convention.getId(), null);
            return false;
        }
    }

    /**
     * Validates the data of a convention.
     * This is a placeholder for actual validation logic.
     *
     * @param convention The Convention object to validate.
     * @return true if the convention data is valid, false otherwise.
     */
    public boolean validateConventionData(Convention convention) {
        // P1 (Should-have): The system SHOULD implement basic data validation for loaded convention data.
        // Implement actual validation logic here. For example:
        if (convention == null) {
            return false;
        }
        if (convention.getId() == null || convention.getId().trim().isEmpty()) {
            Logger.logError("Validation failed: Convention ID is empty.", null);
            return false;
        }
        if (convention.getRefreshmentPointName() == null || convention.getRefreshmentPointName().trim().isEmpty()) {
            Logger.logError("Validation failed: Refreshment Point Name is empty for convention " + convention.getId(), null);
            return false;
        }
        // Add more validation rules as per business requirements (e.g., date formats, terms content)
        Logger.logInfo("Convention data validated successfully for convention: " + convention.getId());
        return true;
    }
}