package com.example.service;

import com.example.dto.PreferenceDTO;
import com.example.model.Preference;
import com.example.model.Tourist;
import com.example.repository.PreferenceRepository;
import com.example.repository.TouristRepository;

/**
 * Implementation of PreferenceService.
 * Contains business logic for preference operations.
 */
public class PreferenceServiceImpl implements PreferenceService {
    private final PreferenceRepository preferenceRepository;
    private final TouristRepository touristRepository;

    public PreferenceServiceImpl(PreferenceRepository preferenceRepository,
                                 TouristRepository touristRepository) {
        this.preferenceRepository = preferenceRepository;
        this.touristRepository = touristRepository;
    }

    @Override
    public PreferenceDTO getPreferences(String touristId) {
        // Step 1: Fetch Tourist (as per sequence diagram)
        Tourist tourist = touristRepository.findById(touristId);
        if (tourist == null) {
            // Tourist not found; return default DTO or throw exception.
            // Assuming we return a default DTO for simplicity.
            return new PreferenceDTO();
        }

        // Step 2: Fetch Preference for that tourist
        Preference preference = preferenceRepository.findByTouristId(touristId);
        if (preference == null) {
            // No preference record found; return default DTO.
            return new PreferenceDTO();
        }

        // Step 3: Convert Preference to DTO and return
        return convertToDTO(preference);
    }

    @Override
    public boolean updatePreferences(String touristId, PreferenceDTO dto) {
        try {
            // Step 1: Fetch Tourist (as per sequence diagram)
            Tourist tourist = touristRepository.findById(touristId);
            if (tourist == null) {
                System.err.println("Tourist with ID " + touristId + " not found.");
                return false;
            }

            // Step 2: Fetch Preference for that tourist
            Preference preference = preferenceRepository.findByTouristId(touristId);
            if (preference == null) {
                // If no existing preference, create a new one.
                preference = new Preference();
                preference.setId(touristId); // Assuming preference ID equals tourist ID.
            }

            // Step 3: Update preference fields from DTO
            updatePreferenceFields(preference, dto);

            // Step 4: Save updated preference
            Preference saved = preferenceRepository.save(preference);
            return saved != null;
        } catch (Exception e) {
            // Simulating connection interruption (as per alternative flow in sequence diagram)
            System.err.println("Connection interrupted: " + e.getMessage());
            return false;
        }
    }

    /**
     * Updates a Preference entity with values from a DTO.
     * Called from updatePreferences as per sequence diagram.
     * @param preference the Preference entity to update
     * @param dto the source DTO
     */
    void updatePreferenceFields(Preference preference, PreferenceDTO dto) {
        preference.setLanguage(dto.getLanguage());
        preference.setTimezone(dto.getTimezone());
        preference.setNotificationEnabled(dto.isNotificationEnabled());
        preference.setAccessibilityMode(dto.isAccessibilityMode());
    }

    /**
     * Converts a Preference entity to a PreferenceDTO.
     * @param preference the entity to convert
     * @return the corresponding DTO
     */
    private PreferenceDTO convertToDTO(Preference preference) {
        return new PreferenceDTO(
                preference.getLanguage(),
                preference.getTimezone(),
                preference.isNotificationEnabled(),
                preference.isAccessibilityMode()
        );
    }
}