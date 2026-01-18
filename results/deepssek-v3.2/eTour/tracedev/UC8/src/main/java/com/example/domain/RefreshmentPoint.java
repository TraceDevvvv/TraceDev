package com.example.domain;

import com.example.dto.RefreshmentPointUpdateDTO;
import java.util.Map;
import java.util.HashMap;

/**
 * Core domain entity representing a refreshment point.
 * Includes attributes and business logic as per the class diagram.
 */
public class RefreshmentPoint {
    private String id;
    private String name;
    private String description;
    private String location;
    private RefreshmentPointStatus status;
    private Map<String, Object> otherAttributes;

    public RefreshmentPoint(String id) {
        this.id = id;
        this.otherAttributes = new HashMap<>();
        // Default values - assumptions
        this.name = "";
        this.description = "";
        this.location = "";
        this.status = RefreshmentPointStatus.ACTIVE;
    }

    // Full constructor for internal use (assumed for repository)
    public RefreshmentPoint(String id, String name, String description, String location,
                            RefreshmentPointStatus status, Map<String, Object> otherAttributes) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.status = status;
        this.otherAttributes = otherAttributes != null ? new HashMap<>(otherAttributes) : new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public RefreshmentPointStatus getStatus() {
        return status;
    }

    public Object getAttribute(String key) {
        return otherAttributes.get(key);
    }

    /**
     * Updates the details of this refreshment point based on the provided DTO.
     * Only updates fields that are set in the DTO.
     */
    public void updateDetails(RefreshmentPointUpdateDTO updateData) {
        if (updateData.getUpdatedName() != null && !updateData.getUpdatedName().isEmpty()) {
            this.name = updateData.getUpdatedName();
        }
        if (updateData.getUpdatedDescription() != null) {
            this.description = updateData.getUpdatedDescription();
        }
        if (updateData.getUpdatedLocation() != null && !updateData.getUpdatedLocation().isEmpty()) {
            this.location = updateData.getUpdatedLocation();
        }
        Map<String, String> updatedAttrs = updateData.getUpdatedAttributes();
        if (updatedAttrs != null) {
            for (Map.Entry<String, String> entry : updatedAttrs.entrySet()) {
                otherAttributes.put(entry.getKey(), entry.getValue());
            }
        }
    }

    public void markActive() {
        this.status = RefreshmentPointStatus.ACTIVE;
    }

    public void markInactive() {
        this.status = RefreshmentPointStatus.INACTIVE;
    }

    public boolean isValid() {
        // Simple validation: id, name, and location must not be empty.
        return id != null && !id.isEmpty() &&
               name != null && !name.isEmpty() &&
               location != null && !location.isEmpty();
    }

    /**
     * Fetches the latest data from the point of rest.
     * In a real scenario, this might involve an external call.
     * For now, returns a copy of current attributes.
     */
    public Map<String, Object> fetchLatestData() {
        return new HashMap<>(otherAttributes);
    }

    // Setters for repository and testing (assumed)
    void setName(String name) {
        this.name = name;
    }

    void setDescription(String description) {
        this.description = description;
    }

    void setLocation(String location) {
        this.location = location;
    }

    void setStatus(RefreshmentPointStatus status) {
        this.status = status;
    }

    void setOtherAttributes(Map<String, Object> otherAttributes) {
        this.otherAttributes = otherAttributes != null ? new HashMap<>(otherAttributes) : new HashMap<>();
    }
}