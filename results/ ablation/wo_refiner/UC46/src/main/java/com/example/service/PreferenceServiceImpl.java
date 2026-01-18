package com.example.service;

import com.example.model.PreferenceDTO;
import com.example.model.SearchPreference;
import com.example.model.Tourist;
import com.example.repository.PreferenceRepository;
import com.example.repository.TouristRepository;
import java.util.UUID;

/**
 * Implementation of PreferenceService.
 * Handles business logic and validation as per the sequence diagram.
 */
public class PreferenceServiceImpl implements PreferenceService {
    private PreferenceRepository preferenceRepository;
    private TouristRepository touristRepository;

    public PreferenceServiceImpl(PreferenceRepository preferenceRepository, TouristRepository touristRepository) {
        this.preferenceRepository = preferenceRepository;
        this.touristRepository = touristRepository;
    }

    @Override
    public SearchPreference loadPreferences(String touristId) {
        // Find tourist to get his preferences (assumes Tourist has reference to SearchPreference)
        Tourist tourist = touristRepository.findById(touristId)
                .orElseThrow(() -> new RuntimeException("Tourist not found"));
        SearchPreference pref = tourist.getPreferences();
        if (pref == null) {
            // If no preferences exist, create a default one
            pref = new SearchPreference(touristId);
            tourist.setPreferences(pref);
        }
        return pref;
    }

    @Override
    public String updatePreferences(String touristId, PreferenceDTO preference) {
        // Validate the DTO
        if (!validate(preference)) {
            throw new RuntimeException("Validation failed");
        }

        // Load existing preference or create new
        SearchPreference existing = loadPreferences(touristId);
        existing.updateFromDTO(preference);

        // Simulate a confirmation ID generation (in real case might be saved to DB)
        String confirmationId = UUID.randomUUID().toString();
        // In a full implementation, we might store the pending update with this confirmationId.
        // For simplicity, we just save the preference now.
        preferenceRepository.save(existing);
        return confirmationId;
    }

    @Override
    public void confirmUpdate(String confirmationId) {
        // In a real scenario, we would retrieve pending update by confirmationId and apply it.
        // Here we just assume the update was already saved and we do nothing.
        System.out.println("Update confirmed for confirmationId: " + confirmationId);
    }

    @Override
    public void handleConnectionInterruption() {
        // Simulate handling connection interruption as per REQ-014
        System.err.println("Handling connection interruption...");
        // Could implement retry logic, logging, etc.
    }

    /**
     * Validates the preference DTO.
     * @param preference the DTO to validate
     * @return true if valid, false otherwise
     */
    protected boolean validate(PreferenceDTO preference) {
        if (preference == null) return false;
        if (preference.getMaxDistance() != null && preference.getMaxDistance() < 0) return false;
        if (preference.getRatingThreshold() != null &&
                (preference.getRatingThreshold() < 0.0 || preference.getRatingThreshold() > 5.0)) {
            return false;
        }
        // Add more validation as needed
        return true;
    }
}