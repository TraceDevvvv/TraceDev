package com.example.domain;

import java.util.List;

/**
 * Represents an agency operator who can manage tourists.
 */
public class AgencyOperator {
    private String username;
    private String password;
    private boolean loggedIn; // track login state

    public AgencyOperator(String username, String password) {
        this.username = username;
        this.password = password;
        this.loggedIn = false;
    }

    /**
     * Simulates operator login.
     * @return true if login is successful
     */
    public boolean login() {
        // In a real system, validate credentials against a database
        loggedIn = true;
        return true;
    }

    /**
     * Logs out the operator.
     */
    public void logout() {
        loggedIn = false;
    }

    /**
     * Search tourists based on criteria.
     * Uses SearchTouristController to perform the search.
     */
    public List<Tourist> searchTourist(String criteria, com.example.controllers.SearchTouristController controller) {
        return controller.searchTourist(criteria);
    }

    /**
     * Select a tourist by ID via SearchTouristController.
     */
    public Tourist selectTourist(String touristId, com.example.controllers.SearchTouristController controller) {
        return controller.findTouristById(touristId);
    }

    /**
     * Confirm deletion from the operator's perspective.
     * In the sequence, operator responds to a confirmation dialog.
     * Here we simulate operator giving confirmation.
     */
    public boolean confirmDeletion() {
        // In reality, the operator would respond via UI.
        // For simulation, assume operator always confirms.
        return true;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}