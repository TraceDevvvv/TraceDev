package com.etour.interfaceadapters;

/**
 * UI component that displays confirmation prompts and messages.
 * Responsible for step 2 of the flow: "System prompts the inclusion."
 */
public class ConfirmationDialog {

    /**
     * Displays a confirmation prompt for adding a site to bookmarks.
     * In a real UI this would block and wait for user input.
     * For this simulation we assume the tourist always confirms (returns true).
     */
    public boolean displayPrompt(String touristId, String siteId) {
        System.out.println("[Dialog] Add site " + siteId + " to bookmarks of tourist " + touristId + "? [Confirm/Cancel]");
        // Simulating that the tourist confirms.
        return true;
    }

    public void showSuccessMessage() {
        System.out.println("[Dialog] Site successfully added to bookmarks!");
    }

    public void showErrorMessage(String message) {
        System.err.println("[Dialog] Error: " + message);
    }
}