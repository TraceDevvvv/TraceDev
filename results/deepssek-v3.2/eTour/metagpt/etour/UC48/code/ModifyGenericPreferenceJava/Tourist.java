package com.etour.preference;

/**
 * Represents an authenticated tourist/user in the ETOUR system.
 * This class holds user identification information and authentication status.
 */
public class Tourist {
    private final String username;
    private final String userId;
    private final boolean authenticated;
    
    /**
     * Constructs a Tourist with a username.
     * Generates a user ID based on username and sets authenticated to true.
     * @param username the tourist's username
     */
    public Tourist(String username) {
        this.username = username;
        this.userId = generateUserId(username);
        this.authenticated = true;
    }
    
    /**
     * Constructs a Tourist with all fields.
     * @param username the tourist's username
     * @param userId the tourist's unique identifier
     * @param authenticated whether the tourist is authenticated
     */
    public Tourist(String username, String userId, boolean authenticated) {
        this.username = username;
        this.userId = userId;
        this.authenticated = authenticated;
    }
    
    /**
     * Generates a user ID based on username.
     * In a real system, this would come from a database or authentication service.
     * @param username the username to generate ID from
     * @return a generated user ID
     */
    private String generateUserId(String username) {
        // Simple hash-based generation for simulation
        int hash = Math.abs(username.hashCode());
        return "USER_" + hash;
    }
    
    /**
     * Gets the tourist's username.
     * @return the username
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Gets the tourist's unique identifier.
     * @return the user ID
     */
    public String getUserId() {
        return userId;
    }
    
    /**
     * Checks if the tourist is authenticated.
     * @return true if authenticated, false otherwise
     */
    public boolean isAuthenticated() {
        return authenticated;
    }
    
    /**
     * Returns a string representation of the Tourist.
     * @return string containing username and user ID
     */
    @Override
    public String toString() {
        return "Tourist [username=" + username + ", userId=" + userId + ", authenticated=" + authenticated + "]";
    }
    
    /**
     * Checks if this Tourist is equal to another object.
     * Two tourists are equal if they have the same user ID.
     * @param obj the object to compare with
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Tourist other = (Tourist) obj;
        return userId.equals(other.userId);
    }
    
    /**
     * Returns a hash code based on the user ID.
     * @return hash code
     */
    @Override
    public int hashCode() {
        return userId.hashCode();
    }
}