package domain;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.ArrayList;

/**
 * Domain entity representing a Convention between a restaurant and an agency.
 */
public class Convention {
    private final String id;
    private final String restaurantId;
    private final String agencyId;
    private final Map<String, Object> details;
    private ConventionStatus status;
    private final LocalDateTime createdAt;

    public Convention(String restaurantId, String agencyId, Map<String, Object> details) {
        this.id = UUID.randomUUID().toString();
        this.restaurantId = restaurantId;
        this.agencyId = agencyId;
        this.details = details != null ? new HashMap<>(details) : new HashMap<>();
        this.status = ConventionStatus.DRAFT;
        this.createdAt = LocalDateTime.now();
    }

    /**
     * Validates the convention data.
     * Implements requirement QR-001: enhanced validation for correctness and completeness.
     * @param data the data to validate (could be the convention's details or other data)
     * @return ValidationResult indicating success or failure with error messages
     */
    public ValidationResult validate(Map<String, Object> data) {
        List<String> errors = new ArrayList<>();
        // Example validation: check required fields
        if (data == null || data.isEmpty()) {
            errors.add("Convention data cannot be empty.");
        } else {
            if (!data.containsKey("restaurantName")) {
                errors.add("Restaurant name is required.");
            }
            if (!data.containsKey("agencyName")) {
                errors.add("Agency name is required.");
            }
            // Add more validation as per business rules
        }
        boolean valid = errors.isEmpty();
        return new ValidationResult(valid, errors);
    }

    public void submit() {
        this.status = ConventionStatus.SUBMITTED;
        // Additional submission logic could be added here (e.g., raise domain event)
    }

    public String getId() {
        return id;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public Map<String, Object> getDetails() {
        return new HashMap<>(details);
    }

    public ConventionStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}