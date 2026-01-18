package com.example.advancedsearch;

/**
 * Represents a Tourist in the system.
 * This class holds basic information about a tourist and their authentication status.
 */
public class Tourist {
    private String username;
    private boolean isAuthenticated;
    private Location currentLocation; // Represents the tourist's current location

    /**
     * Constructs a new Tourist object.
     *
     * @param username The username of the tourist.
     */
    public Tourist(String username) {
        this.username = username;
        this.isAuthenticated = false; // Initially not authenticated
        this.currentLocation = null; // Location is set later
    }

    /**
     * Authenticates the tourist.
     * In a real system, this would involve checking credentials. For this simulation,
     * we simply set the authentication status to true.
     *
     * @return true if authentication is successful, false otherwise.
     */
    public boolean authenticate() {
        // Simulate authentication logic
        System.out.println("Tourist '" + username + "' attempting to authenticate...");
        this.isAuthenticated = true; // Assume successful authentication for the use case
        System.out.println("Tourist '" + username + "' authenticated successfully.");
        return true;
    }

    /**
     * Checks if the tourist is authenticated.
     *
     * @return true if the tourist is authenticated, false otherwise.
     */
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    /**
     * Gets the username of the tourist.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the current location of the tourist.
     * This is crucial for location-based searches.
     *
     * @param currentLocation The current location of the tourist.
     */
    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    /**
     * Gets the current location of the tourist.
     *
     * @return The current location.
     */
    public Location getCurrentLocation() {
        return currentLocation;
    }
}