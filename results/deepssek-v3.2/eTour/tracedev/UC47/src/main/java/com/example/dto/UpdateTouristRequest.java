package com.example.dto;

import java.io.Serializable;

/**
 * Request object for updating tourist profile.
 * Contains tourist ID and new profile data.
 */
public class UpdateTouristRequest implements Serializable {
    private String touristId;
    private TouristProfileDTO profileData;

    // Default constructor
    public UpdateTouristRequest() {}

    // Parameterized constructor
    public UpdateTouristRequest(String touristId, TouristProfileDTO profileData) {
        this.touristId = touristId;
        this.profileData = profileData;
    }

    // Getters and setters
    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    public TouristProfileDTO getProfileData() {
        return profileData;
    }

    public void setProfileData(TouristProfileDTO profileData) {
        this.profileData = profileData;
    }
}