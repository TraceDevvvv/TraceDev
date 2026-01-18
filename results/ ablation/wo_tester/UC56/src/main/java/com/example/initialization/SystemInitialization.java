package com.example.initialization;

import com.example.model.Tourist;
import com.example.model.PositionResult;
import com.example.controller.UseCaseController;

/**
 * Handles system initialization including tourist localization.
 */
public class SystemInitialization {
    private UseCaseController controller;

    public SystemInitialization(UseCaseController controller) {
        this.controller = controller;
    }

    /**
     * Initializes the system for a tourist.
     */
    public PositionResult initializeSystem(Tourist tourist) {
        System.out.println("SystemInitialization: initializing for tourist " + tourist.getName());
        // Check if tourist is active (simplified: tourist is always active for this example)
        boolean touristActive = true;
        if (touristActive) {
            PositionResult result = controller.initializeTouristLocalization(tourist);
            handlePositionResult(result);
            notifyTouristLocalizationSupported(result);
            return result;
        } else {
            System.out.println("Tourist not active, basic mode.");
            return new PositionResult(PositionResultType.ERROR, null, "Tourist not active");
        }
    }

    /**
     * Handles the position result internally.
     */
    private void handlePositionResult(PositionResult result) {
        System.out.println("Handling position result: " + result.getResultType());
        // Could log, update state, etc.
    }

    /**
     * Notifies that localization is supported (or not) based on result.
     */
    public void notifyTouristLocalizationSupported(PositionResult result) {
        if (result.isSuccess()) {
            System.out.println("Tourist localization supported: position available.");
        } else {
            System.out.println("Tourist localization limited: no GPS data.");
        }
    }
    
    public PositionResult positionReceivedSuccessfully() {
        System.out.println("Position received successfully.");
        return new PositionResult(PositionResultType.SUCCESS);
    }
    
    public PositionResult positionNotAvailable() {
        System.out.println("Position not available.");
        return new PositionResult(PositionResultType.NOT_DETECTABLE);
    }
    
    public PositionResult initializationWithoutLocalization() {
        System.out.println("Initialization without localization.");
        return new PositionResult(PositionResultType.ERROR, null, "Initialization without localization");
    }
}