package com.example.repository;

import com.example.model.Preference;

/**
 * JPA implementation of PreferenceRepository.
 * Simulates database operations as per sequence diagram.
 */
public class JpaPreferenceRepository implements PreferenceRepository {
    @Override
    public Preference findByTouristId(String touristId) {
        // Simulating SELECT preference FROM DB
        System.out.println("SELECT preference FROM database WHERE touristId = " + touristId);
        // In a real application, this would use JPA to query the database.
        // For demo, we return a dummy object.
        // Assumption: preference ID equals tourist ID.
        return new Preference(touristId, "en", "UTC", true, false);
    }

    @Override
    public Preference save(Preference preference) {
        // Simulating UPDATE/INSERT preference in DB
        System.out.println("UPDATE preference in database: " + preference);
        // In a real application, this would persist the entity.
        // For demo, we just return the same object.
        return preference;
    }
}