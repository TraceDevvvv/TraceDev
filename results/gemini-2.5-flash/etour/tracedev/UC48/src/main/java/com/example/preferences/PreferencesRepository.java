package com.example.preferences;

/**
 * Interface defining the contract for data access operations related to user preferences.
 * This abstracts the underlying data storage mechanism.
 */
public interface PreferencesRepository {

    /**
     * Finds a Preference object by the unique tourist ID.
     *
     * @param touristId The unique ID of the tourist.
     * @return The Preference object if found, null otherwise.
     */
    Preference findByTouristId(String touristId);

    /**
     * Saves a Preference object, either creating a new one or updating an existing one.
     *
     * @param preference The Preference object to save.
     * @return The saved Preference object, potentially with an updated ID if it was new.
     */
    Preference save(Preference preference);
}