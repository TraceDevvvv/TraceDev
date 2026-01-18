package com.example.controller;

import com.example.model.PointOfRestaurant;
import com.example.model.PointDataForm;
import com.example.repository.PointRepository;
import com.example.service.ValidationService;
import com.example.service.ErrorHandler;
import com.example.service.AuthenticationService;
import java.util.Map;

/**
 * Controller for updating point of restaurant data.
 * Implements the main flow and interactions from the sequence diagram.
 */
public class PointUpdateController {
    private PointRepository pointRepository;
    private PointOfRestaurant currentPoint;
    private PointDataForm form;
    private ValidationService validationService;
    private ErrorHandler errorHandler;
    private AuthenticationService authenticationService;

    /**
     * Constructor with dependencies.
     */
    public PointUpdateController(PointRepository pointRepository,
                                 PointDataForm form,
                                 ValidationService validationService,
                                 ErrorHandler errorHandler,
                                 AuthenticationService authenticationService) {
        this.pointRepository = pointRepository;
        this.form = form;
        this.validationService = validationService;
        this.errorHandler = errorHandler;
        this.authenticationService = authenticationService;
    }

    /**
     * Activates the update functionality (REQ-FLOW-001).
     * This is the entry point after authentication.
     */
    public void activateUpdateFunctionality() {
        System.out.println("Update functionality activated.");
    }

    /**
     * Loads point data from the repository (REQ-FLOW-002).
     * @param pointId The ID of the point to load.
     */
    public void loadPointData(String pointId) {
        try {
            currentPoint = pointRepository.findById(pointId);
            if (currentPoint == null) {
                System.out.println("Point not found with ID: " + pointId);
            }
        } catch (Exception e) {
            errorHandler.handleServerError();
            System.out.println("Failed to load point data due to server error.");
        }
    }

    /**
     * Displays the current data in the form (REQ-FLOW-003).
     */
    public void displayCurrentData() {
        if (currentPoint != null) {
            form.displayFormData(currentPoint.toMap());
        } else {
            System.out.println("No point data loaded.");
        }
    }

    /**
     * Processes user changes from the form (REQ-FLOW-005).
     * @param changedData The map of changed data.
     */
    public void processUserChanges(Map<String, String> changedData) {
        System.out.println("Processing user changes...");
        
        // Validate input data (REQ-FLOW-006)
        if (validateInputData(changedData)) {
            // Show confirmation prompt (REQ-FLOW-007)
            if (requestConfirmation()) {
                confirmOperation();
            } else {
                System.out.println("User cancelled confirmation.");
                handleCancellation();
            }
        } else {
            System.out.println("Validation failed.");
            errorHandler.handleValidationError();
        }
    }

    /**
     * Validates input data using ValidationService (REQ-FLOW-006).
     * @param data The data to validate.
     * @return true if valid, false otherwise.
     */
    public boolean validateInputData(Map<String, String> data) {
        return validationService.validatePointData(data);
    }

    /**
     * Requests confirmation from the user (REQ-FLOW-007).
     * @return true if user confirms, false otherwise.
     */
    public boolean requestConfirmation() {
        if (form != null) {
            return form.showConfirmationPrompt();
        }
        return false;
    }

    /**
     * Saves modified data to the repository (REQ-FLOW-009).
     */
    public void saveModifiedData() {
        if (currentPoint == null) {
            System.out.println("No point data to save.");
            return;
        }
        
        try {
            // In a real scenario, we would extract changed data from form
            // For simplicity, we'll assume the currentPoint is already updated
            boolean success = pointRepository.update(currentPoint.getId(), currentPoint.toMap());
            if (success) {
                System.out.println("Data saved successfully.");
            } else {
                System.out.println("Failed to save data.");
            }
        } catch (Exception e) {
            errorHandler.handleServerError();
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    /**
     * Handles cancellation of the operation (REQ-EXIT-001).
     */
    public void handleCancellation() {
        System.out.println("Operation cancelled by user.");
        // Clean up resources if needed
    }

    /**
     * Handles errors by delegating to ErrorHandler.
     * @param errorMessage The error message.
     */
    public void handleError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
        // In a real application, would log and handle appropriately
    }

    /**
     * Confirms and executes the operation (REQ-FLOW-008).
     */
    public void confirmOperation() {
        System.out.println("Operation confirmed by user.");
        saveModifiedData();
    }

    /**
     * Main method to simulate the update flow from sequence diagram.
     * @param operatorId The operator's ID.
     * @param pointId The point ID to update.
     */
    public void executeUpdateFlow(String operatorId, String pointId) {
        // Authentication check (REQ-ENTRY-001)
        if (!authenticationService.authenticateUser(operatorId)) {
            System.out.println("Authentication failed. Access denied.");
            return;
        }

        // Connection check (simulated)
        boolean connectionAvailable = true; // In real app, check connection
        if (!connectionAvailable) {
            errorHandler.handleConnectionError();
            return;
        }

        // Main success scenario
        activateUpdateFunctionality();
        loadPointData(pointId);
        displayCurrentData();

        // Simulate user input
        Map<String, String> changes = form.getUserInput();
        processUserChanges(changes);
    }

    // Getters and setters
    public PointOfRestaurant getCurrentPoint() {
        return currentPoint;
    }

    public void setCurrentPoint(PointOfRestaurant currentPoint) {
        this.currentPoint = currentPoint;
    }
}