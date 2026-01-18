package com.touristGuide.model;

/**
 * <<actor>> stereotype representing the tourist user.
 * <<trace>> Requirement 3: Participating Actor: Tourist
 */
public class TouristUser {
    private String userId;
    private Location location;

    public TouristUser(String userId, Location location) {
        this.userId = userId;
        this.location = location;
    }

    public boolean authenticated() {
        // Simplified: assume authenticated if userId is not null
        return userId != null;
    }

    public Location getCurrentLocation() {
        return location;
    }

    /**
     * Access personal area (requirement 6).
     */
    public void accessPersonalArea() {
        System.out.println("User " + userId + " accessed personal area.");
    }
}