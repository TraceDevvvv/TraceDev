package com.example.etour.service;

import com.example.etour.model.Tourist;
import com.example.etour.exceptions.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class for handling Tourist operations.
 */
public class TouristService {
    // In-memory storage for demonstration; replace with database in production.
    private Map<String, Tourist> touristDatabase = new HashMap<>();

    /**
     * Searches tourists based on a query string.
     * @param query search term (matches firstName, lastName, email, or passport)
     * @return list of matching tourists
     */
    public List<Tourist> searchTourists(String query) {
        if (query == null || query.trim().isEmpty()) {
            return new ArrayList<>(touristDatabase.values());
        }
        String lowerQuery = query.toLowerCase();
        return touristDatabase.values().stream()
                .filter(t ->
                        (t.getFirstName() != null && t.getFirstName().toLowerCase().contains(lowerQuery)) ||
                        (t.getLastName() != null && t.getLastName().toLowerCase().contains(lowerQuery)) ||
                        (t.getEmail() != null && t.getEmail().toLowerCase().contains(lowerQuery)) ||
                        (t.getPassportNumber() != null && t.getPassportNumber().toLowerCase().contains(lowerQuery))
                )
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a tourist by ID.
     * @param touristId the tourist's ID
     * @return the Tourist object
     * @throws TouristNotFoundException if tourist not found
     */
    public Tourist getTouristById(String touristId) throws TouristNotFoundException {
        Tourist tourist = touristDatabase.get(touristId);
        if (tourist == null) {
            throw new TouristNotFoundException("Tourist with ID " + touristId + " not found.");
        }
        return tourist;
    }

    /**
     * Updates an existing tourist.
     * @param touristId ID of the tourist to update
     * @param updatedTourist Tourist object with updated fields
     * @throws TouristNotFoundException if tourist not found
     * @throws ValidationException if validation fails
     */
    public void updateTourist(String touristId, Tourist updatedTourist)
            throws TouristNotFoundException, ValidationException {
        // Check if tourist exists
        if (!touristDatabase.containsKey(touristId)) {
            throw new TouristNotFoundException("Tourist with ID " + touristId + " not found.");
        }
        // Validate the updated data
        validateTourist(updatedTourist);
        // Update in database
        touristDatabase.put(touristId, updatedTourist);
    }

    /**
     * Validates tourist data.
     * @param tourist Tourist to validate
     * @throws ValidationException if validation fails
     */
    private void validateTourist(Tourist tourist) throws ValidationException {
        List<String> errors = new ArrayList<>();
        if (tourist.getFirstName() == null || tourist.getFirstName().trim().isEmpty()) {
            errors.add("First name is required.");
        }
        if (tourist.getLastName() == null || tourist.getLastName().trim().isEmpty()) {
            errors.add("Last name is required.");
        }
        if (tourist.getEmail() == null || tourist.getEmail().trim().isEmpty()) {
            errors.add("Email is required.");
        } else if (!isValidEmail(tourist.getEmail())) {
            errors.add("Invalid email format.");
        }
        if (tourist.getPhoneNumber() != null && !tourist.getPhoneNumber().trim().isEmpty()) {
            if (!isValidPhoneNumber(tourist.getPhoneNumber())) {
                errors.add("Invalid phone number format.");
            }
        }
        if (tourist.getDateOfBirth() != null && tourist.getDateOfBirth().isAfter(LocalDate.now())) {
            errors.add("Date of birth cannot be in the future.");
        }
        if (tourist.getPassportNumber() == null || tourist.getPassportNumber().trim().isEmpty()) {
            errors.add("Passport number is required.");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException("Validation failed: " + String.join(" ", errors));
        }
    }

    private boolean isValidEmail(String email) {
        // Simple regex for email validation
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }

    private boolean isValidPhoneNumber(String phone) {
        // Accept digits, spaces, plus, parentheses, hyphens
        return phone.matches("[+\\d\\s()-]+");
    }

    /**
     * Adds a tourist to the database (for initialization).
     * @param tourist Tourist to add
     */
    public void addTourist(Tourist tourist) {
        touristDatabase.put(tourist.getId(), tourist);
    }
}