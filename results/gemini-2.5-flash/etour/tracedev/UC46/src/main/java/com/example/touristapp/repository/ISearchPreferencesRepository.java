package com.example.touristapp.repository;

import com.example.touristapp.model.SearchPreferences;

/**
 * Repository interface for managing SearchPreferences data persistence.
 * Defines the contract for data access operations related to SearchPreferences.
 */
public interface ISearchPreferencesRepository {

    /**
     * Finds search preferences by a given tourist ID.
     * @param touristId The ID of the tourist.\n     * @return The SearchPreferences object associated with the tourist, or null if not found.\n     */
    SearchPreferences findByTouristId(String touristId);

    /**
     * Saves or updates a SearchPreferences object.\n     * @param preferences The SearchPreferences object to save.\n     * @throws Exception if there is an error during saving (e.g., connection issue).\n     */
    void save(SearchPreferences preferences) throws Exception;
}