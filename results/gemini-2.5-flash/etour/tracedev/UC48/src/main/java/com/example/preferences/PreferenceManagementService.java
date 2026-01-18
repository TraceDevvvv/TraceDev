package com.example.preferences;

/**
 * Service layer responsible for managing user preferences.
 * It encapsulates business logic and coordinates between the controller and the data repository.
 */
public class PreferenceManagementService {

    private final PreferencesRepository preferencesRepository;

    /**
     * Constructor for PreferenceManagementService.
     * @param preferencesRepository The repository dependency for data access.
     */
    public PreferenceManagementService(PreferencesRepository preferencesRepository) {
        this.preferencesRepository = preferencesRepository;
    }

    /**
     * Retrieves a tourist's preferences.
     * Corresponds to the sequence diagram step where PreferenceManagementService
     * calls findByTouristId on PreferencesRepository and converts to PreferenceDto.
     *
     * @param touristId The ID of the tourist.
     * @return A PreferenceDto containing the current preferences, or null if not found.
     */
    public PreferenceDto getPreferences(String touristId) {
        System.out.println("[Service] Getting preferences for tourist: " + touristId);
        // Call to PreferencesRepository (findByTouristId)
        Preference preference = preferencesRepository.findByTouristId(touristId);

        if (preference != null) {
            // Convert Preference entity to PreferenceDto
            System.out.println("[Service] Converting Preference entity to PreferenceDto.");
            return new PreferenceDto(
                    preference.getTouristId(),
                    preference.getTheme(),
                    preference.getLanguage(),
                    preference.getTimezone()
            );
        }
        System.out.println("[Service] No preferences found for tourist: " + touristId + ", returning default/null.");
        // If no preferences exist, return a default DTO or null depending on requirements.
        // For this example, we'll return a default if not found.
        return new PreferenceDto(touristId, "default", "en", "UTC");
    }

    /**
     * Updates a tourist's preferences.
     * Corresponds to the sequence diagram step where PreferenceManagementService
     * converts PreferenceDto to Preference entity and calls save on PreferencesRepository.
     *
     * @param touristId The ID of the tourist whose preferences are being updated.
     * @param newPreferences The PreferenceDto containing the updated preference data.
     * @return true if the preferences were updated successfully, false otherwise.
     */
    public boolean updatePreferences(String touristId, PreferenceDto newPreferences) {
        System.out.println("[Service] Updating preferences for tourist: " + touristId + " with data: " + newPreferences);
        if (newPreferences == null || !touristId.equals(newPreferences.getTouristId())) {
            System.err.println("[Service] Invalid DTO or touristId mismatch for update.");
            return false;
        }

        // Retrieve existing preference to get its ID, or create a new one if it doesn't exist
        Preference existingPreference = preferencesRepository.findByTouristId(touristId);

        Preference preferenceToSave;
        if (existingPreference != null) {
            // Update existing preference entity
            existingPreference.setTheme(newPreferences.getTheme());
            existingPreference.setLanguage(newPreferences.getLanguage());
            existingPreference.setTimezone(newPreferences.getTimezone());
            preferenceToSave = existingPreference;
        } else {
            // Create a new preference entity if none exists
            preferenceToSave = new Preference(
                    newPreferences.getTouristId(),
                    newPreferences.getTheme(),
                    newPreferences.getLanguage(),
                    newPreferences.getTimezone()
            );
        }

        // Call to PreferencesRepository (save)
        Preference savedPreference = preferencesRepository.save(preferenceToSave);
        return savedPreference != null;
    }
}