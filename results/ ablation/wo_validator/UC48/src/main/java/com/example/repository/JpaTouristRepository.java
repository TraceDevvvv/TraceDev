package com.example.repository;

import com.example.model.Tourist;
import com.example.model.Preference;

/**
 * JPA implementation of TouristRepository.
 * Simulates database operations as per sequence diagram.
 */
public class JpaTouristRepository implements TouristRepository {
    @Override
    public Tourist findById(String id) {
        // Simulating SELECT tourist FROM DB
        System.out.println("SELECT tourist FROM database WHERE id = " + id);
        // In a real application, this would use JPA to query the database.
        // For demo, we return a dummy object.
        // Assumption: tourist has a preference.
        Preference pref = new Preference(id, "en", "UTC", true, false);
        return new Tourist(id, "tourist_" + id, "tourist@" + id + ".com", pref);
    }
}