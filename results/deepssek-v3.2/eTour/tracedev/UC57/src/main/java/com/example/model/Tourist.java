package com.example.model;

import com.example.service.AuthenticationService;
import com.example.exception.ConnectionException;
import java.util.List;

/**
 * Represents a tourist user who can perform site searches.
 * Interacts with the AdvancedSearchForm and displays errors.
 */
public class Tourist {
    private String id;
    private String name;

    public Tourist(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * Gets the current location of the tourist.
     * In a real implementation, this might use device GPS.
     * For simplicity, returns a fixed location.
     */
    public Location getLocation() {
        // Assumption: returning a default location for demonstration
        return new Location(40.7128, -74.0060); // New York City coordinates
    }

    /**
     * Displays an error message to the tourist.
     * This could be a UI popup, console log, etc.
     */
    public void displayError(String message) {
        System.out.println("Error for Tourist " + name + ": " + message);
    }

    /**
     * Authenticates the tourist via AuthenticationService.
     * Not directly in sequence diagram but implied by class diagram.
     */
    public boolean authenticate(AuthenticationService authService) {
        return authService.authenticate(this);
    }

    public Location getLocationData() {
        return getLocation();
    }
}