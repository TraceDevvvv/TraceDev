package com.example.touristapp;

/**
 * Represents a Tourist actor in the system.
 * This class holds information about the tourist, primarily their authentication status.
 */
public class Tourist {
    private String username;
    private boolean isAuthenticated;

    /**
     * Constructs a new Tourist object.
     *
     * @param username The username of the tourist.
     * @param isAuthenticated The authentication status of the tourist.
     */
    public Tourist(String username, boolean isAuthenticated) {
        this.username = username;
        this.isAuthenticated = isAuthenticated;
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
     * Checks if the tourist is authenticated.
     *
     * @return true if the tourist is authenticated, false otherwise.
     */
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    /**
     * Sets the authentication status of the tourist.
     * This method could be used after a successful login or logout.
     *
     * @param authenticated The new authentication status.
     */
    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }

    @Override
    public String toString() {
        return "Tourist{" +
               "username='" + username + '\'' +
               ", isAuthenticated=" + isAuthenticated +
               '}';
    }
}