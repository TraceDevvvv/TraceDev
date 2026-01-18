package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In-memory implementation of RegistrationRepository.
 * Assumption: All registrations stored are pending.
 */
public class RegistrationRepositoryImpl implements RegistrationRepository {
    // In-memory data store
    private Map<String, Registration> registrationDataStore = new HashMap<>();

    @Override
    public Registration findById(String requestId) {
        return registrationDataStore.get(requestId);
    }

    @Override
    public void deleteById(String requestId) {
        registrationDataStore.remove(requestId);
    }

    @Override
    public List<Registration> findAllPending() {
        // Assumption: all stored registrations are pending.
        return new ArrayList<>(registrationDataStore.values());
    }

    /**
     * Helper method to add a registration (for testing or initialization).
     */
    public void addRegistration(Registration registration) {
        registrationDataStore.put(registration.getRequestId(), registration);
    }
}