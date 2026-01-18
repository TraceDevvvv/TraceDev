package com.restaurant.operator;

/**
 * Represents a Restaurant Point Operator who interacts with the system
 * to manage refreshment points.
 * This class simulates the actor's authentication status.
 */
public class RestaurantOperator {
    private String username;
    private boolean isAuthenticated;

    /**
     * Constructs a new RestaurantOperator.
     *
     * @param username The username of the operator.
     */
    public RestaurantOperator(String username) {
        this.username = username;
        this.isAuthenticated = false; // Initially not authenticated
    }

    /**
     * Gets the username of the operator.
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Checks if the operator is authenticated.
     * @return true if authenticated, false otherwise.
     */
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    /**
     * Authenticates the operator.
     * In a real system, this would involve checking credentials.
     * For this simulation, we'll just set the status.
     * @param password The password for authentication (simulated).
     * @return true if authentication is successful, false otherwise.
     */
    public boolean authenticate(String password) {
        // Simulate authentication logic
        if ("admin123".equals(password)) { // Simple hardcoded password for simulation
            this.isAuthenticated = true;
            System.out.println("Operator '" + username + "' authenticated successfully.");
            return true;
        } else {
            this.isAuthenticated = false;
            System.out.println("Authentication failed for operator '" + username + "'.");
            return false;
        }
    }

    /**
     * Logs out the operator.
     */
    public void logout() {
        this.isAuthenticated = false;
        System.out.println("Operator '" + username + "' logged out.");
    }
}