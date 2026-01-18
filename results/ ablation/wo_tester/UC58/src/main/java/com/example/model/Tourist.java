package com.example.model;

/**
 * Represents a Tourist user in the system.
 * Quality Requirement 'None': No specific quality requirements defined for this use case.
 */
public class Tourist {
    public String userId;
    public String userName;
    private String password;

    public Tourist(String userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    /**
     * Authenticates the tourist.
     * @return true if authentication succeeds, false otherwise.
     */
    public boolean authenticate() {
        // Simplified authentication logic: password should not be empty
        boolean result = password != null && !password.trim().isEmpty();
        // Return authentication result (sequence diagram m2)
        return result;
    }

    /**
     * Initiates the viewing of a site by its ID.
     * This method triggers the controller to handle the view site request.
     * @param id the site ID
     */
    public void selectSiteCard(String id) {
        // In a real scenario, this would likely call a controller method.
        // For simplicity, we assume the controller is invoked elsewhere (e.g., in main).
        System.out.println("Tourist " + userName + " selected site card with ID: " + id);
    }
}