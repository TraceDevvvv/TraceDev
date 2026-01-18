package com.example.touristapp.service;

import com.example.touristapp.dto.SearchPreferencesDTO;
import com.example.touristapp.model.SearchPreferences;
import com.example.touristapp.repository.ISearchPreferencesRepository;

/**
 * Use Case service for editing a tourist's search preferences.
 * This class orchestrates the business logic for retrieving and updating preferences.\n */
public class EditSearchPreferencesUseCase {

    private ISearchPreferencesRepository searchPreferencesRepository;

    /**
     * Constructor for EditSearchPreferencesUseCase.\n     * @param searchPreferencesRepository The repository for accessing search preferences data.\n     */
    public EditSearchPreferencesUseCase(ISearchPreferencesRepository searchPreferencesRepository) {
        this.searchPreferencesRepository = searchPreferencesRepository;
    }

    /**
     * Retrieves the search preferences for a given tourist ID.\n     * @param touristId The ID of the tourist whose preferences are to be retrieved.\n     * @return A SearchPreferencesDTO containing the tourist's preferences, or null if not found.\n     */
    public SearchPreferencesDTO getPreferences(String touristId) {
        System.out.println("[EditSearchPreferencesUseCase] Getting preferences for tourist: " + touristId);
        SearchPreferences preferencesEntity = searchPreferencesRepository.findByTouristId(touristId);
        if (preferencesEntity != null) {
            // Convert the entity to a DTO before returning
            return preferencesEntity.toDTO();
        }
        System.out.println("[EditSearchPreferencesUseCase] No preferences found for tourist: " + touristId);
        return null;
    }

    /**
     * Updates the search preferences for a given tourist.\n     * @param touristId The ID of the tourist whose preferences are to be updated.\n     * @param newPreferences The DTO containing the updated preferences data.\n     * @throws Exception if there's an issue updating or saving preferences (e.g., repository error).\n     */
    public void updatePreferences(String touristId, SearchPreferencesDTO newPreferences) throws Exception {
        System.out.println("[EditSearchPreferencesUseCase] Updating preferences for tourist: " + touristId + " with new data: " + newPreferences);
        SearchPreferences existingPreferences = searchPreferencesRepository.findByTouristId(touristId);

        if (existingPreferences == null) {
            // If preferences don't exist, we might create new ones, or throw an error.
            // For this use case, we assume preferences already exist to be updated.
            String preferencesId = java.util.UUID.randomUUID().toString(); // Generate new ID
            existingPreferences = new SearchPreferences(preferencesId, touristId, newPreferences.getDestination(),
                                                        newPreferences.getBudgetRange(), newPreferences.getTravelDates());
            System.out.println("[EditSearchPreferencesUseCase] No existing preferences found, creating new ones.");
        } else {
            // Update the existing entity with the new DTO data
            existingPreferences.updatePreferences(newPreferences);
            System.out.println("[EditSearchPreferencesUseCase] Existing preferences updated in entity.");
        }

        // Save the updated (or newly created) preferences back to the repository
        searchPreferencesRepository.save(existingPreferences);
        System.out.println("[EditSearchPreferencesUseCase] Preferences saved successfully for tourist: " + touristId);
    }
}