package com.example.application;

import com.example.domain.*;
import com.example.infrastructure.TouristRepository;
import com.example.infrastructure.PreferencesRepository;
import com.example.dto.PreferencesDTO;
import com.example.service.ConfirmationService;
import com.example.service.NotificationService;

/**
 * Use case for updating a tourist's search preferences.
 * Orchestrates the update flow including validation, confirmation, and persistence.
 */
public class UpdatePreferencesUseCase {
    private TouristRepository touristRepository;
    private PreferencesRepository preferencesRepository;
    private ConfirmationService confirmationService;
    private NotificationService notificationService;

    public UpdatePreferencesUseCase(TouristRepository touristRepository,
                                    PreferencesRepository preferencesRepository,
                                    ConfirmationService confirmationService,
                                    NotificationService notificationService) {
        this.touristRepository = touristRepository;
        this.preferencesRepository = preferencesRepository;
        this.confirmationService = confirmationService;
        this.notificationService = notificationService;
    }

    // Executes the update preferences command
    public Result execute(String touristId, PreferencesDTO preferencesData) {
        try {
            // Step 1: Retrieve tourist
            Tourist tourist = touristRepository.findById(touristId);
            if (tourist == null) {
                return new Result(false, "Tourist not found", "TOURIST_NOT_FOUND");
            }

            // Step 2: Create domain object from DTO
            SearchPreferences newPreferences = createSearchPreferences(preferencesData);
            newPreferences.setTouristId(touristId);

            // Step 3: Validate preferences
            if (!validatePreferences(newPreferences)) {
                return new Result(false, "Invalid preferences data", "VALIDATION_ERROR");
            }

            // Step 4: Retrieve old preferences for confirmation
            SearchPreferences oldPreferences = tourist.getPreferences();

            // Step 5: Request confirmation if needed
            boolean confirmationRequired = confirmationService.requestConfirmation(touristId, oldPreferences, newPreferences);
            // Note: In a real scenario, confirmation would be handled interactively.
            // Here we assume confirmation is granted if required.
            if (confirmationRequired) {
                // Simulate confirmation granted
                // In actual flow, this would wait for user input via controller.
            }

            // Step 6: Update tourist's preferences
            tourist.updatePreferences(newPreferences);
            touristRepository.save(tourist);

            // Step 7: Save preferences in repositories
            preferencesRepository.save(newPreferences);

            // Step 8: Memorize changes (e.g., audit log)
            memorizeChanges();

            // Step 9: Notify tourist
            notifySuccess(touristId);

            return new Result(true, "Preferences updated successfully", null);
        } catch (Exception e) {
            return new Result(false, "Update failed: " + e.getMessage(), "UPDATE_ERROR");
        }
    }

    // Creates SearchPreferences domain object from DTO
    SearchPreferences createSearchPreferences(PreferencesDTO preferencesData) {
        Range priceRange = new Range(preferencesData.getMinPrice(), preferencesData.getMaxPrice());
        // Assuming ID generation is handled elsewhere; using a placeholder.
        return new SearchPreferences(
                "pref-" + System.currentTimeMillis(),
                null, // touristId will be set later
                preferencesData.getCategories(),
                priceRange,
                preferencesData.getDistanceLimit(),
                preferencesData.getKeywords()
        );
    }

    // Validates the search preferences
    boolean validatePreferences(SearchPreferences preferences) {
        return preferences.validate();
    }

    // Placeholder for memorizing changes (e.g., audit logging)
    void memorizeChanges() {
        // In a real system, this might write to an audit log or event store.
        System.out.println("Changes memorized for audit.");
    }

    // Sends a notification to the tourist about the successful update
    void notifySuccess(String touristId) {
        notificationService.sendNotification(touristId, "Your search preferences have been updated successfully.");
    }
}