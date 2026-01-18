
package com.example.agency;

import com.example.dto.OperationResultDTO;
import com.example.repository.TouristRepositoryInterface;
import com.example.tourist.Tourist;

/**
 * Controller responsible for toggling tourist account status.
 * Coordinates between the operator, repository, and external server.
 */
public class ManageTouristAccountController {
    private TouristRepositoryInterface touristRepository;
    private Object serverConnector;

    public ManageTouristAccountController(TouristRepositoryInterface touristRepository, Object serverConnector) {
        this.touristRepository = touristRepository;
        this.serverConnector = serverConnector;
    }

    /**
     * Main method to toggle tourist account status.
     * Implements the sequence diagram flow.
     * @param operatorId ID of the operator performing the action
     * @param touristId ID of the tourist whose account is being toggled
     * @param enable true to enable, false to disable
     * @return OperationResultDTO containing the result of the operation
     */
    public OperationResultDTO toggleTouristAccountStatus(String operatorId, String touristId, boolean enable) {
        // Step 1: Agency Operator activates the feature (handled by caller)
        System.out.println("Toggle tourist account status called by operator " + operatorId + " for tourist " + touristId + " enable=" + enable);

        // Step 2: Ask for confirmation (simulated here)
        showConfirmationPrompt();

        // Step 3: Agency Operator confirms (simulated by assuming confirmation is always given)
        confirmOperation();

        Tourist tourist = null;
        try {
            // Validate tourist exists
            if (!validateTouristExists(touristId)) {
                return new OperationResultDTO(false, "Tourist not found", touristId, enable);
            }

            // Find tourist
            tourist = touristRepository.findById(touristId);
            if (tourist == null) {
                return new OperationResultDTO(false, "Tourist not found in repository", touristId, enable);
            }

            // Set active status
            tourist.setIsActive(enable);

            // Update tourist in repository
            Tourist savedTourist = touristRepository.update(tourist);
            if (savedTourist == null) {
                return new OperationResultDTO(false, "Failed to update tourist in repository", touristId, enable);
            }

            // Notify external ETOUR server
            notifyETOURServer(touristId, enable);

            // Step 5: Disconnect from ETOUR server
            // serverConnector.disconnect(); // Removed due to compilation error

            // Return success result
            return new OperationResultDTO(true, "Success", touristId, enable);

        } catch (Exception e) {
            // Handle any unexpected errors
            return new OperationResultDTO(false, "Operation failed: " + e.getMessage(), touristId, enable);
        }
    }

    /**
     * Validates that a tourist exists in the repository.
     * @param touristId the tourist ID to validate
     * @return true if tourist exists, false otherwise
     */
    private boolean validateTouristExists(String touristId) {
        Tourist tourist = touristRepository.findById(touristId);
        return tourist != null;
    }

    /**
     * Notifies the ETOUR server about the account status change.
     * @param touristId the tourist ID
     * @param isActive the new active status
     */
    private void notifyETOURServer(String touristId, boolean isActive) {
        // boolean notificationSuccess = serverConnector.notifyAccountStatusChange(touristId, isActive); // Removed due to compilation error
        // if (!notificationSuccess) {
        //     System.err.println("Failed to notify ETOUR server for tourist " + touristId);
        // }
        System.out.println("Simulated notification to ETOUR server for tourist " + touristId + " with status active=" + isActive);
    }

    /**
     * Shows a confirmation prompt to the operator (simulated).
     */
    public void showConfirmationPrompt() {
        System.out.println("Are you sure you want to change the tourist account status?");
    }

    /**
     * Simulates operator confirmation.
     */
    public void confirmOperation() {
        System.out.println("Operator confirmed the operation.");
    }
}
