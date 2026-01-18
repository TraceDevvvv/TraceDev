package com.example.application;

import com.example.domain.Tourist;
import com.example.domain.TouristRepository;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

/**
 * Use case controller coordinating the modify tourist flow.
 */
public class ModifyTouristUseCaseController {
    private TouristRepository touristRepository;
    private ErrorHandler errorHandler;

    public ModifyTouristUseCaseController(TouristRepository repository) {
        this.touristRepository = repository;
        this.errorHandler = new ErrorHandler();
    }

    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    /**
     * Executes the modify tourist use case.
     */
    public ModifyTouristResponse execute(ModifyTouristRequest request) {
        if (!validateInput(request)) {
            List<String> errors = new ArrayList<>();
            errors.add("Input validation failed");
            errorHandler.handleValidationError(errors);
            return new ModifyTouristResponse(false, "Invalid input", null);
        }

        if (!requestConfirmation()) {
            return new ModifyTouristResponse(false, "Operation cancelled by user", null);
        }

        if (!confirmChanges()) {
            return new ModifyTouristResponse(false, "Changes not confirmed", null);
        }

        // Check ETOUR connection as per sequence diagram
        if (!checkConnection()) {
            errorHandler.handleSystemError(new Exception("ETOUR connection interrupted"));
            return new ModifyTouristResponse(false, "Connection to external server failed", null);
        }

        Tourist tourist = touristRepository.findById(request.getTouristId());
        if (tourist == null) {
            return new ModifyTouristResponse(false, "Tourist not found", null);
        }

        tourist.updateProfile(request.getUpdatedData());
        if (!tourist.validate()) {
            List<String> errors = new ArrayList<>();
            errors.add("Tourist data invalid after update");
            errorHandler.handleValidationError(errors);
            return new ModifyTouristResponse(false, "Updated data is invalid", null);
        }

        Tourist updated = touristRepository.update(tourist);
        return new ModifyTouristResponse(true, "Tourist updated successfully", updated);
    }

    /**
     * Validates the request input.
     */
    private boolean validateInput(ModifyTouristRequest request) {
        return request != null && request.getTouristId() != null && request.getUpdatedData() != null;
    }

    /**
     * Confirms changes internally.
     */
    private boolean confirmChanges() {
        // In a real scenario, might involve additional business logic
        return true;
    }

    /**
     * Searches tourists by criteria (Flow 1).
     */
    public List<Tourist> searchTourist(Map<String, Object> criteria) {
        // This method implements the SearchTourist use case activation
        System.out.println("ModifyTouristUseCaseController: searching tourists by criteria");
        return touristRepository.findAllByCriteria(criteria);
    }

    /**
     * Requests confirmation from the presentation layer.
     */
    public boolean requestConfirmation() {
        // Message m28 from UC to TP
        System.out.println("ModifyTouristUseCaseController: request confirmation");
        // This method signals the presentation layer to ask the user for confirmation.
        // The actual confirmation decision is made by the user via presentation layer.
        // Returning true here means the controller is ready to proceed if user confirms.
        return true;
    }

    /**
     * Checks connection to ETOUR server.
     */
    public boolean checkConnection() {
        // Simulating a connection check. In reality would involve pinging ETOUR.
        System.out.println("ModifyTouristUseCaseController: checking ETOUR connection");
        return true;
    }

    /**
     * Finds a tourist by ID.
     */
    public Tourist findById(String id) {
        System.out.println("ModifyTouristUseCaseController: find tourist by id " + id);
        return touristRepository.findById(id);
    }
}