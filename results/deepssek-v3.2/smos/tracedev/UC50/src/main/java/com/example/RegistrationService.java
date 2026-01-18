package com.example;

import java.util.List;

/**
 * Service for handling registration operations.
 */
public class RegistrationService {
    private UserRepository userRepository;
    private RegistrationRepository registrationRepository;

    public RegistrationService(UserRepository userRepository, RegistrationRepository registrationRepository) {
        this.userRepository = userRepository;
        this.registrationRepository = registrationRepository;
    }

    /**
     * Accepts a registration, activates the associated user, and deletes the request.
     * Must complete within 5 seconds (performance requirement REQ-PERF-001).
     * @param requestId the registration request ID
     */
    public void acceptRegistration(String requestId) {
        // Performance requirement: complete within 5 seconds.
        // The actual timing depends on the repository implementations.
        Registration registration = registrationRepository.findById(requestId);
        if (registration == null) {
            throw new IllegalArgumentException("Registration not found: " + requestId);
        }

        String associatedUserId = registration.getAssociatedUserId();
        User user = userRepository.findById(associatedUserId);
        if (user == null) {
            throw new IllegalArgumentException("User not found: " + associatedUserId);
        }

        // Activate user
        user.activate();

        // Save updated user
        userRepository.save(user);

        // Delete the registration request
        registrationRepository.deleteById(requestId);
    }

    /**
     * Retrieves all pending registrations.
     * @return list of pending registrations
     */
    public List<Registration> getPendingRegistrations() {
        return registrationRepository.findAllPending();
    }
}