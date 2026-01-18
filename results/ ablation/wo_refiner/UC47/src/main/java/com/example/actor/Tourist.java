package com.example.actor;

import com.example.dto.TouristDTO;

/**
 * Actor representing a Tourist user.
 * This class is simplified because the actor is usually external.
 */
public class Tourist {
    private String userId;
    private String name;
    private String email;
    // ... other fields

    public Tourist(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public void updateProfile(TouristDTO data) {
        // Update profile with new data
        this.name = data.getName();
        this.email = data.getEmail();
        // ... update other fields
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
}