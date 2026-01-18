package com.etour.dto;

import java.util.Map;
import java.util.HashMap;

/**
 * Data Transfer Object for Tourist.
 * Represents tourist data as it moves between layers.
 */
public class TouristDTO {
    private String touristId;
    private String name;
    private String email;
    private String phoneNumber;
    private Map<String, String> otherAttributes;

    public TouristDTO() {
        this.otherAttributes = new HashMap<>();
    }

    public TouristDTO(String touristId, String name, String email, String phoneNumber) {
        this.touristId = touristId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.otherAttributes = new HashMap<>();
    }

    // Getters and setters
    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Map<String, String> getOtherAttributes() {
        return otherAttributes;
    }

    public void setOtherAttributes(Map<String, String> otherAttributes) {
        this.otherAttributes = otherAttributes;
    }

    @Override
    public String toString() {
        return "TouristDTO{" +
                "touristId='" + touristId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", otherAttributes=" + otherAttributes +
                '}';
    }
}