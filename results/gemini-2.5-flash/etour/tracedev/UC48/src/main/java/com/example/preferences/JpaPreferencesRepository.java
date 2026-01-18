package com.example.preferences;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Concrete implementation of PreferencesRepository, simulating a JPA-like repository
 * using an in-memory HashMap for demonstration purposes.
 * This class handles the "Database" interactions from the sequence diagram.
 */
public class JpaPreferencesRepository implements PreferencesRepository {

    // Simulates a database table for Preferences, mapping touristId to Preference objects.
    // In a real application, this would interact with a database via JPA.
    private final Map<String, Preference> preferencesStore = new HashMap<>();
    // Used to simulate auto-incrementing primary keys for new Preference objects.
    private final AtomicLong idCounter = new AtomicLong();

    /**
     * Initializes the repository with some dummy data for demonstration.
     */
    public JpaPreferencesRepository() {
        // Pre-populate with some data
        Preference defaultPref = new Preference(idCounter.incrementAndGet(), "tourist123", "light", "en", "GMT+1");
        preferencesStore.put(defaultPref.getTouristId(), defaultPref);

        Preference otherPref = new Preference(idCounter.incrementAndGet(), "tourist456", "dark", "es", "America/New_York");
        preferencesStore.put(otherPref.getTouristId(), otherPref);
    }

    /**
     * Finds a Preference object by the unique tourist ID.
     * Corresponds to `queryPreferences(touristId)` in the sequence diagram.
     *
     * @param touristId The unique ID of the tourist.
     * @return The Preference object if found, null otherwise.
     */
    @Override
    public Preference findByTouristId(String touristId) {
        System.out.println("[DB] Querying preferences for tourist: " + touristId);
        // Simulate database lookup
        Preference found = preferencesStore.get(touristId);
        if (found != null) {
            System.out.println("[DB] Found preference: " + found);
            // Return a copy to simulate detaching from persistence context if necessary, or just the object
            return new Preference(found.getId(), found.getTouristId(), found.getTheme(), found.getLanguage(), found.getTimezone());
        }
        System.out.println("[DB] No preferences found for tourist: " + touristId);
        return null; // No preference found for this tourist
    }

    /**
     * Saves a Preference object, either creating a new one or updating an existing one.
     * Corresponds to `persistPreference(preferenceEntity)` in the sequence diagram.
     *
     * @param preference The Preference object to save.
     * @return The saved Preference object, potentially with an updated ID if it was new.
     */
    @Override
    public Preference save(Preference preference) {
        if (preference == null) {
            System.err.println("[DB] Attempted to save a null preference.");
            return null;
        }

        Preference existing = preferencesStore.get(preference.getTouristId());

        if (existing == null) {
            // It's a new preference, assign a new ID
            preference.setId(idCounter.incrementAndGet());
            preferencesStore.put(preference.getTouristId(), preference);
            System.out.println("[DB] Persisted new preference: " + preference);
        } else {
            // It's an existing preference, update its fields
            // Ensure the ID remains the same for existing entries
            preference.setId(existing.getId()); // Use the existing ID if for some reason it was modified
            preferencesStore.put(preference.getTouristId(), preference); // Overwrite with updated preference
            System.out.println("[DB] Updated existing preference: " + preference);
        }
        // Return a copy to simulate object being managed by persistence context or returned from save operation
        return new Preference(preference.getId(), preference.getTouristId(), preference.getTheme(), preference.getLanguage(), preference.getTimezone());
    }
}