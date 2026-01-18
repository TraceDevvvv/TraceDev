package com.example.application;

import com.example.domain.Preferences;
import com.example.dto.PreferencesDTO;
import com.example.infrastructure.PreferencesRepositoryImpl;

/**
 * Application controller for editing preferences.
 * Handles the flow from presentation to domain and infrastructure.
 */
public class EditPreferencesUseCaseController {
    private PreferencesRepository preferencesRepository;
    private AuthenticationService authenticationService;

    /**
     * Constructor.
     * @param repository the preferences repository
     * @param authService the authentication service
     */
    public EditPreferencesUseCaseController(PreferencesRepository repository, AuthenticationService authService) {
        this.preferencesRepository = repository;
        this.authenticationService = authService;
    }

    /**
     * Handles initial request to edit preferences (loading current preferences).
     * @param userId the user identifier
     * @return the PreferencesDTO for display, or null if error
     */
    public PreferencesDTO handleEditPreferencesRequest(String userId) {
        // Step 2.0: Check authentication
        if (!validateAuthentication(userId)) {
            handleError("User not authenticated");
            return null;
        }

        try {
            // Step 2.1: Load preferences
            PreferencesDTO dto = loadPreferences(userId);
            // Step 2.3: Return DTO for display
            return dto;
        } catch (PreferencesRepositoryImpl.DatabaseException e) {
            handleError("Connection failed");
            return null;
        }
    }

    /**
     * Handles form submission with updated preferences.
     * @param userId the user identifier
     * @param updatedDto the updated preferences DTO
     */
    public void handleEditPreferencesRequest(String userId, PreferencesDTO updatedDto) {
        // Re-check authentication (Step 4.3)
        if (!validateAuthentication(userId)) {
            handleError("User not authenticated");
            return;
        }

        // Step 5: Request confirmation (delegated to form)
        // In this simulation, we assume confirmation is done via form interaction.
        // The actual confirmation logic is in the presentation layer.
    }

    /**
     * Proceeds with saving after confirmation.
     * Called by the form after user confirms.
     * @param userId the user identifier
     * @param updatedDto the updated preferences DTO
     */
    public void proceedWithSave(String userId, PreferencesDTO updatedDto) {
        try {
            // Step 6.2: Update preferences from DTO
            Preferences current = preferencesRepository.findByUserId(userId);
            current.updateFromDTO(updatedDto);

            // Step 7: Save preferences
            savePreferences(userId, current);

            notifySuccess("Preferences updated");
        } catch (PreferencesRepositoryImpl.DatabaseException e) {
            handleError("Connection failed");
        }
    }

    /**
     * Loads preferences for a user.
     * @param userId the user identifier
     * @return the PreferencesDTO
     */
    private PreferencesDTO loadPreferences(String userId) {
        Preferences prefs = preferencesRepository.findByUserId(userId);
        return prefs.toDTO();
    }

    /**
     * Saves preferences for a user.
     * @param userId the user identifier
     * @param dto the preferences DTO
     */
    private void savePreferences(String userId, PreferencesDTO dto) {
        Preferences prefs = new Preferences(dto.getLanguage(), dto.getCurrency(), dto.isNotificationEnabled(), dto.getDietaryRestrictions());
        preferencesRepository.save(userId, prefs);
    }

    /**
     * Saves preferences domain object.
     * @param userId the user identifier
     * @param preferences the preferences domain object
     */
    private void savePreferences(String userId, Preferences preferences) {
        preferencesRepository.save(userId, preferences);
    }

    /**
     * Validates authentication.
     * @param userId the user identifier
     * @return true if authenticated
     */
    private boolean validateAuthentication(String userId) {
        return authenticationService.isAuthenticated(userId);
    }

    /**
     * Handles errors.
     * @param errorMessage the error message
     */
    public void handleError(String errorMessage) {
        // In real scenario, would log the error and notify the form.
        // This method is kept for sequence diagram compatibility.
        notifyError(errorMessage);
    }

    /**
     * Requests confirmation from the user.
     * @param message the confirmation message
     * @return true if user confirms (simulated)
     */
    public boolean requestConfirmation(String message) {
        // This is a stub; actual confirmation is handled by the form.
        // Returning true for simulation.
        return true;
    }

    /**
     * Notifies success.
     * @param message the success message
     */
    public void notifySuccess(String message) {
        // Delegated to form.
        displaySuccessNotification(message);
    }

    /**
     * Notifies error.
     * @param message the error message
     */
    public void notifyError(String message) {
        // Delegated to form.
        displayErrorNotification(message);
    }

    /**
     * Cancels the operation.
     */
    public void cancelOperation() {
        // Cleanup if needed.
    }

    /**
     * Displays error notification (mapped from sequence diagram message m9, m39, m54).
     * @param message the error message
     */
    public void displayErrorNotification(String message) {
        // This method is called by the controller to inform the form.
        // Implementation delegated to form via method call.
    }

    /**
     * Displays success notification (mapped from sequence diagram message m45).
     * @param message the success message
     */
    public void displaySuccessNotification(String message) {
        // This method is called by the controller to inform the form.
        // Implementation delegated to form via method call.
    }

    /**
     * Shows confirmation prompt (mapped from sequence diagram message m29).
     * @param message the prompt message
     * @return true if confirmed
     */
    public boolean showConfirmationPrompt(String message) {
        // This method is called by the controller to request confirmation from the form.
        // Implementation delegated to form via method call.
        return requestConfirmation(message);
    }
}