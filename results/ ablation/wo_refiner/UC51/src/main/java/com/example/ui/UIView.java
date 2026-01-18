package com.example.ui;

/**
 * UI Boundary: Represents the user interface
 * Simulates UI interactions from the sequence diagram
 */
public class UIView {
    private String lastMessage;
    
    /**
     * Step 2 from sequence diagram: Prompt for inclusion
     */
    public void promptForInclusion() {
        System.out.println("UI: Do you want to add this site to your bookmarks?");
    }
    
    /**
     * Step 3 from sequence diagram: User confirms input
     */
    public void confirmInput() {
        System.out.println("User: Confirms addition");
    }
    
    /**
     * Displays confirmation or error message to tourist
     */
    public void displayMessage(String message) {
        this.lastMessage = message;
        System.out.println("UI Display: " + message);
    }
    
    public String getLastMessage() {
        return lastMessage;
    }
}