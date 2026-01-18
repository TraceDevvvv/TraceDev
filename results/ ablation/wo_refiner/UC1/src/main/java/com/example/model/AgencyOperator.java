package com.example.model;

/**
 * Represents an agency operator with login capabilities.
 * Added to satisfy requirement REQ-003, REQ-004.
 */
public class AgencyOperator {
    private String username;
    private boolean loggedIn;

    public AgencyOperator(String username) {
        this.username = username;
        this.loggedIn = false;
    }

    public boolean login(String credentials) {
        // Simplified login logic: assume credentials are valid if not empty
        if (credentials != null && !credentials.trim().isEmpty()) {
            loggedIn = true;
            return true;
        }
        return false;
    }

    public void logout() {
        loggedIn = false;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Added method for viewing cultural heritage list.
    public void viewCulturalHeritageList() {
        System.out.println("Agency Operator views the cultural heritage list.");
    }

    // Added method for selecting cultural heritage and activating delete function.
    public void selectCulturalHeritageAndActivateDeleteFunction() {
        System.out.println("Agency Operator selects a cultural heritage item and activates delete function.");
    }

    // Added method for submitting confirmation with token.
    public void submitConfirmationWithToken(String token) {
        System.out.println("Agency Operator submits confirmation with token: " + token);
    }

    // Added method for cancel operation.
    public void cancelOperation() {
        System.out.println("Agency Operator cancels the operation.");
    }
}