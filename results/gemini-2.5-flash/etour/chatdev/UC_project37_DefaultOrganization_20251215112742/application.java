/*
The main application entry point for the ChatDev Logout demonstration.
*/
package com.chatdev;
import com.chatdev.gui.LogoutView;
import com.chatdev.session.LoginResult;
import com.chatdev.session.SessionManager;
import com.chatdev.store.UserStore; // For initial user setup, though not strictly part of logout flow
import javax.swing.*;
/**
 * The main application entry point for the ChatDev Logout demonstration.
 * This class sets up the application's initial state (e.g., a dummy login)
 * and launches the graphical user interface.
 */
public class Application {
    /**
     * The main method to start the application.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // --- Satisfying Entry Condition: "A registered user has previously made a successful Login." ---
        // For demonstration purposes, we automatically log in a user.
        // In a real application, this would be preceded by a login screen.
        LoginResult loginStatus = SessionManager.login("testuser", "password123");
        // Ensure the initial login was successful before showing the logout view.
        if (loginStatus != LoginResult.SUCCESS) {
            String errorMessage;
            switch (loginStatus) {
                case INVALID_CREDENTIALS:
                    errorMessage = "Invalid credentials for initial dummy login (testuser). " +
                                   "Please check UserStore for predefined users.";
                    break;
                case ALREADY_LOGGED_IN:
                    errorMessage = "A user was unexpectedly already logged in during application initialization.";
                    break;
                // Removed UNKNOWN_ERROR case as it's not returned by SessionManager.login
                default: // This default case would now catch any unexpected future LoginResult values
                    errorMessage = "An unexpected login status occurred during initial login: " + loginStatus;
                    break;
            }
            System.err.println("Failed to perform initial dummy login: " + errorMessage + " Exiting application.");
            JOptionPane.showMessageDialog(null, "Failed to initialize application: " + errorMessage, "Initialization Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(() -> {
            LogoutView logoutView = new LogoutView("ChatDev Logout Application");
            logoutView.setViewVisible(true);
        });
    }
}