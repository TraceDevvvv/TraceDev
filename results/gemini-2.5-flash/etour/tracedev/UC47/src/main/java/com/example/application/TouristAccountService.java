package com.example.application;

import com.example.domain.TouristData;
import com.example.infrastructure.ITouristRepository;
import com.example.presentation.TouristForm;

import java.io.IOException; // For simulating connection errors

/**
 * Application Layer: Coordinates the flow of operations related to tourist accounts.
 * It uses domain objects and infrastructure serv to fulfill use cases.
 */
public class TouristAccountService {

    private final ITouristRepository touristRepository;
    private final ValidationService validationService;
    private final ErroredUseCaseActivator erroredUseCaseActivator;
    private final ExternalErrorHandler externalErrorHandler; // Added based on CD

    /**
     * Constructor for TouristAccountService.
     * @param touristRepository The repository for accessing tourist data.
     * @param validationService The service for validating data.
     * @param erroredUseCaseActivator The service for activating errored use cases.
     * @param externalErrorHandler The handler for external system errors.
     */
    public TouristAccountService(
        ITouristRepository touristRepository,
        ValidationService validationService,
        ErroredUseCaseActivator erroredUseCaseActivator,
        ExternalErrorHandler externalErrorHandler) {
        this.touristRepository = touristRepository;
        this.validationService = validationService;
        this.erroredUseCaseActivator = erroredUseCaseActivator;
        this.externalErrorHandler = externalErrorHandler;
    }

    /**
     * Loads tourist data for a given tourist ID.
     * Corresponds to `loadTouristData(touristId: String)` in the class diagram.
     * @param touristId The ID of the tourist.
     * @return TouristData if found and successful, null if not found or an error occurred.
     */
    public TouristData loadTouristData(String touristId) {
        System.out.println("Service: Loading tourist data for ID: " + touristId);
        TouristData touristData = null;
        try {
            // Service -> Repository : findById
            touristData = touristRepository.findById(touristId);
            if (touristData == null) {
                System.out.println("Service: Tourist with ID " + touristId + " not found.");
            }
        } catch (IOException e) { // Simulate connectionError
            // Repository Connection Fails (ETOUR)
            System.err.println("Service: Repository connection failed during load for ID " + touristId + ".");
            // Service -> ErrorHandler : handleConnectionError
            externalErrorHandler.handleConnectionError("TouristRepository", e);
            // Service --> Controller : error: "ETOUR"
            return null; // Indicate failure to load
        } catch (Exception e) {
            System.err.println("Service: An unexpected error occurred during load for ID " + touristId + ": " + e.getMessage());
            externalErrorHandler.handleConnectionError("TouristRepository", e);
            return null;
        }
        // Service --> Controller : touristData
        return touristData;
    }

    /**
     * Updates the tourist account information based on the provided form data.
     * Corresponds to `updateTouristAccount(touristId: String, updatedFormData: TouristForm)` in the class diagram.
     * This method performs initial processing and validation, but not the final save.
     * @param touristId The ID of the tourist to update.
     * @param updatedFormData The new data from the form.
     * @return true if the initial update processing (validation, data update) was successful,
     *         false if validation failed or data could not be prepared.
     */
    public boolean updateTouristAccount(String touristId, TouristForm updatedFormData) {
        System.out.println("Service: Updating tourist account for ID: " + touristId);
        // Service -> Validator : validateTouristData
        if (!validationService.validateTouristData(updatedFormData)) {
            // Data is invalid (Flow 8)
            System.out.println("Service: Validation failed for updated form data.");
            // Service -> ErroredUC : activate
            erroredUseCaseActivator.activate("INVALID_DATA", "Provided data for tourist " + touristId + " is invalid.");
            // Service --> Controller : updateSuccessful: false
            return false;
        }

        // Data is valid (Flow 6. continues)
        // Retrieve current tourist data to apply updates.
        TouristData touristData = loadTouristData(touristId); // Re-load data, in case it changed elsewhere
        if (touristData == null) {
            System.err.println("Service: Tourist data not found for ID " + touristId + " during update attempt.");
            erroredUseCaseActivator.activate("TOURIST_NOT_FOUND", "Could not find tourist " + touristId + " for update.");
            return false;
        }

        // Apply updates to the domain entity
        // Service -> DataEntity : updateFromForm
        touristData.updateFromForm(updatedFormData);
        // At this point, the TouristData object is updated in memory.
        // The actual persistence (save) happens after confirmation.
        System.out.println("Service: Tourist data for ID " + touristId + " successfully prepared for update.");
        // Service --> Controller : updateSuccessful: true
        return true;
    }

    /**
     * Processes the confirmation result for a tourist account update.
     * Corresponds to `processConfirmationResult(touristId: String, confirmed: boolean)` in the class diagram.
     * If confirmed, it attempts to save the changes.
     * @param touristId The ID of the tourist.
     * @param confirmed True if the user confirmed the changes, false if cancelled.
     * @return True if the operation (save or cancellation) was handled successfully, false if save failed.
     */
    public boolean processConfirmationResult(String touristId, boolean confirmed) {
        System.out.println("Service: Processing confirmation result for ID: " + touristId + ", confirmed: " + confirmed);
        if (confirmed) {
            // Tourist confirms (Flow 9. continues)
            TouristData touristData = loadTouristData(touristId); // Load the *potentially updated* data again or use a session-stored one
            if (touristData == null) {
                System.err.println("Service: Tourist data not found for ID " + touristId + " for final save after confirmation.");
                erroredUseCaseActivator.activate("TOURIST_NOT_FOUND", "Could not find tourist " + touristId + " for final save.");
                return false;
            }

            try {
                // Service -> Repository : save
                touristRepository.save(touristData);
                System.out.println("Service: Tourist data for ID " + touristId + " successfully saved.");
                // Service --> Controller : updateSuccessful: true
                return true;
            } catch (IOException e) { // Simulate connectionError
                // Repository Connection Fails (ETOUR)
                System.err.println("Service: Repository connection failed during save for ID " + touristId + ".");
                // Service -> ErrorHandler : handleConnectionError
                externalErrorHandler.handleConnectionError("TouristRepository", e);
                // Service --> Controller : updateSuccessful: false, error: "ETOUR"
                return false; // Indicate failure to save
            } catch (Exception e) {
                System.err.println("Service: An unexpected error occurred during save for ID " + touristId + ": " + e.getMessage());
                externalErrorHandler.handleConnectionError("TouristRepository", e);
                return false;
            }
        } else {
            // Tourist cancels (Exit Condition 2)
            System.out.println("Service: Operation for tourist ID " + touristId + " cancelled by user.");
            // Service --> Controller : updateSuccessful: false
            return false; // Indicate cancellation
        }
    }
}