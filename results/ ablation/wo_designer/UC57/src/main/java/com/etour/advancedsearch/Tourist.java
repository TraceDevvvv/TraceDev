package com.etour.advancedsearch;

/**
 * Represents a Tourist user in the system.
 * Assumes the tourist has already been authenticated.
 */
public class Tourist {
    private String username;
    private String email;
    private Location currentLocation;

    public Tourist(String username, String email) {
        this.username = username;
        this.email = email;
        // In a real application, the location might be fetched from GPS or IP
        this.currentLocation = null;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }
}