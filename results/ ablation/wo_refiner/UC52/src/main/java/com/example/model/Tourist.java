package com.example.model;

/**
 * Represents a Tourist who can remove bookmarked sites.
 */
public class Tourist {
    private String userId;

    public Tourist(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    /**
     * Called by controller to display removal prompt.
     */
    public void chooseSiteToDelete(Object features) {
        // This method is called by the controller to initiate the flow.
        // Actual action is performed in the controller's executeDeletionFlow.
        System.out.println("Tourist " + userId + " chooses site with features: " + features);
    }

    /**
     * Simulates user prompt for removal.
     */
    public boolean promptForRemoval() {
        // In a real system, this would be a UI interaction.
        // Assume user is prompted and returns true if they want to proceed.
        System.out.println("Tourist prompted for removal. Returning true to proceed.");
        return true;
    }

    /**
     * Simulates user confirmation of removal.
     */
    public boolean confirmRemoval() {
        // In a real system, this would be a UI interaction.
        // Assume user confirms.
        System.out.println("Tourist confirms removal.");
        return true;
    }

    /**
     * Simulates user cancellation.
     */
    public void cancelOperation() {
        System.out.println("Tourist cancels operation.");
    }
}