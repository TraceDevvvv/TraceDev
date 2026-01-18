
package com.example.justice.repository.impl;

import com.example.justice.domain.Justice;
import com.example.justice.repository.JusticeRepository;

import java.util.Date; // Added import for java.util.Date
import java.util.HashMap;
import java.util.Map;

/**
 * Concrete implementation of JusticeRepository using an in-memory Map
 * to simulate a database for demonstration purposes.
 */
public class JusticeRepositoryImpl implements JusticeRepository {
    // Simulates a database table in memory
    private final Map<String, Justice> database = new HashMap<>();

    public JusticeRepositoryImpl() {
        // Initialize with some dummy data for demonstration
        System.out.println("JusticeRepositoryImpl: Initializing with dummy data.");
        // Make sure the initial date is in the past for realistic updates
        database.put("J101", new Justice("J101", new java.util.Date(System.currentTimeMillis() - 86400000L * 30), "PENDING")); // 30 days ago
        database.put("J102", new Justice("J102", new java.util.Date(System.currentTimeMillis() - 86400000L * 10), "APPROVED")); // 10 days ago
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Justice findById(String id) {
        System.out.println("JusticeRepositoryImpl: Searching for Justice with ID: " + id + " in DB.");
        // Returns a copy to simulate actual database behavior where objects are detached
        // and to prevent direct modification of the stored entity without saving.
        Justice found = database.get(id);
        return found != null ? deepCopy(found) : null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Justice save(Justice justice) {
        System.out.println("JusticeRepositoryImpl: Saving Justice with ID: " + justice.getId() + " to DB.");
        database.put(justice.getId(), deepCopy(justice)); // Store a copy
        return deepCopy(justice); // Return a copy of the saved state
    }

    /**
     * Helper method to create a deep copy of a Justice object.
     * This simulates how a JPA or ORM framework might return a managed entity
     * or how a new object is created from database results.
     *
     * @param original The Justice object to copy.
     * @return A new Justice object with the same state as the original.
     */
    private Justice deepCopy(Justice original) {
        if (original == null) {
            return null;
        }
        // Assuming Justice's constructor and simple fields allow for a direct copy
        return new Justice(original.getId(), (Date) original.getDateJustification().clone(), original.getStatus());
    }
}
