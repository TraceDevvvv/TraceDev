package com.example.model;

import java.util.Map;

/**
 * Represents a point of restaurant in the system.
 * Contains all relevant details about a restaurant point.
 */
public class PointOfRestaurant {
    private String id;
    private String name;
    private String address;
    private String contactInfo;
    private String operatingHours;

    /**
     * Default constructor.
     */
    public PointOfRestaurant() {
    }

    /**
     * Constructor with all fields.
     */
    public PointOfRestaurant(String id, String name, String address, String contactInfo, String operatingHours) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contactInfo = contactInfo;
        this.operatingHours = operatingHours;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(String operatingHours) {
        this.operatingHours = operatingHours;
    }

    /**
     * Validates the data of this point.
     * @return true if data is valid, false otherwise.
     */
    public boolean validateData() {
        // Basic validation: none of the fields should be null or empty
        return id != null && !id.trim().isEmpty() &&
               name != null && !name.trim().isEmpty() &&
               address != null && !address.trim().isEmpty() &&
               contactInfo != null && !contactInfo.trim().isEmpty() &&
               operatingHours != null && !operatingHours.trim().isEmpty();
    }

    /**
     * Converts the point data to a Map for form display.
     * @return Map containing the point's data.
     */
    public Map<String, String> toMap() {
        return Map.of(
            "id", id,
            "name", name,
            "address", address,
            "contactInfo", contactInfo,
            "operatingHours", operatingHours
        );
    }

    /**
     * Updates fields from a map of data.
     * @param data Map containing new values.
     */
    public void updateFromMap(Map<String, String> data) {
        if (data.containsKey("name")) {
            this.name = data.get("name");
        }
        if (data.containsKey("address")) {
            this.address = data.get("address");
        }
        if (data.containsKey("contactInfo")) {
            this.contactInfo = data.get("contactInfo");
        }
        if (data.containsKey("operatingHours")) {
            this.operatingHours = data.get("operatingHours");
        }
    }

    /**
     * Updates the point with provided data.
     * @param updatedData Map containing updated values.
     */
    public void updatePoint(Map<String, String> updatedData) {
        updateFromMap(updatedData);
    }
}