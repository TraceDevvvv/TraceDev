package com.etour.model;

/**
 * Holds tourist details.
 * Added to satisfy requirement Flow of Events (1, 3)
 */
public class TouristInfo {
    private String userId;
    private String name;
    private String email;

    public TouristInfo(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}