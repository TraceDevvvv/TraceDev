package com.etour.preferences.service;

import com.etour.preferences.dto.PreferenceDTO;
import com.etour.preferences.exception.InvalidPreferenceDataException;
import com.etour.preferences.exception.PreferenceNotFoundException;
import com.etour.preferences.model.Preference;
import com.etour.preferences.repository.PreferenceRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Service layer for managing user preferences.
 * This class encapsulates the business logic for retrieving, updating,
 * and validating generic personal preferences.
 */
@Service
public class PreferenceService {

    private final PreferenceRepository preferenceRepository;
    private final ObjectMapper objectMapper; // Used for JSON serialization/deserialization

    /**
     * Constructs a new PreferenceService with the given repository and ObjectMapper.
     *
     * @param preferenceRepository The repository for accessing preference data.
     * @param objectMapper The Jackson ObjectMapper for JSON processing.
     */
    @Autowired
    public PreferenceService(PreferenceRepository preferenceRepository, ObjectMapper objectMapper) {
        this.preferenceRepository = preferenceRepository;
        this.objectMapper = objectMapper;
    }

    /**
     * Retrieves the generic personal preferences for a given user ID.
     *
     * @param userId The unique identifier of the user.
     * @return A {@link PreferenceDTO} containing the user's preferences.
     * @throws PreferenceNotFoundException if no preferences are found for the given user ID.
     */
    @Transactional(readOnly = true)
    public PreferenceDTO getPreferences(Long userId) {
        // Find the preference entity by userId
        Optional<Preference> preferenceOptional = preferenceRepository.findByUserId(userId);

        // If preferences are not found, throw an exception
        if (preferenceOptional.isEmpty()) {
            throw new PreferenceNotFoundException("Preferences not found for user ID: " + userId);
        }

        Preference preference = preferenceOptional.get();
        // Convert the JSON string settings back to a Map for the DTO
        Map<String, String> settingsMap = new HashMap<>();
        if (preference.getSettingsJson() != null && !preference.getSettingsJson().isEmpty()) {
            try {
                settingsMap = objectMapper.readValue(preference.getSettingsJson(), Map.class);
            } catch (JsonProcessingException e) {
                // Log the error and return an empty map or rethrow as a custom exception
                // For now, we'll log and proceed with an empty map to avoid breaking the flow
                System.err.println("Error deserializing preferences for user " + userId + ": " + e.getMessage());
            }
        }

        // Create and return a PreferenceDTO
        return new PreferenceDTO(preference.getUserId(), settingsMap);
    }

    /**
     * Updates the generic personal preferences for a given user.
     * If preferences for the user do not exist, new preferences are created.
     *
     * @param preferenceDTO The {@link PreferenceDTO} containing the updated preferences.
     * @return The updated {@link PreferenceDTO}.
     * @throws InvalidPreferenceDataException if the provided preference data is invalid.
     */
    @Transactional
    public PreferenceDTO updatePreferences(PreferenceDTO preferenceDTO) {
        // Validate the incoming DTO
        validatePreferenceDTO(preferenceDTO);

        // Find existing preferences or create a new Preference entity
        Preference preference = preferenceRepository.findByUserId(preferenceDTO.getUserId())
                .orElseGet(() -> {
                    Preference newPreference = new Preference();
                    newPreference.setUserId(preferenceDTO.getUserId());
                    return newPreference;
                });

        // Convert the settings Map from DTO to JSON string for storage
        String settingsJson = null;
        try {
            settingsJson = objectMapper.writeValueAsString(preferenceDTO.getSettings());
        } catch (JsonProcessingException e) {
            // Log the error and throw an InvalidPreferenceDataException
            System.err.println("Error serializing preferences for user " + preferenceDTO.getUserId() + ": " + e.getMessage());
            throw new InvalidPreferenceDataException("Failed to process preference settings: " + e.getMessage());
        }

        preference.setSettingsJson(settingsJson);

        // Save the updated/new preference entity
        Preference savedPreference = preferenceRepository.save(preference);

        // Convert the saved entity back to DTO for response
        Map<String, String> savedSettingsMap = new HashMap<>();
        if (savedPreference.getSettingsJson() != null && !savedPreference.getSettingsJson().isEmpty()) {
            try {
                savedSettingsMap = objectMapper.readValue(savedPreference.getSettingsJson(), Map.class);
            } catch (JsonProcessingException e) {
                System.err.println("Error deserializing saved preferences for user " + savedPreference.getUserId() + ": " + e.getMessage());
            }
        }

        return new PreferenceDTO(savedPreference.getUserId(), savedSettingsMap);
    }

    /**
     * Validates the incoming PreferenceDTO.
     * This method can be extended to include more specific business validation rules
     * for the preference settings (e.g., allowed values, format checks).
     *
     * @param preferenceDTO The DTO to validate.
     * @throws InvalidPreferenceDataException if validation fails.
     */
    private void validatePreferenceDTO(PreferenceDTO preferenceDTO) {
        if (preferenceDTO.getUserId() == null) {
            throw new InvalidPreferenceDataException("User ID must not be null.");
        }
        // Add more specific validation rules here if needed, e.g.,
        // checking if certain preference keys are present or if values are valid.
        if (preferenceDTO.getSettings() == null) {
            throw new InvalidPreferenceDataException("Preference settings map must not be null.");
        }
        // Example: Check if a 'language' preference is valid
        if (preferenceDTO.getSettings().containsKey("language")) {
            String language = preferenceDTO.getSettings().get("language");
            if (!isValidLanguage(language)) {
                throw new InvalidPreferenceDataException("Invalid language preference: " + language);
            }
        }
        // Example: Check if a 'theme' preference is valid
        if (preferenceDTO.getSettings().containsKey("theme")) {
            String theme = preferenceDTO.getSettings().get("theme");
            if (!isValidTheme(theme)) {
                throw new InvalidPreferenceDataException("Invalid theme preference: " + theme);
            }
        }
    }

    /**
     * Helper method to validate language preference.
     *
     * @param language The language string to validate.
     * @return true if the language is valid, false otherwise.
     */
    private boolean isValidLanguage(String language) {
        // In a real application, this would check against a predefined list of supported languages.
        return language != null && (language.equals("en") || language.equals("es") || language.equals("fr"));
    }

    /**
     * Helper method to validate theme preference.
     *
     * @param theme The theme string to validate.
     * @return true if the theme is valid, false otherwise.
     */
    private boolean isValidTheme(String theme) {
        // In a real application, this would check against a predefined list of supported themes.
        return theme != null && (theme.equals("light") || theme.equals("dark"));
    }
}