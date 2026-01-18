package com.culturalheritage.service;

import com.culturalheritage.model.CulturalHeritageObject;
import com.culturalheritage.exception.InvalidCulturalHeritageDataException;

/**
 * Validator for cultural heritage object data.
 * This class provides methods to validate the integrity and correctness
 * of CulturalHeritageObject attributes before they are persisted.
 */
public class CulturalHeritageValidator {

    /**
     * Validates a CulturalHeritageObject.
     * Checks for null or empty fields and ensures numerical values are within reasonable bounds.
     *
     * @param object The CulturalHeritageObject to validate.
     * @throws InvalidCulturalHeritageDataException if any validation rule is violated.
     */
    public void validate(CulturalHeritageObject object) {
        if (object == null) {
            throw new InvalidCulturalHeritageDataException("Cultural Heritage Object cannot be null.");
        }
        if (object.getId() == null || object.getId().trim().isEmpty()) {
            throw new InvalidCulturalHeritageDataException("ID cannot be null or empty.");
        }
        if (object.getName() == null || object.getName().trim().isEmpty()) {
            throw new InvalidCulturalHeritageDataException("Name cannot be null or empty.");
        }
        if (object.getDescription() == null || object.getDescription().trim().isEmpty()) {
            throw new InvalidCulturalHeritageDataException("Description cannot be null or empty.");
        }
        if (object.getOrigin() == null || object.getOrigin().trim().isEmpty()) {
            throw new InvalidCulturalHeritageDataException("Origin cannot be null or empty.");
        }
        // Assuming a reasonable range for years, e.g., between -5000 (5000 BC) and current year + 100
        // This can be adjusted based on specific requirements.
        int currentYear = java.time.Year.now().getValue();
        if (object.getYear() < -5000 || object.getYear() > (currentYear + 100)) {
            throw new InvalidCulturalHeritageDataException("Year must be between -5000 and " + (currentYear + 100) + ".");
        }
    }
}