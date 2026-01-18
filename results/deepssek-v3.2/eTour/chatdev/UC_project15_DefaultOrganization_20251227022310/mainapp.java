'''
Main application entry point. Handles navigation between search and modify frames.
Manages the flow according to the use case specification.
'''
package com.etour.agency;
import javax.swing.*;
import java.util.List;
public class MainApp {
    private static JFrame currentFrame;
    private static TouristService touristService;
    public static void main(String[] args) {
        // Initialize serv
        touristService = new TouristService();
        // Start with the search tourist screen (Step 1 of use case)
        showSearchTouristFrame();
    }
    /**
     * Display the search tourist frame (Use Case: SearchTourist)
     */
    public static void showSearchTouristFrame() {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        currentFrame = new SearchTouristFrame(touristService);
        currentFrame.setVisible(true);
    }
    /**
     * Display the modify tourist frame for a specific tourist
     * @param tourist The tourist to modify
     */
    public static void showModifyTouristFrame(Tourist tourist) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        currentFrame = new ModifyTouristFrame(touristService, tourist);
        currentFrame.setVisible(true);
    }
    /**
     * Handle connection errors (Interruption to ETOUR server)
     */
    public static void handleConnectionError(String message) {
        int option = JOptionPane.showConfirmDialog(null, 
            "Connection Error: " + message + "\n\nWould you like to try reconnecting?",
            "Server Connection Error",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.ERROR_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
            // Attempt to reconnect
            touristService.reconnect();
            // Refresh the current view after reconnection
            if (currentFrame instanceof SearchTouristFrame) {
                showSearchTouristFrame();
            } else if (currentFrame instanceof ModifyTouristFrame) {
                // Go back to search to avoid data loss
                showSearchTouristFrame();
            }
        } else {
            // User chose not to reconnect - cannot proceed without connection
            JOptionPane.showMessageDialog(null,
                "Cannot proceed without server connection. Returning to main screen.",
                "Connection Required",
                JOptionPane.WARNING_MESSAGE);
            // Return to search screen
            showSearchTouristFrame();
        }
    }
    /**
     * Display error dialog (Use Case: Errored)
     */
    public static void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(null, 
            "Validation Error: " + message,
            "Invalid Data",
            JOptionPane.ERROR_MESSAGE);
    }
    /**
     * Exit the application with proper cleanup
     */
    public static void exitApplication() {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        // In a real implementation, add cleanup for resources like database connections
        System.exit(0);
    }
}