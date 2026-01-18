
package com.example.service;

import com.example.dto.SearchPreferencesDTO;
import com.example.model.SearchPreferences;
import com.example.repository.SearchPreferencesRepository;
import java.util.Date;

/**
 * Service layer for handling business logic related to tourist preferences.
 */
public class SearchPreferencesService {
    private SearchPreferencesRepository searchPreferencesRepository;

    public SearchPreferencesService(SearchPreferencesRepository searchPreferencesRepository) {
        this.searchPreferencesRepository = searchPreferencesRepository;
    }

    /**
     * Retrieves preferences for a tourist and converts to DTO.
     */
    public SearchPreferencesDTO getTouristPreferences(String touristId) {
        SearchPreferences entity = searchPreferencesRepository.findByTouristId(touristId);
        if (entity == null) {
            return null;
        }
        // Convert entity to DTO (as per sequence diagram step "convert entity to DTO")
        SearchPreferencesDTO dto = new SearchPreferencesDTO();
        dto.setLocationPreference(entity.getLocationPreference());
        dto.setActivityTypePreference(entity.getActivityTypePreference());
        dto.setBudgetRange(entity.getBudgetRange());
        dto.setTravelDates(entity.getTravelDates());
        return dto;
    }

    /**
     * Updates preferences for a tourist.
     * Returns true if update is successful.
     */
    public boolean updateTouristPreferences(String touristId, SearchPreferencesDTO dto) {
        // Validate preferences first
        if (!validatePreferences(dto)) {
            return false;
        }

        // Fetch existing preferences
        SearchPreferences existing = searchPreferencesRepository.findByTouristId(touristId);
        if (existing == null) {
            // If no existing preferences, create new entity
            SearchPreferences newPref = dto.toEntity();
            newPref.setTouristId(touristId);
            newPref.setPreferenceId("pref_" + System.currentTimeMillis());
            searchPreferencesRepository.save(newPref);
        } else {
            // Update existing preferences (as per sequence diagram)
            existing.setLocationPreference(dto.getLocationPreference());
            existing.setActivityTypePreference(dto.getActivityTypePreference());
            existing.setBudgetRange(dto.getBudgetRange());
            existing.setTravelDates(dto.getTravelDates());
            searchPreferencesRepository.save(existing);
        }
        return true;
    }

    /**
     * Validates the preference DTO.
     * Simple validation: budget must be positive, dates not in past, etc.
     */
    public boolean validatePreferences(SearchPreferencesDTO dto) {
        if (dto == null) {
            return false;
        }
        if (dto.getBudgetRange() <= 0) {
            return false;
        }
        if (dto.getTravelDates() != null && dto.getTravelDates().before(new Date())) {
            return false; // past date not allowed
        }
        // Additional validation can be added
        return true;
    }
}
