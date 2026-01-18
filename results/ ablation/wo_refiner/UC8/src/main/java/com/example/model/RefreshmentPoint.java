
package com.example.model;

import java.util.Map;

/**
 * Entity representing a refreshment point.
 */
public class RefreshmentPoint {
    private String id;
    private String name;
    private Location location;
    private PointStatus status;
    private Map<String, Object> attributes;
    private int version;

    public RefreshmentPoint(String id, String name, Location location, PointStatus status, Map<String, Object> attributes, int version) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.status = status;
        this.attributes = attributes;
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public PointStatus getStatus() {
        return status;
    }

    public boolean isActive() {
        return status == PointStatus.ACTIVE;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public int getVersion() {
        return version;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setStatus(PointStatus status) {
        this.status = status;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * Updates entity from form DTO.
     */
    public void updateFromForm(RefreshmentPointForm formData) {
        this.name = formData.getName();
        LocationDTO locDto = formData.getLocationData();
        if (locDto != null) {
            this.location = new Location(locDto.getLatitude(), locDto.getLongitude(), locDto.getAddress());
        }
        this.attributes = formData.getAttributes();
    }

    /**
     * Validates the refreshment point.
     */
    public ValidationResult validate() {
        ValidationResult result = new ValidationResult();
        if (id == null || id.trim().isEmpty()) {
            result.addError("Refreshment point ID is required");
        }
        if (name == null || name.trim().isEmpty()) {
            result.addError("Name is required");
        }
        if (location == null) {
            result.addError("Location is required");
        }
        return result;
    }

    public static class RefreshmentPointForm {
        private String name;
        private LocationDTO locationData;
        private Map<String, Object> attributes;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public LocationDTO getLocationData() {
            return locationData;
        }

        public void setLocationData(LocationDTO locationData) {
            this.locationData = locationData;
        }

        public Map<String, Object> getAttributes() {
            return attributes;
        }

        public void setAttributes(Map<String, Object> attributes) {
            this.attributes = attributes;
        }
    }
}
