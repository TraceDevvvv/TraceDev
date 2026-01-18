package com.example.entity;

/**
 * Entity representing a refreshment point with its operational details.
 */
public class RefreshmentPoint {
    private String id;
    private String name;
    private String location;
    private String operatingHours;
    private String contactInfo;

    public RefreshmentPoint() {}

    public RefreshmentPoint(String id, String name, String location, String operatingHours, String contactInfo) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.operatingHours = operatingHours;
        this.contactInfo = contactInfo;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(String hours) {
        this.operatingHours = hours;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String info) {
        this.contactInfo = info;
    }

    /**
     * Validates the entity's required fields.
     */
    public boolean validate() {
        return id != null && !id.trim().isEmpty() &&
               name != null && !name.trim().isEmpty() &&
               location != null && !location.trim().isEmpty();
    }
}